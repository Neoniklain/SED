package com.unesco.core.services.dataService.account.userService;

import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.services.dataService.account.roleService.RoleDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService implements IUserDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleDataService roleDataService;

    public PageResultDTO<UserDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) userRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<UserEntity> entitys = userRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<UserDTO> result = new ArrayList<UserDTO>();
        for (UserEntity e: entitys) {
            result.add((UserDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, userRepository.count());
    }

    public List<UserDTO> getAll()
    {
        List<UserDTO> modelList = new ArrayList<>();
        Iterable<UserEntity> entityList = userRepository.findAll();
        for (UserEntity item: entityList) {
            UserDTO model = (UserDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public UserDTO get(long id)
    {
        UserEntity entity = userRepository.findOne(id);
        UserDTO model = (UserDTO) mapperService.toDto(entity);
        return model;
    }

    public UserDTO getByUsername(String username)
    {
        UserEntity entity = userRepository.findByUsername(username);
        UserDTO model = (UserDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<UserDTO> delete(long id)
    {
        ResponseStatusDTO<UserDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            userRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<UserDTO> save(UserDTO user)
    {
        List<RoleDTO> roles = new ArrayList<>();
        for (RoleDTO role:  user.getRoles()) {
            RoleDTO findRole = roleDataService.getByName(role.roleName);
            if(findRole != null) {
                roles.add(findRole);
            }
        }
        user.setRoles(roles);

        ResponseStatusDTO<UserDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);

        UserEntity entity = (UserEntity) mapperService.toEntity(user);
        try {
            entity = userRepository.save(entity);
        } catch (Exception e) {
            e.getMessage();
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((UserDTO) mapperService.toDto(entity));
        return result;

    }

}
