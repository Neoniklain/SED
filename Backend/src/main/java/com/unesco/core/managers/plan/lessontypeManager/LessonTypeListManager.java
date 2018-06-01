package com.unesco.core.managers.plan.lessontypeManager;

import com.unesco.core.managers.plan.lessontypeManager.interfaces.lessontypeList.ILessonTypeListManager;
import com.unesco.core.models.plan.LessonTypeModel;
import com.unesco.core.services.plan.lessonTypeService.ILessonTypeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class LessonTypeListManager implements ILessonTypeListManager {

    public List<LessonTypeModel> lessontypeList;

    public LessonTypeListManager() {
        lessontypeList = new ArrayList<>();
    }

    public void Init(List<LessonTypeModel> LessonTypeList) {
        lessontypeList = LessonTypeList;
    }

    public List<LessonTypeModel> GetAll() {
        return lessontypeList;
    }
}
