package com.unesco.core.services.dataService.plan.planService;

import com.unesco.core.entities.plan.PlanEntity;
import com.unesco.core.dto.plan.PlanDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.repositories.plan.PlanRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanDataService implements IPlanDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PlanRepository planRepository;

    public List<PlanDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) planRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<PlanEntity> entitys = planRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PlanDTO> result = new ArrayList<PlanDTO>();
        for (PlanEntity e: entitys) {
            result.add((PlanDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<PlanDTO> getAll()
    {
        List<PlanDTO> modelList = new ArrayList<>();
        Iterable<PlanEntity> entityList = planRepository.findAll();
        for (PlanEntity item: entityList) {
            PlanDTO model = (PlanDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PlanDTO get(long id)
    {
        PlanEntity entity = planRepository.findOne(id);
        PlanDTO model = (PlanDTO) mapperService.toDto(entity);
        return model;
    }

    public void delete(long id)
    {
        planRepository.delete(id);
    }

    public PlanDTO save(PlanDTO plan)
    {
        PlanEntity entity = (PlanEntity) mapperService.toEntity(plan);
        PlanEntity model = planRepository.save(entity);
        plan = (PlanDTO) mapperService.toDto(model);
        return plan;
    }
}
