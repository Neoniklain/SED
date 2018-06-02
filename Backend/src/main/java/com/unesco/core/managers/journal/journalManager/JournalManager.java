package com.unesco.core.managers.journal.journalManager;

import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.IPairEventListManager;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.journal.JournalModel;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.models.journal.PointModel;
import com.unesco.core.models.journal.PointTypeModel;
import com.unesco.core.models.shedule.PairModel;
import com.unesco.core.utils.PointTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class JournalManager implements IJournalManager {

    @Autowired
    private IPairEventListManager pairEventListManager;

    JournalModel journal;

    public JournalManager() {
        journal = new JournalModel();
    }

    public void Init(JournalModel journal)
    {
        this.journal = journal;
    }

    public void InitEmptyCells(List<LessonEventModel> lessonEvents)
    {
        pairEventListManager.Init(lessonEvents);
        pairEventListManager.ApplayFilter(this.journal.getLesson());
        List<LessonEventModel> LessonEvents = pairEventListManager.GetAll();

        List<PointModel> points = new ArrayList<>();

        ArrayList<Object> objects = new ArrayList<>();

        for (Date date : journal.getDates()) {
            for (StudentModel student : journal.getStudents()) {
                for (PairModel pair : journal.getPairs()) {


                    List<PointModel> find = this.journal.getJournalCell().stream().
                            filter(o -> o.getStudent().getId() == student.getId()
                                    && getZeroTimeDate(o.getDate()).compareTo(getZeroTimeDate(date)) == 0
                                    && o.getPair().getId() == pair.getId()
                            )
                            .collect(Collectors.toList());

                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    boolean pairEqDay = translateDay(dayOfWeek).equals(pair.getDayofweek());

                    if (find.size() == 0 && pairEqDay) {
                        PointModel point = new PointModel();
                        point.setValue(0);
                        point.setId(0);
                        point.setStudent(student);
                        PointTypeModel visit = new PointTypeModel();
                        visit.setName(PointTypes.Visitation.toString());
                        point.setType(visit);
                        point.setDate(getZeroTimeDate(date));
                        point.setPair(pair);
                        points.add(point);
                    }

                }
            }
        }

        for (StudentModel student : journal.getStudents()) {
            for (LessonEventModel currentLessonEvent : LessonEvents) {
                PointModel pointLessonEvent = new PointModel();
                pointLessonEvent.setValue(0);
                pointLessonEvent.setId(0);
                pointLessonEvent.setStudent(student);
                pointLessonEvent.setType(currentLessonEvent.getType());
                pointLessonEvent.setDate(getZeroTimeDate(currentLessonEvent.getDate()));
                pointLessonEvent.setPair(journal.getPairs().get(0));
                points.add(pointLessonEvent);
            }
        }


        points.addAll(this.journal.getJournalCell());
        this.journal.setJournalCell(points);

    }

    public JournalModel Get() {
        return journal;
    }

    private String translateDay(int dayNum) {
        switch (dayNum) {
            case 1:
                return "Воскресенье";
            case 2:
                return "Понедельник";
            case 3:
                return "Вторник";
            case 4:
                return "Среда";
            case 5:
                return "Четверг";
            case 6:
                return "Пятница";
            case 7:
                return "Суббота";
        }
        return "";
    }

    private Date getZeroTimeDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( fecha );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();

        return res;
    }

}
