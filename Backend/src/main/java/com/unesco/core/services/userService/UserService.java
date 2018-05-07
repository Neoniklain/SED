package com.unesco.core.services.userService;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Group;
import com.unesco.core.entities.Professor;
import com.unesco.core.entities.Student;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.models.DepartmentModel;
import com.unesco.core.models.GroupModel;
import com.unesco.core.models.ProfessorModel;
import com.unesco.core.models.StudentModel;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserCreateModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.repositories.account.StudentRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.services.dictionaryDataService.DitionaryDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DitionaryDataService ditionaryDataService;

    UserService() {}

    public List<ProfessorModel> getProfessors()
    {
        List<ProfessorModel> proffesorList = new ArrayList<ProfessorModel>();
        List<Professor> professors = professorRepository.findAll();

        for (Professor item: professors) {
            ProfessorModel proffesor = (ProfessorModel) mapperService.toModel(item);
            proffesorList.add(proffesor);
        }
        return proffesorList;
    }
    public ProfessorModel getProfessor(int id)
    {
        Professor entity = professorRepository.findOne((long) id);
        ProfessorModel proffesor = (ProfessorModel) mapperService.toModel(entity);
        return proffesor;
    }
    public List<ProfessorModel> getProfessors(DepartmentModel department)
    {
        List<ProfessorModel> proffesorList = getProfessors();
        List<Professor> professors = professorRepository.findAllByDepartmentId(department.getId());
        for (Professor item: professors) {
            ProfessorModel proffesor = (ProfessorModel) mapperService.toModel(item);
            proffesorList.add(proffesor);
        }
        return proffesorList;
    }

    public StudentModel getStudent(int id)
    {
        Student entity = studentRepository.findOne((long) id);
        StudentModel student = (StudentModel) mapperService.toModel(entity);
        return student;
    }

    public void setUserGroup(int userId, int groupId)
    {
        Student entity = studentRepository.findOne((long) userId);
        GroupModel group = ditionaryDataService.getGroup(groupId);
        entity.setGroup((Group) mapperService.toEntity(group));
        studentRepository.save(entity);
    }

    public void setProfessorDepartment(int userId, int departmentId)
    {
        Professor entity = professorRepository.findOne((long) userId);
        DepartmentModel department = ditionaryDataService.getDepartment(departmentId);
        entity.setDepartment((Department) mapperService.toEntity(department));
        professorRepository.save(entity);
    }

    public int AddUser(UserCreateModel user)
    {
        // Если роли нет назначаем роль пользователя
        if(user.getRoles().size() == 0){
            List<RoleModel> roles = new ArrayList<RoleModel>();
            RoleModel r = ditionaryDataService.getRole(RoleType.USER);
            roles.add(r);
            user.setRoles(roles);
        }
        // Получаем сохраненного
        User result = userRepository.save((User) mapperService.toEntity(user));
        // Получаем список ролей
        List<String> role = new ArrayList<Role>(result.getRoles()).stream().map(Role::getRoleName)
                .collect(Collectors.toList());

        if (role.contains(RoleType.PROFESSOR.toString()))
        {
            Professor professor = new Professor();
            professor.setUser(result);
            professorRepository.save(professor);
        }
        if (role.contains(RoleType.STUDENT.toString()))
        {
            Student student = new Student();
            student.setUser(result);
            studentRepository.save(student);
        }
        return (int) result.getId();
    }

    public List<UserModel> getUserByFio(String fio)
    {
        Iterable<User> allUsers = userRepository.findAll();
        List<UserModel> result = new ArrayList<UserModel>();
        for (User item : allUsers) {
            if(item.getUserFIO().toLowerCase().contains(fio.toLowerCase()))
            {
                result.add((UserModel) mapperService.toModel(item));
            }
        }
        return result;
    }

    public UserModel getUserByUsername(String username)
    {
        User user = userRepository.findByUsername(username);
        return (UserModel) mapperService.toModel(user);
    }

}
