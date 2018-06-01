package com.unesco.core.managers.schedule.sheduleManager;

import com.unesco.core.managers.schedule.sheduleManager.interfaces.sheduleList.ISheduleListManager;
import com.unesco.core.models.SheduleModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class SheduleListManager implements ISheduleListManager {

    public List<SheduleModel> sheduleList;
}
