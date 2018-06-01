package com.unesco.core.managers.account.studentManager;

import com.unesco.core.managers.account.studentManager.interfaces.studentList.IStudentListManager;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.services.account.studentService.IStudentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class StudentListManager implements IStudentListManager {

    @Autowired
    public IStudentDataService dataService;

    public List<StudentModel> studentList;

    public StudentListManager() {
        studentList = new ArrayList<>();
    }

    public void Init(List<StudentModel> StudentList) {
        studentList = StudentList;
    }

    public List<StudentModel> GetAll() {
        return studentList;
    }

    public void ApplayFilter(GroupModel group) {
        List<StudentModel> result = new ArrayList<>();
        for (StudentModel s : result ) {
            if (s.getGroup() == group)
                result.add(s);

        }
        studentList = result;
    }

}
