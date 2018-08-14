package com.unesco.core.services.dataService.journal.journal;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.services.dataService.account.studentService.IStudentDataService;
import com.unesco.core.services.dataService.journal.point.IPointDataService;
import com.unesco.core.services.dataService.journal.pointType.IPointTypeDataService;
import com.unesco.core.services.dataService.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.dataService.schedule.pairService.IPairDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
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

    public JournalDTO get(long lessonId)
    {
        LessonDTO lesson = lessonDataService.get(lessonId);
        List<StudentDTO> students = studentDataService.getByGroup(lesson.getGroup().getId());
        List<PairDTO> pairs = pairDataService.getAllByLesson(lesson.getId());

        Calendar starDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

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
                default:
                    starDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
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

            while(starDate.getTime().compareTo(endDate.getTime()) < 0) {
                days.add(getZeroTimeDate(starDate.getTime()));
                starDate.add(Calendar.DAY_OF_WEEK, amount);
            }
        }

        List<PointDTO> points = new ArrayList<>();

        for (StudentDTO student : students ) {
            for (PairDTO pair : pairs ) {
                points.addAll(pointDataService.getByStudentAndPair(student.getUser().getId(), pair.getId()));
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

    public ResponseStatusDTO<JournalDTO> save(JournalDTO journal)
    {
        List<PointDTO> points = new ArrayList<>();

        ResponseStatusDTO<JournalDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);

        for (PointDTO point : journal.getJournalCell() ) {

            if(point.getType().getId() == 0) {
                PointTypeDTO pointType = pointTypeDataService.findByName(point.getType().getName());
                if(pointType==null) {
                    throw new InvalidParameterException("Не верно указан тип отметки");
                }
                point.setType(pointType);
            }

            if(point.getId() == 0) {

                PointDTO findPoint = pointDataService.getByStudentAndDateAndTypeAndPair(
                        point.getStudent().getUser().getId(),
                        point.getDate(),
                        point.getType().getId(),
                        point.getPair().getId());

                if(findPoint!=null) {
                    point.setId(findPoint.getId());
                }
            }
            if(point.getValue() != 0) {
                ResponseStatusDTO<PointDTO> savePointStatus = pointDataService.save(point);
                if(savePointStatus.getStatus() == StatusTypes.ERROR) {
                    result.setStatus(StatusTypes.ERROR);
                    result.setMessage(savePointStatus.getMessage());
                    return result;
                }
                points.add(savePointStatus.getData());
                continue;
            }
            if(point.getValue() == 0 && point.getId() != 0) {
                ResponseStatusDTO<PointDTO> deletePointStatus = pointDataService.delete(point.getId());
                if (deletePointStatus.getStatus() == StatusTypes.ERROR){
                    result.setStatus(StatusTypes.ERROR);
                    result.setMessage(deletePointStatus.getMessage());
                    return result;
                }
                continue;
            }
        }
        journal.setJournalCell(points);
        result.setData(journal);
        return result;
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
