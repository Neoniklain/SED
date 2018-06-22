package com.unesco.core.managers.account.studentManager;

import com.unesco.core.managers.account.studentManager.interfaces.studentList.IStudentListManager;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.shedule.GroupDTO;
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

    public List<StudentDTO> studentList;

    public StudentListManager() {
        studentList = new ArrayList<>();
    }

    public void Init(List<StudentDTO> StudentList) {
        studentList = StudentList;
    }

    public List<StudentDTO> GetAll() {
        return studentList;
    }

    public void ApplayFilter(GroupDTO group) {
        List<StudentDTO> result = new ArrayList<>();
        for (StudentDTO s : result ) {
            if (s.getGroup() == group)
                result.add(s);

        }
        studentList = result;
    }

}
