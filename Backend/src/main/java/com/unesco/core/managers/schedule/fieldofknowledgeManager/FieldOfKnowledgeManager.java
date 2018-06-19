package com.unesco.core.managers.schedule.fieldofknowledgeManager;

import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledge.IFieldOfKnowledgeManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.FieldOfKnowledgeModel;
import com.unesco.core.utils.StatusTypes;
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

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (fieldofknowledge.getName().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указано название раздела знаний");
        }
        return responseStatus;
    }
}
