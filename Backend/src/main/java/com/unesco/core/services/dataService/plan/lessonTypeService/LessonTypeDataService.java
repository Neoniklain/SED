package com.unesco.core.services.dataService.plan.lessonTypeService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.plan.LessonTypeDTO;
import com.unesco.core.entities.plan.LessonTypeEntity;
import com.unesco.core.repositories.plan.LessonTypeRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonTypeDataService implements ILessonTypeDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private LessonTypeRepository lessonTypeRepository;

    public List<LessonTypeDTO> getAll()
    {
        List<LessonTypeDTO> modelList = new ArrayList<>();
        Iterable<LessonTypeEntity> entityList = lessonTypeRepository.findAll();
        for (LessonTypeEntity item: entityList) {
            LessonTypeDTO model = (LessonTypeDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonTypeDTO get(long id)
    {
        LessonTypeEntity entity = lessonTypeRepository.findOne(id);
        LessonTypeDTO model = (LessonTypeDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<LessonTypeDTO> delete(long id)
    {
        ResponseStatusDTO<LessonTypeDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            lessonTypeRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<LessonTypeDTO> save(LessonTypeDTO lessonType)
    {
        LessonTypeEntity entity = (LessonTypeEntity) mapperService.toEntity(lessonType);
        ResponseStatusDTO<LessonTypeDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = lessonTypeRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((LessonTypeDTO) mapperService.toDto(entity));
        return result;
    }
}
