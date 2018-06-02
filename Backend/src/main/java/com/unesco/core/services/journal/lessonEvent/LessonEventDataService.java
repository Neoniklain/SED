package com.unesco.core.services.journal.lessonEvent;

import com.unesco.core.entities.account.Professor;
import com.unesco.core.entities.journal.LessonEvent;
import com.unesco.core.models.journal.LessonEventModel;
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

    public List<LessonEventModel> GetAll()
    {
        List<LessonEventModel> modelList = new ArrayList<>();
        Iterable<LessonEvent> entityList = lessonEventRepository.findAll();
        for (LessonEvent item: entityList) {
            LessonEventModel model = (LessonEventModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonEventModel Get(long id)
    {
        LessonEvent entity = lessonEventRepository.findOne(id);
        LessonEventModel model = (LessonEventModel) mapperService.toModel(entity);
        return model;
    }

    public List<LessonEventModel> GetByLesson(long lessonId)
    {

        List<LessonEventModel> modelList = new ArrayList<>();
        Iterable<LessonEvent> entityList = lessonEventRepository.findByLessonId(lessonId);
        for (LessonEvent item: entityList) {
            LessonEventModel model = (LessonEventModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public void Delete(long id)
    {
        lessonEventRepository.delete(id);
    }

    public LessonEventModel Save(LessonEventModel lessonEvent)
    {
        LessonEvent entity = (LessonEvent) mapperService.toEntity(lessonEvent);
        entity = lessonEventRepository.save(entity);
        lessonEvent = (LessonEventModel) mapperService.toModel(entity);
        return lessonEvent;
    }
}
