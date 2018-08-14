package com.unesco.core.services.dataService.schedule.groupService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.entities.schedule.GroupEntity;
import com.unesco.core.repositories.plan.GroupRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public PageResultDTO<GroupDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) groupRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<GroupEntity> entitys = groupRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<GroupDTO> result = new ArrayList<GroupDTO>();
        for (GroupEntity e: entitys) {
            result.add((GroupDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, groupRepository.count());
    }

    public List<GroupDTO> getAll()
    {
        List<GroupDTO> modelList = new ArrayList<>();
        Iterable<GroupEntity> entityList = groupRepository.findAll();
        for (GroupEntity item: entityList) {
            GroupDTO model = (GroupDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public GroupDTO get(long id)
    {
        GroupEntity entity = groupRepository.findOne(id);
        GroupDTO model = (GroupDTO) mapperService.toDto(entity);
        return model;
    }

    public GroupDTO getByName(String name)
    {
        GroupEntity entity = groupRepository.findByName(name);
        GroupDTO model = (GroupDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<GroupDTO> delete(long id)
    {
        ResponseStatusDTO<GroupDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            groupRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<GroupDTO> save(GroupDTO group)
    {
        GroupEntity entity = (GroupEntity) mapperService.toEntity(group);
        ResponseStatusDTO<GroupDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = groupRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((GroupDTO) mapperService.toDto(entity));
        return result;
    }
}
