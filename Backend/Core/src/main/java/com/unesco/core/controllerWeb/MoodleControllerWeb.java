package com.unesco.core.controllerWeb;

import com.unesco.core.controller.MoodleController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/moodle")
public class MoodleControllerWeb {

    @Autowired
    MoodleController _moodleController;

    @RequestMapping(value = "getAllCourses")
    public ResponseStatusDTO GetAllCourses() {
        return _moodleController.GetAllCourses();
    }

    @RequestMapping(value = "getAllUsers")
    public ResponseStatusDTO GetAllUsers() {
        return _moodleController.GetAllUsers();
    }

    @RequestMapping(value = "getAllGroups")
    public ResponseStatusDTO GetAllGroups() {
        return _moodleController.GetAllGroups();
    }

    @RequestMapping(value = "createCourses")
    public ResponseStatusDTO CreateCourses() {
        return _moodleController.CreateCourses();
    }

    @RequestMapping(value = "createGroups")
    public ResponseStatusDTO CreateGroups() {
        return _moodleController.CreateGroups();
    }

    @RequestMapping(value = "createStudents")
    public ResponseStatusDTO CreateStudents() {
        return _moodleController.CreateStudents();
    }

    @RequestMapping(value = "assignStudentsOnGroups")
    public ResponseStatusDTO AssignStudentsOnGroups() {
        return _moodleController.AssignStudentsOnGroups();
    }
}
