package com.unesco.core.services.account.studentService;

import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IStudentDataService extends IDataService<StudentModel> {
    List<StudentModel> GetPage(FilterQuery filter);
    StudentModel GetByUser(long userId);
    List<StudentModel> GetByGroup(long groupId);
}
