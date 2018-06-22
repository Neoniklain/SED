package com.unesco.core.managers.journal.journalManager;

import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.ILessonEventListManager;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.journal.JournalDTO;
import com.unesco.core.models.journal.LessonEventDTO;
import com.unesco.core.models.journal.PointDTO;
import com.unesco.core.models.journal.PointTypeDTO;
import com.unesco.core.models.shedule.PairDTO;
import com.unesco.core.utils.PointTypes;
import com.unesco.core.utils.StatusTypes;
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
    private ILessonEventListManager pairEventListManager;

    JournalDTO journal;

    public JournalManager() {
        journal = new JournalDTO();
    }

    public void Init(JournalDTO journal)
    {
        this.journal = journal;
    }

    public void InitEmptyCells(List<LessonEventDTO> lessonEvents)
    {
        pairEventListManager.Init(lessonEvents);
        pairEventListManager.ApplayFilter(this.journal.getLesson());
        List<LessonEventDTO> LessonEvents = pairEventListManager.GetAll();

        List<PointDTO> points = new ArrayList<>();

        ArrayList<Object> objects = new ArrayList<>();

        journal.setDates(journal.getDates().stream().sorted().collect(Collectors.toList()));

        int weekNumber = 0;
        Date lastDate = journal.getDates().get(0);
        for (Date date : journal.getDates()) {

            // Для определения четности
            Calendar last = Calendar.getInstance();
            Calendar cur = Calendar.getInstance();
            last.setTime(lastDate);
            cur.setTime(date);
            if(last.get(Calendar.DAY_OF_WEEK) >= cur.get(Calendar.DAY_OF_WEEK) ) {
                weekNumber = weekNumber + 1;
            }

            for (StudentDTO student : journal.getStudents()) {
                for (PairDTO pair : journal.getPairs()) {


                    List<PointDTO> find = this.journal.getJournalCell().stream().
                            filter(o -> o.getStudent().getUser().getId() == student.getUser().getId()
                                    && o.getDate().compareTo(date) == 0
                                    && o.getType().getName().equals(PointTypes.Visitation.toString())
                                    && o.getPair().getId() == pair.getId()
                            )
                            .collect(Collectors.toList());

                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    boolean pairEqDay = translateDay(dayOfWeek).equals(pair.getDayofweek());

                    if (find.size() == 0 && pairEqDay) {

                        if(!pair.getWeektype().equals("Все")
                            && (
                                (pair.getWeektype().equals("Чет") && weekNumber % 2 != 0
                                ||
                                pair.getWeektype().equals("Нечет") && weekNumber % 2 == 0)
                                && journal.getPairs().stream().anyMatch(o -> o.getWeektype().equals("Все"))
                                )
                        ) {
                            continue;
                        }
                        PointDTO point = new PointDTO();
                        point.setValue(0);
                        point.setId(0);
                        point.setStudent(student);
                        PointTypeDTO visit = new PointTypeDTO();
                        visit.setName(PointTypes.Visitation.toString());
                        point.setType(visit);
                        point.setDate(date);
                        point.setPair(pair);
                        points.add(point);
                    }

                }
            }
            lastDate = date;
        }

        for (StudentDTO student : journal.getStudents()) {
            for (LessonEventDTO currentLessonEvent : LessonEvents) {
                if( !this.journal.getJournalCell().stream().anyMatch(
                        o -> o.getDate().compareTo(currentLessonEvent.getDate()) == 0
                        && o.getType().getId() == currentLessonEvent.getType().getId()
                )) {
                    PointDTO pointLessonEvent = new PointDTO();
                    pointLessonEvent.setValue(0);
                    pointLessonEvent.setId(0);
                    pointLessonEvent.setStudent(student);
                    pointLessonEvent.setType(currentLessonEvent.getType());
                    pointLessonEvent.setDate(currentLessonEvent.getDate());
                    pointLessonEvent.setPair(journal.getPairs().get(0));
                    points.add(pointLessonEvent);
                }
            }
        }


        points.addAll(this.journal.getJournalCell());
        this.journal.setJournalCell(points);

    }

    public List<Date> GetDates()
    {
        return  journal.getDates();
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        return responseStatusDTO;
    }

    public JournalDTO Get() {
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

}
