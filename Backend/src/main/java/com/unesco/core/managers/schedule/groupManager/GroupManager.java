package com.unesco.core.managers.schedule.groupManager;

import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
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

}
