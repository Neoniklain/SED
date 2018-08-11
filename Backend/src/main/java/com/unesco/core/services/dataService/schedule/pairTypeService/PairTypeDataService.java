package com.unesco.core.services.dataService.schedule.pairTypeService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.PairTypeDTO;
import com.unesco.core.entities.schedule.PairTypeEntity;
import com.unesco.core.repositories.PairTypeRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PairTypeDataService implements IPairTypeDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PairTypeRepository pairTypeRepository;

    public List<PairTypeDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) pairTypeRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<PairTypeEntity> entitys = pairTypeRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PairTypeDTO> result = new ArrayList<PairTypeDTO>();
        for (PairTypeEntity e: entitys) {
            result.add((PairTypeDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<PairTypeDTO> getAll()
    {
        List<PairTypeDTO> modelList = new ArrayList<>();
        Iterable<PairTypeEntity> entityList = pairTypeRepository.findAll();
        for (PairTypeEntity item: entityList) {
            PairTypeDTO model = (PairTypeDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairTypeDTO get(long id)
    {
        PairTypeEntity entity = pairTypeRepository.findOne(id);
        PairTypeDTO model = (PairTypeDTO) mapperService.toDto(entity);
        return model;
    }

    public PairTypeDTO getByType(String name)
    {
        PairTypeEntity entity = pairTypeRepository.findByType(name);
        PairTypeDTO model = (PairTypeDTO) mapperService.toDto(entity);
        return model;
    }

    public void delete(long id)
    {
        pairTypeRepository.delete(id);
    }

    public PairTypeDTO save(PairTypeDTO pairType)
    {
        PairTypeEntity entity = (PairTypeEntity) mapperService.toEntity(pairType);
        PairTypeEntity model = pairTypeRepository.save(entity);
        pairType = (PairTypeDTO) mapperService.toDto(model);
        return pairType;
    }

}
