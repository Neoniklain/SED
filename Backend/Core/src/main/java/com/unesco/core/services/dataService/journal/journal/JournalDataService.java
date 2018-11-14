package com.unesco.core.services.dataService.journal.journal;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.PointTypes;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.*;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.services.dataService.account.studentService.IStudentDataService;
import com.unesco.core.services.dataService.journal.point.IPointDataService;
import com.unesco.core.services.dataService.journal.pointType.IPointTypeDataService;
import com.unesco.core.services.dataService.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.dataService.schedule.pairService.IPairDataService;
import com.unesco.core.utils.DateHelper;
import com.unesco.core.utils.DayOfWeekHelper;
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
        return getForMonth(lessonId, -1);
    }

    public JournalDTO getForMonth(long lessonId, int month)
    {
        JournalDTO model = new JournalDTO();
        model.setComparison(new ArrayList<>());

        LessonDTO lesson = lessonDataService.get(lessonId);
        List<StudentJournalDTO> students = studentDataService.getByGroupAndLesson(lesson.getGroup().getId(), lesson.getId());
        List<PairDTO> pairs = pairDataService.getAllByLesson(lesson.getId());

        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();

        // Тут устанавливаются даты начала и конца обучения
        // Это конечно не верно, нужно установить как-то подругому, по хорошему
        // сделать сервис отвечающий за даты сессии
        // Нумерация месяцов идет с 0
        from.set(2018,8,1,12,0);
        to.set(2018,11,28,12,0);

        Calendar starDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        int toMonth = to.get(Calendar.MONTH);
        int fromMonth = from.get(Calendar.MONTH);

        // Устанавливаем дату начала и конца в соответсвии с запрошеным месяцем
        // Если месяц = -1 то берется журнал за весь период
        if(month != -1 && month >= from.get(Calendar.MONTH) && month <= to.get(Calendar.MONTH))
        {
            from.set(from.get(Calendar.YEAR), month,1,12,0);
            to.set(from.get(Calendar.YEAR), month, from.getActualMaximum(Calendar.DAY_OF_MONTH),12,0);
        }
        else if(month != -1 && month < fromMonth) {
            month = from.get(Calendar.MONTH);
            from.set(from.get(Calendar.YEAR), month,1,12,0);
            to.set(from.get(Calendar.YEAR), month, from.getActualMaximum(Calendar.DAY_OF_MONTH),12,0);
        }
        else if(month != -1 && month > toMonth) {
            month = to.get(Calendar.MONTH);
            from.set(from.get(Calendar.YEAR), month,1,12,0);
            to.set(from.get(Calendar.YEAR), month, from.getActualMaximum(Calendar.DAY_OF_MONTH),12,0);
        }
        Date t1 =  from.getTime();
        Date t2 =  to.getTime();

        // Заполнение занятий по указанным дням
        for (PairDTO pair: pairs) {
            starDate.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH),12,0);
            endDate.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH),12,0);

            starDate.set(Calendar.DAY_OF_WEEK, DayOfWeekHelper.getWeekNumber(pair.getDayofweek()));
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);

            int prityWeek = DateHelper.getPrityWeek(from.getTime(), starDate.getTime());

            // для четных/нечетных недель
            if(!pair.getWeektype().equals("Все"))
            {
                if(pair.getWeektype().equals("Чет") && prityWeek == 0) {
                    starDate.add(Calendar.DAY_OF_WEEK, 7); //Прибавляем неделю
                }
                if(pair.getWeektype().equals("Нечет") && prityWeek == 1) {
                    starDate.add(Calendar.DAY_OF_WEEK, 7); //Прибавляем неделю
                }
                addDate(model.getComparison(), getZeroTimeDate(starDate.getTime()), pair);
                starDate.add(Calendar.DAY_OF_WEEK, 14); //Прибавляем две недели
            }
            int amount = pair.getWeektype().equals("Все") ? 7 : 14;

            while(starDate.getTime().compareTo(endDate.getTime()) < 0) {
                addDate(model.getComparison(), getZeroTimeDate(starDate.getTime()), pair);
                starDate.add(Calendar.DAY_OF_WEEK, amount);
            }
        }

        // Добавляем в журнал сохраненные отметки
        List<PointDTO> points = new ArrayList<>();
        for (StudentJournalDTO student : students ) {
            for (PairDTO pair : pairs ) {
                points.addAll(pointDataService.getByStudentAndPair(student.getStudent().getUser().getId(), pair.getId()));
            }
        }
        model.setLesson(lesson);
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
                        point.getStudentId(),
                        point.getDate(),
                        point.getType().getId(),
                        point.getPairId());

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
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();

        return res;
    }

    private void addDate(List<ComparisonDTO> comp, Date date, PairDTO pair) {
        PointTypeDTO visit = new PointTypeDTO();
        visit.setName(PointTypes.Visitation.toString());

        if (comp.stream().noneMatch(x -> x.getDate().compareTo(date)==0)) {
            ComparisonDTO newComp = new ComparisonDTO();
            newComp.setDate(date);
            ComparisonPointDTO point = new ComparisonPointDTO();
            point.setType(visit);
            point.setPair(pair);
            newComp.setPoints(new ArrayList<ComparisonPointDTO>(){{add(point);}});
            comp.add(newComp);
        } else {
            ComparisonDTO newComp = new ComparisonDTO();

            ComparisonDTO comparisonDTO = comp.stream().filter(x -> x.getDate().compareTo(date) == 0).collect(Collectors.toList()).get(0);

            ComparisonPointDTO point = new ComparisonPointDTO();
            point.setType(visit);
            point.setPair(pair);

            List<ComparisonPointDTO> points = comparisonDTO.getPoints();
            points.add(point);

            newComp.setDate(date);
            newComp.setPoints(points);

            comp.remove(comparisonDTO);
            comp.add(newComp);
        }

    }

}
