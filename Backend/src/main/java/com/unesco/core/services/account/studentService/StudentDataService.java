package com.unesco.core.services.account.studentService;

import com.unesco.core.entities.account.Student;
import com.unesco.core.entities.account.User;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.account.StudentRepository;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDataService implements IStudentDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IUserDataService userDataService;

    public List<StudentModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) studentRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Student> entitys = studentRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<StudentModel> result = new ArrayList<StudentModel>();
        for (Student e: entitys) {
            result.add((StudentModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<StudentModel> GetAll()
    {
        List<StudentModel> modelList = new ArrayList<>();
        Iterable<Student> entityList = studentRepository.findAll();
        for (Student item: entityList) {
            StudentModel model = (StudentModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public StudentModel Get(long id)
    {
        Student entity = studentRepository.findOne(id);
        StudentModel model = (StudentModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        studentRepository.delete(id);
    }

    public List<StudentModel> GetByGroup(long groupId) {
        List<StudentModel> modelList = new ArrayList<>();
        Iterable<Student> entityList = studentRepository.findAllByGroupId(groupId);
        for (Student item: entityList) {
            StudentModel model = (StudentModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }
    public StudentModel Save(StudentModel student)
    {
        Student entity = (Student) mapperService.toEntity(student);
        User user = (User) mapperService.toEntity(userDataService.GetByUsername(entity.getUser().getUsername()));
        entity.setUser(user);
        Student model = studentRepository.save(entity);
        student = (StudentModel) mapperService.toModel(model);
        return student;
    }

    public StudentModel GetByUser(long userId) {
        Student entity = studentRepository.findByUserId(userId);
        StudentModel model = (StudentModel) mapperService.toModel(entity);
        return model;
    }
}
