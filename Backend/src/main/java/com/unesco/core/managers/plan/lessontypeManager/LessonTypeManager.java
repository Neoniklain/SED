package com.unesco.core.managers.plan.lessontypeManager;

import com.unesco.core.managers.plan.lessontypeManager.interfaces.lessontype.ILessonTypeManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.plan.LessonTypeModel;
import com.unesco.core.services.plan.lessonTypeService.ILessonTypeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LessonTypeManager implements ILessonTypeManager {

    LessonTypeModel lessontype;

    public LessonTypeManager() {
        lessontype = new LessonTypeModel();
    }

    public void Init(LessonTypeModel LessonType) {
        lessontype = LessonType;
    }

    public LessonTypeModel Get() {
        return lessontype;
    }

}
