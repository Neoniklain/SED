package com.unesco.core.services.dataService.account.studentService;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.StudentJournalDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.entities.account.StudentEntity;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.entities.schedule.StudentLessonSubgroupEntity;
import com.unesco.core.repositories.account.StudentRepository;
import com.unesco.core.repositories.schedule.StudentLessonSubgroupRepository;
import com.unesco.core.services.dataService.account.userService.IUserDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @Autowired
    private StudentLessonSubgroupRepository studentLessonSubgroupRepository;

    public PageResultDTO<StudentDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) studentRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<StudentEntity> entitys = studentRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<StudentDTO> result = new ArrayList<StudentDTO>();
        for (StudentEntity e: entitys) {
            result.add((StudentDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, studentRepository.count());
    }

    public List<StudentDTO> getAll()
    {
        List<StudentDTO> modelList = new ArrayList<>();
        Iterable<StudentEntity> entityList = studentRepository.findAll();
        for (StudentEntity item: entityList) {
            StudentDTO model = (StudentDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public StudentDTO get(long id)
    {
        StudentEntity entity = studentRepository.findOne(id);
        StudentDTO model = (StudentDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<StudentDTO> delete(long id)
    {
        ResponseStatusDTO<StudentDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            studentRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public List<StudentDTO> getByGroup(long groupId) {
        List<StudentDTO> modelList = new ArrayList<>();
        Iterable<StudentEntity> entityList = studentRepository.findAllByGroupId(groupId);
        for (StudentEntity item: entityList) {
            StudentDTO model = (StudentDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<StudentJournalDTO> getByGroupAndLesson(long groupId, long lessonId) {

        List<StudentJournalDTO> modelList = new ArrayList<>();
        Iterable<StudentEntity> entityList = studentRepository.findAllByGroupId(groupId);
        List<StudentLessonSubgroupEntity> all = studentLessonSubgroupRepository.findByLessonId(lessonId);

        for (StudentEntity item: entityList) {
            StudentDTO model = (StudentDTO) mapperService.toDto(item);
            StudentJournalDTO student = new StudentJournalDTO();
            student.setStudent(model);
            student.setSubgroup(0);
            if(all.stream().anyMatch(x -> x.getStudent().getId() == item.getId())) {
                StudentLessonSubgroupEntity studentLessonSubgroup = all.stream().filter(x -> x.getStudent().getId() == item.getId()).findFirst().get();
                student.setSubgroup(studentLessonSubgroup.getSubgroup());
            }
            modelList.add(student);
        }

        return modelList;
    }

    public ResponseStatusDTO<StudentDTO> save(StudentDTO student)
    {
        StudentEntity entity = (StudentEntity) mapperService.toEntity(student);
        UserEntity userEntity = (UserEntity) mapperService.toEntity(userDataService.getByUsername(entity.getUser().getUsername()));
        entity.setUser(userEntity);
        ResponseStatusDTO<StudentDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        StudentEntity model;
        try {
            model = studentRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((StudentDTO) mapperService.toDto(model));
        return result;
    }

    public StudentDTO getByUser(long userId) {
        StudentEntity entity = studentRepository.findByUserId(userId);
        StudentDTO model = (StudentDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<StudentDTO> saveStudentsSubgroup(List<StudentJournalDTO> studentsJournal, LessonDTO lesson) {

        for (StudentJournalDTO studentJournal: studentsJournal) {
            StudentLessonSubgroupEntity studentLesson = new StudentLessonSubgroupEntity();
            studentLesson.setLesson((LessonEntity) mapperService.toEntity(lesson));
            studentLesson.setStudent((StudentEntity) mapperService.toEntity(studentJournal.getStudent()));
            studentLesson.setSubgroup(studentJournal.getSubgroup());

            StudentLessonSubgroupEntity findStudentAndLesson = studentLessonSubgroupRepository.findByStudentIdAndLessonId(studentJournal.getStudent().getId(), lesson.getId());

            if(studentJournal.getSubgroup() != 0) {
                try {
                    if(findStudentAndLesson == null) {
                        studentLessonSubgroupRepository.save(studentLesson);
                    } else {
                        findStudentAndLesson.setSubgroup(studentJournal.getSubgroup());
                        studentLessonSubgroupRepository.save(findStudentAndLesson);
                    }
                } catch (Exception e) {
                    ResponseStatusDTO result = new ResponseStatusDTO();
                    result.setStatus(StatusTypes.ERROR);
                    result.addErrors(e.getMessage());
                    return result;
                }
            } else {
                try {
                    if(findStudentAndLesson != null) {
                        studentLessonSubgroupRepository.delete(findStudentAndLesson.getId());
                    }
                } catch (Exception e) {
                    ResponseStatusDTO result = new ResponseStatusDTO();
                    result.setStatus(StatusTypes.ERROR);
                    result.addErrors(e.getMessage());
                    return result;
                }
            }
        }

        ResponseStatusDTO<StudentDTO> result = new ResponseStatusDTO<StudentDTO>();
        result.setStatus(StatusTypes.OK);
        result.addMessage("Подгруппы сохранены");

        return result;
    }

}
