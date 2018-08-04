package com.unesco.core.services.journal.journal;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.services.account.studentService.IStudentDataService;
import com.unesco.core.services.journal.point.IPointDataService;
import com.unesco.core.services.journal.pointType.IPointTypeDataService;
import com.unesco.core.services.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.schedule.pairService.IPairDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JournalDataService implements IJournalDataService {

    @Autowired
    private IStudentDataService studentDataService;
    @Autowired
    private IPairDataService pairDataService;
    @Autowired
    private ILessonDataService lessonDataService;
    @Autowired
    private IPointTypeDataService pointTypeDataService;
    @Autowired
    private IPointDataService pointDataService;

    public JournalDTO Get(long lessonId)
    {
        LessonDTO lesson = lessonDataService.Get(lessonId);
        List<StudentDTO> students = studentDataService.GetByGroup(lesson.getGroup().getId());
        List<PairDTO> pairs = pairDataService.GetAllByLesson(lesson.getId());

        Calendar starDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("dd.MM");
        Set<Date> days = new HashSet<>();

        for (PairDTO pair: pairs) {

            starDate.set(2018,5,1);
            endDate.set(2018,8,28);

            switch (pair.getDayofweek()) {
                case "Понедельник":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
                case "Вторник":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
                case "Среда":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
                case "Четверг":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
                case "Пятница":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
                case "Суббота":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
                case "Воскресенье":
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                    break;
            }

            // для четных/нечетных недель
            if(!pair.getWeektype().equals("Все"))
            {
                if(pair.getWeektype().equals("Чет")) {
                    days.add(getZeroTimeDate(starDate.getTime()));
                    starDate.add(Calendar.DAY_OF_WEEK, 14); //Прибавляем две недели
                } else {
                    starDate.add(Calendar.DAY_OF_WEEK, 7); //Прибавляем неделю
                    days.add(getZeroTimeDate(starDate.getTime()));
                    starDate.add(Calendar.DAY_OF_WEEK, 14); //Прибавляем две недели
                }
            }

            int amount = pair.getWeektype().equals("Все") ? 7 : 14;

            for(int i = 0; starDate.getTime().compareTo(endDate.getTime()) < 0; i++){
                days.add(getZeroTimeDate(starDate.getTime()));
                starDate.add(Calendar.DAY_OF_WEEK, amount);
            }
        }

        List<PointDTO> points = new ArrayList<>();

        for (StudentDTO student : students ) {
            for (PairDTO pair : pairs ) {
                points.addAll(pointDataService.GetByStudentAndPair(student.getUser().getId(), pair.getId()));
            }
        }

        JournalDTO model = new JournalDTO();
        model.setPairs(pairs);
        model.setLesson(lesson);
        model.setDates(days.stream().collect(Collectors.toList()));
        model.setStudents(students);
        model.setJournalCell(points);

        return model;
    }

    public JournalDTO Save(JournalDTO journal)
    {
        List<PointDTO> result = new ArrayList<>();

        for (PointDTO point : journal.getJournalCell() ) {

            if(point.getType().getId() == 0) {
                PointTypeDTO pointType = pointTypeDataService.FindByName(point.getType().getName());
                if(pointType==null) {
                    throw new NullPointerException("Не верно указан тип отметки");
                }
                point.setType(pointType);
            }

            if(point.getId() == 0) {

                PointDTO findPoint = pointDataService.GetByStudentAndDateAndTypeAndPair(
                        point.getStudent().getUser().getId(),
                        point.getDate(),
                        point.getType().getId(),
                        point.getPair().getId());

                if(findPoint!=null) {
                    point.setId(findPoint.getId());
                }
            }
            if(point.getValue() != 0) {
                result.add(pointDataService.Save(point));
                continue;
            }
            if(point.getValue() == 0 && point.getId() != 0) {
                pointDataService.Delete(point.getId());
                continue;
            }

        }
        journal.setJournalCell(result);
        return journal;
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
