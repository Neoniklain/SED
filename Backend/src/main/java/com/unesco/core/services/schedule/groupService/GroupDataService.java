package com.unesco.core.services.schedule.groupService;

import com.unesco.core.entities.schedule.GroupEntity;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.shedule.GroupDTO;
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

    public List<GroupDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) groupRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<GroupEntity> entitys = groupRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<GroupDTO> result = new ArrayList<GroupDTO>();
        for (GroupEntity e: entitys) {
            result.add((GroupDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<GroupDTO> GetAll()
    {
        List<GroupDTO> modelList = new ArrayList<>();
        Iterable<GroupEntity> entityList = groupRepository.findAll();
        for (GroupEntity item: entityList) {
            GroupDTO model = (GroupDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public GroupDTO Get(long id)
    {
        GroupEntity entity = groupRepository.findOne(id);
        GroupDTO model = (GroupDTO) mapperService.toModel(entity);
        return model;
    }

    public GroupDTO GetByName(String name)
    {
        GroupEntity entity = groupRepository.findByName(name);
        GroupDTO model = (GroupDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        groupRepository.delete(id);
    }

    public GroupDTO Save(GroupDTO group)
    {
        GroupEntity entity = (GroupEntity) mapperService.toEntity(group);
        GroupEntity model = groupRepository.save(entity);
        group = (GroupDTO) mapperService.toModel(model);
        return group;
    }
}
