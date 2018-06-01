package com.unesco.core.services.plan.planService;

import com.unesco.core.entities.plan.Plan;
import com.unesco.core.models.plan.PlanModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.plan.PlanRepository;
import com.unesco.core.services.mapperService.IMapperService;
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

    public List<PlanModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) planRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Plan> entitys = planRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PlanModel> result = new ArrayList<PlanModel>();
        for (Plan e: entitys) {
            result.add((PlanModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<PlanModel> GetAll()
    {
        List<PlanModel> modelList = new ArrayList<>();
        Iterable<Plan> entityList = planRepository.findAll();
        for (Plan item: entityList) {
            PlanModel model = (PlanModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PlanModel Get(long id)
    {
        Plan entity = planRepository.findOne(id);
        PlanModel model = (PlanModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        planRepository.delete(id);
    }

    public PlanModel Save(PlanModel plan)
    {
        Plan entity = (Plan) mapperService.toEntity(plan);
        Plan model = planRepository.save(entity);
        plan = (PlanModel) mapperService.toModel(model);
        return plan;
    }
}
