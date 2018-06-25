package com.unesco.core.managers.schedule.groupManager;

import com.unesco.core.managers.schedule.groupManager.interfaces.groupList.IGroupListManager;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class GroupListManager implements IGroupListManager {

    @Autowired
    public IGroupDataService dataService;

    public List<GroupDTO> groupList;

    public GroupListManager() {
        groupList = new ArrayList<>();
    }

    public void Init(List<GroupDTO> GroupList) {
        groupList = GroupList;
    }

    public List<GroupDTO> GetAll() {
        return groupList;
    }

}
