package com.unesco.core.services.account.studentService;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IStudentDataService extends IDataService<StudentDTO> {
    List<StudentDTO> GetPage(FilterQueryDTO filter);
    StudentDTO GetByUser(long userId);
    List<StudentDTO> GetByGroup(long groupId);
}
