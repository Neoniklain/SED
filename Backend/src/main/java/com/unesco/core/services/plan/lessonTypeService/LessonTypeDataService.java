package com.unesco.core.services.plan.lessonTypeService;

import com.unesco.core.entities.plan.LessonTypeEntity;
import com.unesco.core.models.plan.LessonTypeDTO;
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

    public List<LessonTypeDTO> GetAll()
    {
        List<LessonTypeDTO> modelList = new ArrayList<>();
        Iterable<LessonTypeEntity> entityList = lessonTypeRepository.findAll();
        for (LessonTypeEntity item: entityList) {
            LessonTypeDTO model = (LessonTypeDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonTypeDTO Get(long id)
    {
        LessonTypeEntity entity = lessonTypeRepository.findOne(id);
        LessonTypeDTO model = (LessonTypeDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        lessonTypeRepository.delete(id);
    }

    public LessonTypeDTO Save(LessonTypeDTO lessonType)
    {
        LessonTypeEntity entity = (LessonTypeEntity) mapperService.toEntity(lessonType);
        LessonTypeEntity model = lessonTypeRepository.save(entity);
        lessonType = (LessonTypeDTO) mapperService.toModel(model);
        return lessonType;
    }
}
