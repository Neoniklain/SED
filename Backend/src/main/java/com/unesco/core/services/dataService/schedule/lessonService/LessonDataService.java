package com.unesco.core.services.dataService.schedule.lessonService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.repositories.LessonRepository;
import com.unesco.core.services.dataService.journal.visitation.VisitationConfigDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonDataService implements ILessonDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private VisitationConfigDataService visitationConfigDataService;

    public List<LessonDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) lessonRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<LessonEntity> entitys = lessonRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<LessonDTO> result = new ArrayList<LessonDTO>();
        for (LessonEntity e: entitys) {
            result.add((LessonDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<LessonDTO> getAll()
    {
        List<LessonDTO> modelList = new ArrayList<>();
        Iterable<LessonEntity> entityList = lessonRepository.findAll();
        for (LessonEntity item: entityList) {
            LessonDTO model = (LessonDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonDTO get(long id)
    {
        LessonEntity entity = lessonRepository.findOne(id);
        LessonDTO model = (LessonDTO) mapperService.toDto(entity);
        return model;
    }

    public LessonDTO getDisciplineIdAndGroupIdAndProfessorId(long disciplineId, long groupId, long professorId)
    {
        LessonEntity entity = lessonRepository.findByDisciplineIdAndGroupIdAndProfessorId(disciplineId, groupId, professorId);
        LessonDTO model = (LessonDTO) mapperService.toDto(entity);
        return model;
    }

    public List<LessonDTO> getByProfessorId(long professorId)
    {
        List<LessonDTO> modelList = new ArrayList<>();
        List<LessonEntity> entityList = lessonRepository.findByProfessorId(professorId);
        for (LessonEntity item: entityList) {
            LessonDTO model = (LessonDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public ResponseStatusDTO<LessonDTO> delete(long id)
    {
        ResponseStatusDTO<LessonDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        VisitationConfigDTO byLesson = visitationConfigDataService.getByLesson(id);
        if (byLesson != null)
            visitationConfigDataService.delete(byLesson.getId());

        try {
            lessonRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<LessonDTO> save(LessonDTO lesson)
    {
        LessonEntity entity = (LessonEntity) mapperService.toEntity(lesson);
        ResponseStatusDTO<LessonDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        LessonEntity model;
        try {
            model = lessonRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        result.setData((LessonDTO) mapperService.toDto(model));
        return result;
    }

}