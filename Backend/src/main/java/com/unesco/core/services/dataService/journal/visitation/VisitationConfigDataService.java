package com.unesco.core.services.dataService.journal.visitation;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.entities.journal.VisitationConfigEntity;
import com.unesco.core.repositories.VisitationConfigRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitationConfigDataService implements IVisitationConfigDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private VisitationConfigRepository visitationConfigRepository;

    public List<VisitationConfigDTO> getAll()
    {
        List<VisitationConfigDTO> modelList = new ArrayList<>();
        Iterable<VisitationConfigEntity> entityList = visitationConfigRepository.findAll();
        for (VisitationConfigEntity item: entityList) {
            VisitationConfigDTO model = (VisitationConfigDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public VisitationConfigDTO get(long id)
    {
        VisitationConfigEntity entity = visitationConfigRepository.findOne(id);
        VisitationConfigDTO model = (VisitationConfigDTO) mapperService.toDto(entity);
        return model;
    }

    public VisitationConfigDTO getByLesson(long lessonId) {
        VisitationConfigEntity entity = visitationConfigRepository.findByLessonId(lessonId);
        VisitationConfigDTO model = (VisitationConfigDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<VisitationConfigDTO> delete(long id)
    {
        ResponseStatusDTO<VisitationConfigDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            visitationConfigRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<VisitationConfigDTO> save(VisitationConfigDTO model)
    {
        VisitationConfigEntity entity = (VisitationConfigEntity) mapperService.toEntity(model);
        ResponseStatusDTO<VisitationConfigDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = visitationConfigRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((VisitationConfigDTO) mapperService.toDto(entity));
        return result;
    }

}
