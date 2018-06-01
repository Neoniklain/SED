package com.unesco.core.services.journal.lessonEvent;

import com.unesco.core.entities.journal.PairEvent;
import com.unesco.core.models.journal.PairEventModel;
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

    public List<PairEventModel> GetAll()
    {
        List<PairEventModel> modelList = new ArrayList<>();
        Iterable<PairEvent> entityList = lessonEventRepository.findAll();
        for (PairEvent item: entityList) {
            PairEventModel model = (PairEventModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairEventModel Get(long id)
    {
        PairEvent entity = lessonEventRepository.findOne(id);
        PairEventModel model = (PairEventModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        lessonEventRepository.delete(id);
    }

    public PairEventModel Save(PairEventModel lessonEvent)
    {
        PairEvent entity = (PairEvent) mapperService.toEntity(lessonEvent);
        entity = lessonEventRepository.save(entity);
        lessonEvent = (PairEventModel) mapperService.toModel(entity);
        return lessonEvent;
    }
}
