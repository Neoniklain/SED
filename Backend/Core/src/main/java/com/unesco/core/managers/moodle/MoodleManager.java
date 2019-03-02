package com.unesco.core.managers.moodle;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.managers.moodle.interfaces.IMoodleManager;
import com.unesco.core.repositories.moodlerest.MoodleCohort;
import com.unesco.core.repositories.moodlerest.MoodleCourse;
import com.unesco.core.repositories.moodlerest.MoodleUser;
import com.unesco.core.services.dataService.account.studentService.IStudentDataService;
import com.unesco.core.services.dataService.moodleService.IMoodleService;
import com.unesco.core.services.dataService.schedule.groupService.IGroupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public MoodleUser[] CreateStudents(long groupId) {
        GroupDTO group = _groupDataService.get(groupId);
        return CreateStudents(group);
    }

    private MoodleUser[] CreateStudents(GroupDTO group) {
        List<StudentDTO> studentsOfGroup = _studentDataService.getByGroup(group.getId());
        return _moodleService.CreateStudents(studentsOfGroup);
    }

    public MoodleUser[] CreateAllStudents() {
        List<GroupDTO> groups = _groupDataService.getAll();
        List<MoodleUser> addedUsers = new ArrayList<>();
        for (GroupDTO group: groups) {
            try {
                addedUsers.addAll(Arrays.asList(CreateStudents(group)));
                // System.out.println(group.getName() + ": пользователи успешно созданы в moodle");
            }
            catch (Exception e) {
                System.out.println(group.getName() + ": не удалось создать пользователей в moodle");
            }
        }
        return addedUsers.toArray(new MoodleUser[addedUsers.size()]);
    }

    public MoodleCohort[] CreateGroups() {
        List<GroupDTO> groups = _groupDataService.getAll();
        return _moodleService.CreateGroups(groups);
    }

    private boolean AssignStudentsOnGroups(GroupDTO group) {
        List<StudentDTO> studentsOfGroup = _studentDataService.getByGroup(group.getId());
        return _moodleService.AddUsersToCohorts(studentsOfGroup);
    }

    public boolean AssignStudentsOnGroups(long groupId) {
        GroupDTO group = _groupDataService.get(groupId);
        return AssignStudentsOnGroups(group);
    }

    public boolean AllAssignStudentsOnGroups() {
        List<GroupDTO> groups = _groupDataService.getAll();
        boolean result = true;
        for (GroupDTO group: groups) {
            boolean temp = AssignStudentsOnGroups(group);
            if (temp == false) {
                result = false;
            }
        }
        return result;
    }
}
