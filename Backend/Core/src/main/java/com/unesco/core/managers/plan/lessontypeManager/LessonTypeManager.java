package com.unesco.core.managers.plan.lessontypeManager;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.managers.plan.lessontypeManager.interfaces.lessontype.ILessonTypeManager;
import com.unesco.core.dto.plan.LessonTypeDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LessonTypeManager implements ILessonTypeManager {

    LessonTypeDTO lessontype;

    public LessonTypeManager() {
        lessontype = new LessonTypeDTO();
    }

    public void init(LessonTypeDTO LessonType) {
        lessontype = LessonType;
    }

    public LessonTypeDTO get() {
        return lessontype;
    }

    @Override
    public ResponseStatusDTO validate() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        return result;
    }
}
