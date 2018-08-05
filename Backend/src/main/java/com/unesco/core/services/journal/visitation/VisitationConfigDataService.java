package com.unesco.core.services.journal.visitation;

import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.entities.journal.VisitationConfigEntity;
import com.unesco.core.repositories.VisitationConfigRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitationConfigDataService implements IVisitationConfigDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private VisitationConfigRepository visitationConfigRepository;

    public List<VisitationConfigDTO> GetAll()
    {
        List<VisitationConfigDTO> modelList = new ArrayList<>();
        Iterable<VisitationConfigEntity> entityList = visitationConfigRepository.findAll();
        for (VisitationConfigEntity item: entityList) {
            VisitationConfigDTO model = (VisitationConfigDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public VisitationConfigDTO Get(long id)
    {
        VisitationConfigEntity entity = visitationConfigRepository.findOne(id);
        VisitationConfigDTO model = (VisitationConfigDTO) mapperService.toDto(entity);
        return model;
    }

    public VisitationConfigDTO GetByLesson(long lessonId) {
        VisitationConfigEntity entity = visitationConfigRepository.findByLessonId(lessonId);
        VisitationConfigDTO model = (VisitationConfigDTO) mapperService.toDto(entity);
        return model;
    }

    public void Delete(long id)
    {
        visitationConfigRepository.delete(id);
    }

    public VisitationConfigDTO Save(VisitationConfigDTO model)
    {
        VisitationConfigEntity entity = (VisitationConfigEntity) mapperService.toEntity(model);
        entity = visitationConfigRepository.save(entity);
        model = (VisitationConfigDTO) mapperService.toDto(entity);
        return model;
    }

}
