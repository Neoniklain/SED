package com.unesco.core.services.userService;

import com.unesco.core.models.ProfessorModel;
import com.unesco.core.models.StudentModel;
import com.unesco.core.models.account.UserCreateModel;
import com.unesco.core.models.account.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<ProfessorModel> getProfessors();
    ProfessorModel getProfessor(int id);
    int AddUser(UserCreateModel user);
    List<UserModel> getUser(String username);
    StudentModel getStudent(int id);

    void setUserGroup(int groupId, int userId);
    void setProfessorDepartment(int departmentId, int userId);
}
