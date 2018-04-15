package com.unesco.core.controller;

import com.unesco.core.models.*;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.services.dictionaryDataService.IDitionaryDataService;
import com.unesco.core.services.sheduleService.ISheduleService;
import com.unesco.core.services.userService.IUserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/demo")
public class SheduleController {

    @Autowired
    private IDitionaryDataService ditionaryDataService;
    @Autowired
    private ISheduleService sheduleService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/department/{id}/pairs")
    public List<DepartmentSheduleModel> getDepartmentPairs(@PathVariable("id") int id) {
        DepartmentModel department = ditionaryDataService.getDepartment(id);
        return sheduleService.getPairs(department);
    }

    @RequestMapping("/department/{id}/pairs/even")
    public List<DepartmentSheduleModel> getOddDepartmentPairs(@PathVariable("id") int id) {
        DepartmentModel department = ditionaryDataService.getDepartment(id);
        return sheduleService.getEvenPairs(department);
    }

    @RequestMapping("/department/{id}/pairs/odd")
    public List<DepartmentSheduleModel> getEvenDepartmentPairs(@PathVariable("id") int id) {
        DepartmentModel department = ditionaryDataService.getDepartment(id);
        return sheduleService.getOddPairs(department);
    }

    @RequestMapping("/group/{id}/pairs")
    public List<PairModel> getGroupPairs(@PathVariable("id") int id) {
        GroupModel group = ditionaryDataService.getGroup(id);
        return sheduleService.getPairs(group);
    }

    @RequestMapping("/group/{id}/pairs/even")
    public List<PairModel> getEvenGroupPairs(@PathVariable("id") int id) {
        GroupModel group = ditionaryDataService.getGroup(id);
        return sheduleService.getEvenPairs(group);
    }

    @RequestMapping("/group/{id}/pairs/odd")
    public List<PairModel> getOddGroupPairs(@PathVariable("id") int id) {
        GroupModel group = ditionaryDataService.getGroup(id);
        return sheduleService.getOddPairs(group);
    }

    @RequestMapping("/groups")
    public List<GroupModel> getGroups() {
        return ditionaryDataService.getGroups();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pair/{id}")
    public PairModel getPair(@PathVariable("id") int id) {
        return sheduleService.getPair(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pair/{id}")
    public JSONResponseStatus deletePair(@PathVariable("id") int id) {
        return sheduleService.deletePair(id);
    }

    @RequestMapping("/professor/{id}/pairs")
    public List<PairModel> getProfessorPairs(@PathVariable("id") int id) {
        ProfessorModel professor = userService.getProfessor(id);
        return sheduleService.getPairs(professor);
    }

    @RequestMapping("/professor/{id}/pairs/even")
    public List<PairModel> getEvenProfessorPairs(@PathVariable("id") int id) {
        ProfessorModel professor = userService.getProfessor(id);
        return sheduleService.getEvenPairs(professor);
    }

    @RequestMapping("/professor/{id}/pairs/odd")
    public List<PairModel> getOddProfessorPairs(@PathVariable("id") int id) {
        ProfessorModel professor = userService.getProfessor(id);
        return sheduleService.getOddPairs(professor);
    }

    @RequestMapping("/professors")
    public List<ProfessorModel> getProfessors() {
        return userService.getProfessors();
    }

    @RequestMapping("/professor/{id}")
    public ProfessorModel getProfessor(@PathVariable("id") int id) {
        return userService.getProfessor(id);
    }

    @RequestMapping("/student/{id}")
    public StudentModel getStudent(@PathVariable("id") int id) {
        return userService.getStudent(id);
    }

    @RequestMapping("/pair/save")
    public JSONResponseStatus savePair(@RequestBody PairModel pairModel) {
        return sheduleService.savePair(pairModel);
    }
}
