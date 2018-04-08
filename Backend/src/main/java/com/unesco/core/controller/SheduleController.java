package com.unesco.core.controller;

import com.unesco.core.models.*;
import com.unesco.core.services.dictionaryDataService.IDitionaryDataService;
import com.unesco.core.services.sheduleService.ISheduleService;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
public class SheduleController {

    @Autowired
    private IDitionaryDataService ditionaryDataService;
    @Autowired
    private ISheduleService sheduleService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/department/{id}/pairs/even")
    public Map<ProfessorModel, List<PairModel>> getOddDepartmentPairs(@PathVariable("id") int id) {
        DepartmentModel department = ditionaryDataService.getDepartment(id);
        return sheduleService.getEvenPairs(department);
    }

    @RequestMapping("/department/{id}/pairs/odd")
    public Map<ProfessorModel, List<PairModel>> getEvenDepartmentPairs(@PathVariable("id") int id) {
        DepartmentModel department = ditionaryDataService.getDepartment(id);
        return sheduleService.getOddPairs(department);
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

    @RequestMapping("/pair/{id}")
    public PairModel getPair(@PathVariable("id") int id) {
        return sheduleService.getPair(id);
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
}
