package com.unesco.core.managers.plan.lessontypeManager;

import com.unesco.core.managers.plan.lessontypeManager.interfaces.lessontype.ILessonTypeManager;
import com.unesco.core.models.plan.LessonTypeDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LessonTypeManager implements ILessonTypeManager {

    LessonTypeDTO lessontype;

    public LessonTypeManager() {
        lessontype = new LessonTypeDTO();
    }

    public void Init(LessonTypeDTO LessonType) {
        lessontype = LessonType;
    }

    public LessonTypeDTO Get() {
        return lessontype;
    }

}
