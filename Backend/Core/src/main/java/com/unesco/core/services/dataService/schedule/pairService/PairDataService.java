package com.unesco.core.services.dataService.schedule.pairService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.entities.plan.EducationPeriodEntity;
import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.entities.schedule.PairEntity;
import com.unesco.core.repositories.plan.EducationPeriodRepository;
import com.unesco.core.repositories.schedule.PairRepository;
import com.unesco.core.services.dataService.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.utils.DateHelper;
import com.unesco.core.utils.StartEndDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PairDataService implements IPairDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PairRepository pairRepository;
    @Autowired
    private ILessonDataService lessonDataService;
    @Autowired
    private EducationPeriodRepository educationPeriodRepository;

    public List<PairDTO> getAll() {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findAll();
        for (PairEntity item : entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByProfessor(long professorId, int semester, int year) {
        List<PairDTO> modelList = new ArrayList<>();
        EducationPeriodEntity period = getEducationPeriodForYearAndSemester(semester, year);
        if (period == null)
            return new ArrayList<>();

        Iterable<PairEntity> entityList = pairRepository.findPairsByProfessorId(professorId, period.getId());
        for (PairEntity item : entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByDepartament(long departmentId, int semester, int year) {
        List<PairDTO> modelList = new ArrayList<>();

        EducationPeriodEntity period = getEducationPeriodForYearAndSemester(semester, year);
        if (period == null)
            return new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByDepartmentId(departmentId, period.getId());
        for (PairEntity item : entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByGroup(long groupId, int semester, int year) {
        List<PairDTO> modelList = new ArrayList<>();

        EducationPeriodEntity period = getEducationPeriodForYearAndSemester(semester, year);
        if (period == null)
            return new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByGroupId(groupId, period.getId());
        for (PairEntity item : entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByLesson(long lessonId, int semester, int year) {
        EducationPeriodEntity period = getEducationPeriodForYearAndSemester(semester, year);
        if (period == null)
            return new ArrayList<>();

        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByLessonId(lessonId, period.getId());
        for (PairEntity item : entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairDTO get(long id) {
        PairEntity entity = pairRepository.findOne(id);
        PairDTO model = (PairDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<PairDTO> delete(long id) {
        ResponseStatusDTO<PairDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        PairDTO pairDTO = get(id);
        try {
            pairRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if (e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            else
                result.addErrors("Удаление не удалось");
            return result;
        }
        lessonDataService.delete(pairDTO.getLesson().getId());
        return result;
    }

    public ResponseStatusDTO<PairDTO> save(PairDTO pair) {
        PairEntity entity = (PairEntity) mapperService.toEntity(pair);
        ResponseStatusDTO<PairDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);

        LessonDTO findLesson = lessonDataService.getDisciplineIdAndGroupIdAndProfessorId(
                entity.getLesson().getDiscipline().getId(),
                entity.getLesson().getGroup().getId(),
                entity.getLesson().getProfessor().getId());

        if (findLesson == null) {
            LessonDTO lesson = pair.getLesson();
            lesson.setId(0);
            pair.setLesson(lesson);
            ResponseStatusDTO<LessonDTO> saveLessonStatus = lessonDataService.save(pair.getLesson());
            if (saveLessonStatus.getStatus() == StatusTypes.ERROR) {
                result.setMessage(saveLessonStatus.getMessage());
                return result;
            }
            findLesson = saveLessonStatus.getData();
        }

        entity.setLesson((LessonEntity) mapperService.toEntity(findLesson));

        try {
            entity = pairRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((PairDTO) mapperService.toDto(entity));
        return result;
    }

    private EducationPeriodEntity getEducationPeriodForYearAndSemester(int semester, int year) {
        StartEndDate result = DateHelper.getPeriodForYearAndSemester(semester, year);
        return educationPeriodRepository.findByStartDateBetween(result.startDate, result.endDate);
    }

}
