package com.unesco.core.managers.moodle;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.managers.moodle.interfaces.IMoodleManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.repositories.moodlerest.MoodleCohort;
import com.unesco.core.repositories.moodlerest.MoodleCourse;
import com.unesco.core.repositories.moodlerest.MoodleUser;
import com.unesco.core.services.dataService.account.studentService.IStudentDataService;
import com.unesco.core.services.dataService.moodleService.IMoodleService;
import com.unesco.core.services.dataService.schedule.groupService.IGroupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoodleManager implements IMoodleManager {

    @Autowired
    IMoodleService _moodleService;
    @Autowired
    IGroupDataService _groupDataService;
    @Autowired
    IStudentDataService _studentDataService;

    public MoodleCourse[] GetAllCourses() {
        return _moodleService.GetAllCourses();
    }

    public MoodleUser[] GetAllUsers() {
        return _moodleService.GetAllUsers();
    }

    public MoodleCohort[] GetAllGroups() {
        return _moodleService.GetAllGroups();
    }

    public MoodleCourse[] CreateCourses() {
        return _moodleService.CreateCourses();
    }

    public MoodleUser[] CreateStudents() {
        List<GroupDTO> groups = _groupDataService.getAll();
        List<StudentDTO> studentsOfGroup = _studentDataService.getByGroup(groups.get(4).getId());
        return _moodleService.CreateStudents(studentsOfGroup);
    }

    public MoodleCohort[] CreateGroups() {
        List<GroupDTO> groups = _groupDataService.getAll();
        return _moodleService.CreateGroups(groups);
    }

    public boolean AssignStudentsOnGroups() {
        List<GroupDTO> groups = _groupDataService.getAll();
        List<StudentDTO> studentsOfGroup = _studentDataService.getByGroup(groups.get(4).getId());
        return _moodleService.AddUsersToCohorts(studentsOfGroup);
    }
}
