package com.unesco.core.services.dataService.account.accessRightService;

import com.unesco.core.dto.account.AccessRightDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.AccessRightType;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.entities.account.AccessRightEntity;
import com.unesco.core.repositories.account.AccessRightRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessRightDataService implements IAccessRightDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private AccessRightRepository accessRightRepository;

    public List<AccessRightDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) accessRightRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<AccessRightEntity> entitys = accessRightRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<AccessRightDTO> result = new ArrayList<AccessRightDTO>();
        for (AccessRightEntity e: entitys) {
            result.add((AccessRightDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<AccessRightDTO> getAll()
    {
        List<AccessRightDTO> modelList = new ArrayList<>();
        Iterable<AccessRightEntity> entityList = accessRightRepository.findAll();
        for (AccessRightEntity item: entityList) {
            AccessRightDTO model = (AccessRightDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public AccessRightDTO get(long id)
    {
        AccessRightEntity entity = accessRightRepository.findOne(id);
        AccessRightDTO model = (AccessRightDTO) mapperService.toDto(entity);
        return model;
    }

    public AccessRightDTO getByType(AccessRightType type)
    {
        AccessRightEntity entity = accessRightRepository.findByName(type.toString());
        AccessRightDTO model = (AccessRightDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<AccessRightDTO> delete(long id)
    {
        ResponseStatusDTO<AccessRightDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            accessRightRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<AccessRightDTO> save(AccessRightDTO accessRight)
    {
        AccessRightEntity entity = (AccessRightEntity) mapperService.toEntity(accessRight);
        ResponseStatusDTO<AccessRightDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        AccessRightEntity model;
        try {
            model = accessRightRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((AccessRightDTO) mapperService.toDto(model));
        return result;
    }

}
