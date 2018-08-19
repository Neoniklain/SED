package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment.ISheduleDepartmentManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.services.accessControlService.IAccessControlService;
import com.unesco.core.services.dataService.account.professorService.IProfessorDataService;
import com.unesco.core.services.dataService.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.dataService.schedule.groupService.IGroupDataService;
import com.unesco.core.services.dataService.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.dataService.schedule.pairService.IPairDataService;
import com.unesco.core.services.userService.IUserService;
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


    @Autowired
    private IAccessControlService accessСontrolService;
    @Autowired
    private IUserService userService;

    public ResponseStatusDTO getDepartmentPairs(long departmentId) {
        sheduleDepartmentManager.init(pairDataService.getAllByDepartament(departmentId),
                professorDataService.getAllByDepartament(departmentId),
                departmentDataService.get(departmentId));

        return new ResponseStatusDTO(StatusTypes.OK, sheduleDepartmentManager.get());
    }

    public ResponseStatusDTO getGroupPairs(long groupId) {
        sheduleManager.init(pairDataService.getAllByGroup(groupId),
                groupDataService.get(groupId));

        return new ResponseStatusDTO(StatusTypes.OK, sheduleManager.get());
    }

    public ResponseStatusDTO getPair(long pairId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.get(pairId));
    }

    public ResponseStatusDTO deletePair(long pairId) {
        ResponseStatusDTO res = new ResponseStatusDTO(StatusTypes.OK);
        try {
            pairDataService.delete(pairId);
            res.addMessage("Занятие удалено.");
            return res;
        }
        catch (Exception e) {
            return new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
    }

    public ResponseStatusDTO getProfessorPairs(long professorId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.getAllByProfessor(professorId));
    }

    public ResponseStatusDTO getLessonPairs(long lessonId) {
        return new ResponseStatusDTO(StatusTypes.OK, pairDataService.getAllByLesson(lessonId));
    }

    public ResponseStatusDTO getLessonsForProfessor(long professorId) {
        return new ResponseStatusDTO(StatusTypes.OK, lessonDataService.getByProfessorId(professorId));
    }

    public ResponseStatusDTO savePair(@RequestBody PairDTO pairModel) {
        pairManager.init(pairModel);
        
        ResponseStatusDTO result = pairManager.validate();
        if(result.getStatus() != StatusTypes.OK) return result;

        result = pairManager.CheckIntersection(pairDataService.getAll());
        if(result.getStatus() != StatusTypes.OK) return result;

        try {
            pairDataService.save(pairManager.get());
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
