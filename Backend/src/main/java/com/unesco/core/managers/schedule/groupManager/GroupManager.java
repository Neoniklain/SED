package com.unesco.core.managers.schedule.groupManager;

import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.shedule.GroupDTO;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GroupManager implements IGroupManager {

@Autowired
public IGroupDataService dataService;

    GroupDTO group;

    public GroupManager() {
        group = new GroupDTO();
    }

    public void Init(GroupDTO Group) {
        group = Group;
    }

    public GroupDTO Get() {
        return group;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (group.getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано название группы");
        }
        if (group.getDepartment().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана кафедра");
        }
        return responseStatusDTO;
    }

}
