package com.unesco.core.managers.schedule.fieldofknowledgeManager;

import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledge.IFieldOfKnowledgeManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.FieldOfKnowledgeDTO;
import com.unesco.core.utils.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FieldOfKnowledgeManager implements IFieldOfKnowledgeManager {

    FieldOfKnowledgeDTO fieldofknowledge;

    public FieldOfKnowledgeManager() {
        fieldofknowledge = new FieldOfKnowledgeDTO();
    }

    public void Init(FieldOfKnowledgeDTO FieldOfKnowledge) {
        fieldofknowledge = FieldOfKnowledge;
    }

    public FieldOfKnowledgeDTO Get() {
        return fieldofknowledge;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (fieldofknowledge.getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано название раздела знаний");
        }
        return responseStatusDTO;
    }
}
