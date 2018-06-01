package com.unesco.core.controller;

import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment.ISheduleDepartmentManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.PairModel;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.studentService.IStudentDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.services.schedule.pairService.IPairDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/shedule")
public class SheduleController {

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
    private IStudentDataService studentDataService;
    @Autowired
    private IGroupDataService groupDataService;
    @Autowired
    private IPairDataService pairDataService;

    @RequestMapping("/department/{id}/pairs")
    public ResponseStatus getDepartmentPairs(@PathVariable("id") long departmentId) {

        sheduleDepartmentManager.Init(pairDataService.GetAllByDepartament(departmentId),
                professorDataService.GetAllByDepartament(departmentId),
                departmentDataService.Get(departmentId));

        return new ResponseStatus(StatusTypes.OK, sheduleDepartmentManager.Get());
    }

    @RequestMapping("/group/{id}/pairs")
    public ResponseStatus getGroupPairs(@PathVariable("id") long groupId) {

        sheduleManager.Init(pairDataService.GetAllByGroup(groupId),
                groupDataService.Get(groupId));

        return new ResponseStatus(StatusTypes.OK, sheduleManager.Get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pair/{id}")
    public ResponseStatus getPair(@PathVariable("id") long pairId) {
        return new ResponseStatus(StatusTypes.OK, pairDataService.Get(pairId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pair/{id}")
    public ResponseStatus deletePair(@PathVariable("id") long pairId) {
        ResponseStatus res = new ResponseStatus(StatusTypes.OK);
        try {
            pairDataService.Delete(pairId);
            res.addMessage("Занятие удалено.");
            return res;
        }
        catch (Exception e) {
            return new ResponseStatus(StatusTypes.ERROR, e.getMessage());
        }
    }

    @RequestMapping("/professor/{id}/pairs")
    public ResponseStatus getProfessorPairs(@PathVariable("id") long professorId) {
        professorDataService.Get(professorId);
        return new ResponseStatus(StatusTypes.OK, pairDataService.GetAllByProfessor(professorId));
    }

    @RequestMapping("/professors")
    public ResponseStatus getProfessors() {
        return new ResponseStatus(StatusTypes.OK, professorDataService.GetAll());
    }

    @RequestMapping("/professor/{id}")
    public ResponseStatus getProfessor(@PathVariable("id") long professorId) {
        return new ResponseStatus(StatusTypes.OK, professorDataService.Get(professorId));
    }

    @RequestMapping("/student/{id}")
    public ResponseStatus getStudent(@PathVariable("id") long studentId) {
        return new ResponseStatus(StatusTypes.OK, studentDataService.Get(studentId));
    }

    @RequestMapping("/pair/save")
    public ResponseStatus savePair(@RequestBody PairModel pairModel) {
        pairManager.Init(pairModel);
        ResponseStatus result = pairManager.CheckIntersection(pairDataService.GetAll());
        if(result.getStatus() == StatusTypes.OK) {
            try {
                pairDataService.Save(pairManager.Get());
                result.addMessage("Занятие сохранено.");
                return result;
            }
            catch (Exception e) {
                result.setStatus(StatusTypes.ERROR);
                result.addErrors("При создании занятия произошла ошибка");
                result.addErrors(e.getMessage());
                return result;
            }
        }
        else {
            return result;
        }
    }

}
