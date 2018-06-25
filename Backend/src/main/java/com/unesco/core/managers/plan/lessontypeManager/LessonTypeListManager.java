package com.unesco.core.managers.plan.lessontypeManager;

import com.unesco.core.managers.plan.lessontypeManager.interfaces.lessontypeList.ILessonTypeListManager;
import com.unesco.core.dto.plan.LessonTypeDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class LessonTypeListManager implements ILessonTypeListManager {

    public List<LessonTypeDTO> lessontypeList;

    public LessonTypeListManager() {
        lessontypeList = new ArrayList<>();
    }

    public void Init(List<LessonTypeDTO> LessonTypeList) {
        lessontypeList = LessonTypeList;
    }

    public List<LessonTypeDTO> GetAll() {
        return lessontypeList;
    }
}
