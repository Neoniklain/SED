package com.unesco.core.managers.schedule.fieldofknowledgeManager;

import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledgeList.IFieldOfKnowledgeListManager;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.FieldOfKnowledgeModel;
import com.unesco.core.services.schedule.fieldOfKnowledgeService.IFieldOfKnowledgeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class FieldOfKnowledgeListManager implements IFieldOfKnowledgeListManager {

    public List<FieldOfKnowledgeModel> fieldofknowledgeList;

    public FieldOfKnowledgeListManager() {
        fieldofknowledgeList = new ArrayList<>();
    }

    //Base

    public void Init(List<FieldOfKnowledgeModel> FieldOfKnowledgeList) {
        fieldofknowledgeList = FieldOfKnowledgeList;
    }

    public List<FieldOfKnowledgeModel> GetAll() {
        return fieldofknowledgeList;
    }

}
