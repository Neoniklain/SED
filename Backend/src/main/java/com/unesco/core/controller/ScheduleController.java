package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment.ISheduleDepartmentManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.services.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.schedule.pairService.IPairDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ScheduleController {

    @Autowired
    private ISheduleManager sheduleManager;
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

    public ResponseStatusDTO getDepartmentPairs(long departmentId) {

        sheduleDepartmentManager.Init(pairDataService.GetAllByDepartament(departmentId),
                professorDataService.GetAllByDepartament(departmentId),
                departmentDataService.Get(departmentId));

        return new ResponseStatusDTO(StatusTypes.OK, sheduleDepartmentManager.Get());
    }

    public ResponseStatusDTO getGroupPairs(long groupId) {

        sheduleManager.Init(pairDataService.GetAllByGroup(groupId),
                groupDataService.Get(groupId));

        return new ResponseStatusDTO(StatusTypes.OK, sheduleManager.Get());
    }

    public ResponseStatusDTO getPair(long pairId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.Get(pairId));
    }

    public ResponseStatusDTO deletePair(long pairId) {
        ResponseStatusDTO res = new ResponseStatusDTO(StatusTypes.OK);
        try {
            pairDataService.Delete(pairId);
            res.addMessage("Занятие удалено.");
            return res;
        }
        catch (Exception e) {
            return new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
    }

    public ResponseStatusDTO getProfessorPairs(long professorId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.GetAllByProfessor(professorId));
    }

    public ResponseStatusDTO getLessonPairs(long lessonId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.GetAllByLesson(lessonId));
    }

    public ResponseStatusDTO getLessonsForProfessor(long professorId) {
        return new ResponseStatusDTO(StatusTypes.OK, lessonDataService.GetByProfessorId(professorId));
    }

    public ResponseStatusDTO savePair(@RequestBody PairDTO pairModel) {
        pairManager.Init(pairModel);
        
        ResponseStatusDTO result = pairManager.Validate();
        if(result.getStatus() != StatusTypes.OK) return result;

        result = pairManager.CheckIntersection(pairDataService.GetAll());
        if(result.getStatus() != StatusTypes.OK) return result;

        try {
            pairDataService.Save(pairManager.Get());
            result.addMessage("Занятие сохранено.");
        }
        catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("При создании занятия произошла ошибка");
            result.addErrors(e.getMessage());
        }
        return result;
    }

}
