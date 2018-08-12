package com.unesco.core.services.dataService.schedule.instituteService;

import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.entities.schedule.InstituteEntity;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.repositories.plan.InstituteRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstituteDataService implements IInstituteDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private InstituteRepository instituteRepository;

    public PageResultDTO<InstituteDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) instituteRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<InstituteEntity> entitys = instituteRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<InstituteDTO> result = new ArrayList<InstituteDTO>();
        for (InstituteEntity e: entitys) {
            result.add((InstituteDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, instituteRepository.count());
    }

    public List<InstituteDTO> getAll()
    {
        List<InstituteDTO> modelList = new ArrayList<>();
        Iterable<InstituteEntity> entityList = instituteRepository.findAll();
        for (InstituteEntity item: entityList) {
            InstituteDTO model = (InstituteDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public InstituteDTO get(long id)
    {
        InstituteEntity entity = instituteRepository.findOne(id);
        InstituteDTO model = (InstituteDTO) mapperService.toDto(entity);
        return model;
    }

    public InstituteDTO getByName(String name)
    {
        InstituteEntity entity = instituteRepository.findByName(name);
        InstituteDTO model = (InstituteDTO) mapperService.toDto(entity);
        return model;
    }

    public void delete(long id)
    {
        instituteRepository.delete(id);
    }

    public InstituteDTO save(InstituteDTO institute)
    {
        InstituteEntity entity = (InstituteEntity) mapperService.toEntity(institute);
        InstituteEntity model = instituteRepository.save(entity);
        institute = (InstituteDTO) mapperService.toDto(model);
        return institute;
    }
}
