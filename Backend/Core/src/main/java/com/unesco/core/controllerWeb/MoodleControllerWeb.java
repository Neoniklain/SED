package com.unesco.core.controllerWeb;

import com.unesco.core.controller.MoodleController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.repositories.moodlerest.MoodleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

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

    @RequestMapping(value = "getUserById/{id}")
    public ResponseStatusDTO GetUserById(@PathVariable("id") long userId ) {
        return _moodleController.GetUserById(userId);
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

    @RequestMapping(value = "createStudents/{groupId}")
    public ResponseStatusDTO CreateStudents(@PathVariable("groupId") long groupId) {
        return _moodleController.CreateStudents(groupId);
    }

    @RequestMapping(value = "createUser/{userId}")
    public ResponseStatusDTO createUser(@PathVariable("userId") long userId) {
        return _moodleController.CreateUser(userId);
    }

    @RequestMapping(value = "createStudents")
    public ResponseStatusDTO CreateStudents() {
        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setStatus(StatusTypes.OK);

        long startTimeCreate = System.currentTimeMillis();
        ResponseStatusDTO createResult = _moodleController.CreateAllStudents();
        long endTimeCreate = System.currentTimeMillis();
        long betweenSecondsCreate = (endTimeCreate - startTimeCreate) / 1000;
        result.addMessage("Creation time = " + betweenSecondsCreate);

        if(createResult.getStatus() != StatusTypes.OK) {
            result.setStatus(StatusTypes.ERROR);
            result.addMessage("Error while students were created");
        }
        return result;
    }

    @RequestMapping(value = "assignStudentsOnGroups/{groupId}")
    public ResponseStatusDTO AssignStudentsOnGroups(@PathVariable("groupId") long groupId) {
        return _moodleController.AssignStudentsOnGroups(groupId);
    }

    @RequestMapping(value = "assignStudentsOnGroups")
    public ResponseStatusDTO AssignStudentsOnGroups() {
        return _moodleController.AssignStudentsOnGroups();
    }
}
