package com.unesco.core.services.dataService.account.roleService;

import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public PageResultDTO<RoleDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) roleRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<RoleEntity> entitys = roleRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<RoleDTO> result = new ArrayList<RoleDTO>();
        for (RoleEntity e: entitys) {
            result.add((RoleDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, roleRepository.count());
    }

    public List<RoleDTO> getAll()
    {
        List<RoleDTO> modelList = new ArrayList<>();
        Iterable<RoleEntity> entityList = roleRepository.findAll();
        for (RoleEntity item: entityList) {
            RoleDTO model = (RoleDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public RoleDTO get(long id)
    {
        RoleEntity entity = roleRepository.findOne(id);
        RoleDTO model = (RoleDTO) mapperService.toDto(entity);
        return model;
    }

    public RoleDTO getByName(String name)
    {
        RoleEntity entity = roleRepository.findByRole(name);
        RoleDTO model = (RoleDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<RoleDTO> delete(long id)
    {
        ResponseStatusDTO<RoleDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            roleRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<RoleDTO> save(RoleDTO role)
    {
        RoleEntity entity = (RoleEntity) mapperService.toEntity(role);
        ResponseStatusDTO<RoleDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = roleRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((RoleDTO) mapperService.toDto(entity));
        return result;
    }

}
