package com.unesco.core.managers.schedule.groupManager;

import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.GroupModel;
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

    GroupModel group;

    public GroupManager() {
        group = new GroupModel();
    }

    public void Init(GroupModel Group) {
        group = Group;
    }

    public GroupModel Get() {
        return group;
    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (group.getName().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указано название группы");
        }
        if (group.getDepartment().getId() == 0) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указана кафедра");
        }
        return responseStatus;
    }

}
