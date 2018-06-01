package com.unesco.core.services.schedule.groupService;

import com.unesco.core.entities.schedule.Group;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.plan.GroupRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupDataService implements IGroupDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private GroupRepository groupRepository;

    public List<GroupModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) groupRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Group> entitys = groupRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<GroupModel> result = new ArrayList<GroupModel>();
        for (Group e: entitys) {
            result.add((GroupModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<GroupModel> GetAll()
    {
        List<GroupModel> modelList = new ArrayList<>();
        Iterable<Group> entityList = groupRepository.findAll();
        for (Group item: entityList) {
            GroupModel model = (GroupModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public GroupModel Get(long id)
    {
        Group entity = groupRepository.findOne(id);
        GroupModel model = (GroupModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        groupRepository.delete(id);
    }

    public GroupModel Save(GroupModel group)
    {
        Group entity = (Group) mapperService.toEntity(group);
        Group model = groupRepository.save(entity);
        group = (GroupModel) mapperService.toModel(model);
        return group;
    }
}
