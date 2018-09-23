package com.unesco.core.managers.schedule.fieldofknowledgeManager;

import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledgeList.IFieldOfKnowledgeListManager;
import com.unesco.core.dto.shedule.FieldOfKnowledgeDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class FieldOfKnowledgeListManager implements IFieldOfKnowledgeListManager {

    public List<FieldOfKnowledgeDTO> fieldofknowledgeList;

    public FieldOfKnowledgeListManager() {
        fieldofknowledgeList = new ArrayList<>();
    }

    //Base

    public void init(List<FieldOfKnowledgeDTO> FieldOfKnowledgeList) {
        fieldofknowledgeList = FieldOfKnowledgeList;
    }

    public List<FieldOfKnowledgeDTO> getAll() {
        return fieldofknowledgeList;
    }

}
