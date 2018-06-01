package com.unesco.core.managers.schedule.fieldofknowledgeManager;

import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledge.IFieldOfKnowledgeManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.FieldOfKnowledgeModel;
import com.unesco.core.services.schedule.fieldOfKnowledgeService.IFieldOfKnowledgeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FieldOfKnowledgeManager implements IFieldOfKnowledgeManager {

    FieldOfKnowledgeModel fieldofknowledge;

    public FieldOfKnowledgeManager() {
        fieldofknowledge = new FieldOfKnowledgeModel();
    }

    public void Init(FieldOfKnowledgeModel FieldOfKnowledge) {
        fieldofknowledge = FieldOfKnowledge;
    }

    public FieldOfKnowledgeModel Get() {
        return fieldofknowledge;
    }

}
