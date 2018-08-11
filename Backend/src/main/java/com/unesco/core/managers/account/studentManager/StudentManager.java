package com.unesco.core.managers.account.studentManager;

import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StudentManager implements IStudentManager {

    StudentDTO student;

    public StudentManager() {
        student = new StudentDTO();
    }

    public void init(StudentDTO Student) {
        student = Student;
    }

    public StudentDTO get() {
        return student;
    }

    public void SetGroup(GroupDTO group)
    {
        student.setGroup(group);
    }

    public void Create(UserDTO user) {
        student.setId(0);
        student.setUser(user);
        student.setGroup(null);
    }

}
