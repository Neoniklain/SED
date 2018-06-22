package com.unesco.core.services.account.studentService;

import com.unesco.core.entities.account.StudentEntity;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
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

    public List<StudentDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) studentRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<StudentEntity> entitys = studentRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<StudentDTO> result = new ArrayList<StudentDTO>();
        for (StudentEntity e: entitys) {
            result.add((StudentDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<StudentDTO> GetAll()
    {
        List<StudentDTO> modelList = new ArrayList<>();
        Iterable<StudentEntity> entityList = studentRepository.findAll();
        for (StudentEntity item: entityList) {
            StudentDTO model = (StudentDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public StudentDTO Get(long id)
    {
        StudentEntity entity = studentRepository.findOne(id);
        StudentDTO model = (StudentDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        studentRepository.delete(id);
    }

    public List<StudentDTO> GetByGroup(long groupId) {
        List<StudentDTO> modelList = new ArrayList<>();
        Iterable<StudentEntity> entityList = studentRepository.findAllByGroupId(groupId);
        for (StudentEntity item: entityList) {
            StudentDTO model = (StudentDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }
    public StudentDTO Save(StudentDTO student)
    {
        StudentEntity entity = (StudentEntity) mapperService.toEntity(student);
        UserEntity userEntity = (UserEntity) mapperService.toEntity(userDataService.GetByUsername(entity.getUserEntity().getUsername()));
        entity.setUserEntity(userEntity);
        StudentEntity model = studentRepository.save(entity);
        student = (StudentDTO) mapperService.toModel(model);
        return student;
    }

    public StudentDTO GetByUser(long userId) {
        StudentEntity entity = studentRepository.findByUserId(userId);
        StudentDTO model = (StudentDTO) mapperService.toModel(entity);
        return model;
    }
}
