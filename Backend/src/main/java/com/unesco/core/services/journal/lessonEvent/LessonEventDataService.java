package com.unesco.core.services.journal.lessonEvent;

import com.unesco.core.entities.journal.LessonEventEntity;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.repositories.journal.LessonEventRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonEventDataService implements ILessonEventDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private LessonEventRepository lessonEventRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public List<LessonEventDTO> GetAll()
    {
        List<LessonEventDTO> modelList = new ArrayList<>();
        Iterable<LessonEventEntity> entityList = lessonEventRepository.findAll();
        for (LessonEventEntity item: entityList) {
            LessonEventDTO model = (LessonEventDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonEventDTO Get(long id)
    {
        LessonEventEntity entity = lessonEventRepository.findOne(id);
        LessonEventDTO model = (LessonEventDTO) mapperService.toDto(entity);
        return model;
    }

    public List<LessonEventDTO> GetByLesson(long lessonId)
    {

        List<LessonEventDTO> modelList = new ArrayList<>();
        Iterable<LessonEventEntity> entityList = lessonEventRepository.findByLessonEntityId(lessonId);
        for (LessonEventEntity item: entityList) {
            LessonEventDTO model = (LessonEventDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public void Delete(long id)
    {
        lessonEventRepository.delete(id);
    }

    public LessonEventDTO Save(LessonEventDTO lessonEvent)
    {
        LessonEventEntity entity = (LessonEventEntity) mapperService.toEntity(lessonEvent);
        entity = lessonEventRepository.save(entity);
        lessonEvent = (LessonEventDTO) mapperService.toDto(entity);
        return lessonEvent;
    }
}
