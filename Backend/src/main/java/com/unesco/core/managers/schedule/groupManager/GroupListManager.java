package com.unesco.core.managers.schedule.groupManager;

import com.unesco.core.managers.schedule.groupManager.interfaces.groupList.IGroupListManager;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.GroupModel;
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

    public List<GroupModel> groupList;

    public GroupListManager() {
        groupList = new ArrayList<>();
    }

    public void Init(List<GroupModel> GroupList) {
        groupList = GroupList;
    }

    public List<GroupModel> GetAll() {
        return groupList;
    }

}
