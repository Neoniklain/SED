package com.unesco.core.services.dataService.plan.educationPeriodService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.plan.EducationPeriodDTO;
import com.unesco.core.entities.plan.EducationPeriodEntity;
import com.unesco.core.repositories.plan.EducationPeriodRepository;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.utils.DateHelper;
import com.unesco.core.utils.StartEndDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationPeriodService implements IEducationPeriodService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private EducationPeriodRepository educationPeriodRepository;

    public List<EducationPeriodDTO> getAll() {
        List<EducationPeriodDTO> modelList = new ArrayList<>();
        Iterable<EducationPeriodEntity> entityList = educationPeriodRepository.findAll();
        for (EducationPeriodEntity item : entityList) {
            EducationPeriodDTO model = (EducationPeriodDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public EducationPeriodDTO get(long id) {
        EducationPeriodEntity entity = educationPeriodRepository.findOne(id);
        EducationPeriodDTO model = (EducationPeriodDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<EducationPeriodDTO> delete(long id) {
        ResponseStatusDTO<EducationPeriodDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            educationPeriodRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if (e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<EducationPeriodDTO> save(EducationPeriodDTO educationPeriodDto) {
        EducationPeriodEntity entity = (EducationPeriodEntity) mapperService.toEntity(educationPeriodDto);
        ResponseStatusDTO<EducationPeriodDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = educationPeriodRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((EducationPeriodDTO) mapperService.toDto(entity));
        return result;
    }

    public EducationPeriodDTO getEducationPeriodForYearAndSemester(int semester, int year) {
        StartEndDate result = DateHelper.getPeriodForYearAndSemester(semester, year);
        EducationPeriodEntity entity = educationPeriodRepository.findByStartDateBetween(result.startDate, result.endDate);
        EducationPeriodDTO model = (EducationPeriodDTO) mapperService.toDto(entity);
        return model;
    }

    public EducationPeriodDTO getEducationPeriodForGroup(long specialityId, int semester, int year) {
        StartEndDate result = DateHelper.getPeriodForYearAndSemester(semester, year);
        EducationPeriodEntity entity = educationPeriodRepository.findBySpecialityIdAndStartDateBetween(specialityId, result.startDate, result.endDate);
        EducationPeriodDTO model = (EducationPeriodDTO) mapperService.toDto(entity);
        return model;
    }
}
