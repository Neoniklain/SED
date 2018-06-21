package com.unesco.core.services.account.roleService;

import com.unesco.core.entities.account.Role;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.additional.FilterQuery;
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

    public List<RoleModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) roleRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Role> entitys = roleRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<RoleModel> result = new ArrayList<RoleModel>();
        for (Role e: entitys) {
            result.add((RoleModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<RoleModel> GetAll()
    {
        List<RoleModel> modelList = new ArrayList<>();
        Iterable<Role> entityList = roleRepository.findAll();
        for (Role item: entityList) {
            RoleModel model = (RoleModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public RoleModel Get(long id)
    {
        Role entity = roleRepository.findOne(id);
        RoleModel model = (RoleModel) mapperService.toModel(entity);
        return model;
    }

    public RoleModel GetByName(String name)
    {
        Role entity = roleRepository.findByRole(name);
        RoleModel model = (RoleModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        roleRepository.delete(id);
    }

    public RoleModel Save(RoleModel role)
    {
        Role entity = (Role) mapperService.toEntity(role);
        Role model = roleRepository.save(entity);
        role = (RoleModel) mapperService.toModel(model);
        return role;
    }

}
