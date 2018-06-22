package com.unesco.core.services.account.roleService;

import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.models.account.RoleDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleDataService implements IRoleDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) roleRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<RoleEntity> entitys = roleRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<RoleDTO> result = new ArrayList<RoleDTO>();
        for (RoleEntity e: entitys) {
            result.add((RoleDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<RoleDTO> GetAll()
    {
        List<RoleDTO> modelList = new ArrayList<>();
        Iterable<RoleEntity> entityList = roleRepository.findAll();
        for (RoleEntity item: entityList) {
            RoleDTO model = (RoleDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public RoleDTO Get(long id)
    {
        RoleEntity entity = roleRepository.findOne(id);
        RoleDTO model = (RoleDTO) mapperService.toModel(entity);
        return model;
    }

    public RoleDTO GetByName(String name)
    {
        RoleEntity entity = roleRepository.findByRole(name);
        RoleDTO model = (RoleDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        roleRepository.delete(id);
    }

    public RoleDTO Save(RoleDTO role)
    {
        RoleEntity entity = (RoleEntity) mapperService.toEntity(role);
        RoleEntity model = roleRepository.save(entity);
        role = (RoleDTO) mapperService.toModel(model);
        return role;
    }

}
