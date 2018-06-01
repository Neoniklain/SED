package com.unesco.core.services.plan.lessonTypeService;

import com.unesco.core.entities.plan.LessonType;
import com.unesco.core.models.plan.LessonTypeModel;
import com.unesco.core.repositories.plan.LessonTypeRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonTypeDataService implements ILessonTypeDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private LessonTypeRepository lessonTypeRepository;

    public List<LessonTypeModel> GetAll()
    {
        List<LessonTypeModel> modelList = new ArrayList<>();
        Iterable<LessonType> entityList = lessonTypeRepository.findAll();
        for (LessonType item: entityList) {
            LessonTypeModel model = (LessonTypeModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonTypeModel Get(long id)
    {
        LessonType entity = lessonTypeRepository.findOne(id);
        LessonTypeModel model = (LessonTypeModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        lessonTypeRepository.delete(id);
    }

    public LessonTypeModel Save(LessonTypeModel lessonType)
    {
        LessonType entity = (LessonType) mapperService.toEntity(lessonType);
        LessonType model = lessonTypeRepository.save(entity);
        lessonType = (LessonTypeModel) mapperService.toModel(model);
        return lessonType;
    }
}
