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

    @RequestMapping(value = "createAllStudents")
    public ResponseStatusDTO CreateAllStudents() {
        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setStatus(StatusTypes.OK);
        ResponseStatusDTO assignResult = new ResponseStatusDTO();
        assignResult.setStatus(StatusTypes.ERROR);


        long startTimeCreate = System.currentTimeMillis();
        ResponseStatusDTO createResult = _moodleController.CreateAllStudents();
        long endTimeCreate = System.currentTimeMillis();
        long betweenSecondsCreate = (endTimeCreate - startTimeCreate) / 1000;
        result.addMessage("Creation time = " + betweenSecondsCreate);

        if(createResult.getStatus() == StatusTypes.OK) {
            result.addMessage("Count of created users = " + ((MoodleUser[]) createResult.getData()).length);
            long startTimeAssign = System.currentTimeMillis();
            assignResult = _moodleController.AssignStudentsOnGroups();
            long endTimeAssign = System.currentTimeMillis();
            long betweenSecondsAssign = (endTimeAssign - startTimeAssign) / 1000;
            result.addMessage("Assignment time = " + betweenSecondsAssign);
        }
        else {
            result.setStatus(StatusTypes.ERROR);
            result.addMessage("Error while users was created");
        }
        if(assignResult.getStatus() == StatusTypes.OK) {
            result.addMessage("All students was assigned? = " + (boolean)assignResult.getData());
        }
        else {
            result.setStatus(StatusTypes.ERROR);
            result.addMessage("Error while users was assigned");
        }

        return result;
    }

    @RequestMapping(value = "assignStudentsOnGroups")
    public ResponseStatusDTO AssignStudentsOnGroups() {
        return _moodleController.AssignStudentsOnGroups();
    }
}
