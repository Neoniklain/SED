package com.unesco.core.managers.account.studentManager;

import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.shedule.GroupModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StudentManager implements IStudentManager {

    StudentModel student;

    public StudentManager() {
        student = new StudentModel();
    }

    public void Init(StudentModel Student) {
        student = Student;
    }

    public StudentModel Get() {
        return student;
    }

    public void SetGroup(GroupModel group)
    {
        student.setGroup(group);
    }

    public void Create(UserModel user) {
        student.setId(user.getId());
        student.setRoles(user.getRoles());
        student.setUserFIO(user.getUserFIO());
        student.setUsername(user.getUsername());
        student.setEmail(user.getEmail());
        student.setGroup(null);
    }

}
