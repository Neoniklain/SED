package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.plan.EducationPeriodDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment.ISheduleDepartmentManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.services.dataService.account.professorService.IProfessorDataService;
import com.unesco.core.services.dataService.plan.educationPeriodService.IEducationPeriodService;
import com.unesco.core.services.dataService.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.dataService.schedule.groupService.IGroupDataService;
import com.unesco.core.services.dataService.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.dataService.schedule.pairService.IPairDataService;
import com.unesco.core.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleController {

    @Autowired
    private ISheduleManager scheduleManager;
    @Autowired
    private IPairManager pairManager;
    @Autowired
    private ISheduleDepartmentManager sheduleDepartmentManager;
    @Autowired
    private IDepartmentDataService departmentDataService;
    @Autowired
    private IProfessorDataService professorDataService;
    @Autowired
    private IGroupDataService groupDataService;
    @Autowired
    private IPairDataService pairDataService;
    @Autowired
    private ILessonDataService lessonDataService;
    @Autowired
    private IEducationPeriodService educationPeriodService;

    public ResponseStatusDTO getDepartmentPairs(long departmentId, int semester, int year) {
        sheduleDepartmentManager.init(pairDataService.getAllByDepartament(departmentId, semester, year),
                professorDataService.getAllByDepartament(departmentId),
                departmentDataService.get(departmentId));

        return new ResponseStatusDTO(StatusTypes.OK, sheduleDepartmentManager.get());
    }

    public ResponseStatusDTO getGroupPairs(long groupId, int semester, int year) {
        scheduleManager.init(pairDataService.getAllByGroup(groupId, semester, year),
                groupDataService.get(groupId));

        return new ResponseStatusDTO(StatusTypes.OK, scheduleManager.get().getPairs());
    }

    public ResponseStatusDTO getProfessorPairs(long professorId, int semester, int year) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.getAllByProfessor(professorId, semester, year));
    }

    public ResponseStatusDTO getPair(long pairId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.get(pairId));
    }

    public ResponseStatusDTO deletePair(long pairId) {
        ResponseStatusDTO res = new ResponseStatusDTO(StatusTypes.OK);
        res = pairDataService.delete(pairId);
        if (res.getStatus() == StatusTypes.ERROR)
            return res;
        res.addMessage("Занятие удалено.");
        return res;
    }

    public ResponseStatusDTO getLessonPairs(long lessonId, int semester, int year) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.getAllByLesson(lessonId, semester, year));
    }

    public ResponseStatusDTO getLessonsForProfessor(long professorId, int semester, int year) {
        return new ResponseStatusDTO(StatusTypes.OK, lessonDataService.getByProfessorId(professorId, semester, year));
    }

    public ResponseStatusDTO getLessonsForGroup(long groupId, int semester, int year) {
        return new ResponseStatusDTO(StatusTypes.OK, lessonDataService.getByGroupId(groupId, semester, year));
    }

    public ResponseStatusDTO savePair(PairDTO pairModel, boolean skipWarnings) {
        pairManager.init(pairModel);

        ResponseStatusDTO result = pairManager.validate();
        if (result.getStatus() != StatusTypes.OK) return result;

        if (!skipWarnings) {
            result = pairManager.CheckIntersection(pairDataService.getAll());
            if (result.getStatus() != StatusTypes.OK) return result;
        }

        result = pairDataService.save(pairManager.get());
        if (result.getStatus() == StatusTypes.OK) {
            result.addMessage("Занятие сохранено.");
        }

        return result;
    }

    public ResponseStatusDTO getPrityWeek(int semester, int year) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        EducationPeriodDTO educationPeriod = educationPeriodService.getEducationPeriodForYearAndSemester(semester, year);
        if (educationPeriod != null) {
            Calendar from = Calendar.getInstance();
            from.setTime(educationPeriod.getStartDate());
            result.setData(DateHelper.getPrityWeek(new Date(), from.getTime()));
            return result;
        }
        result.setData(0);
        return result;
    }

    public ResponseStatusDTO getEducationPeriod(long specialityId) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        List<EducationPeriodDTO> all = educationPeriodService.getAll();
        result.setData(all);
        return result;
    }

    public ResponseStatusDTO saveEducationPeriod(EducationPeriodDTO educationPeriod) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);

        result = educationPeriodService.save(educationPeriod);
        if (result.getStatus() == StatusTypes.OK) {
            result.addMessage("Период сохранен.");
        }
        return result;
    }

    public ResponseStatusDTO getEducationPeriodForGroup(long groupId, int semester, int year) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        GroupDTO group = groupDataService.get(groupId);
        if (group == null) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("Не найдена группа");
            return result;
        }
        EducationPeriodDTO period = educationPeriodService.getEducationPeriodForGroup(group.getSpeciality().getId(), semester, year);
        result.setData(period);
        return result;
    }

}
