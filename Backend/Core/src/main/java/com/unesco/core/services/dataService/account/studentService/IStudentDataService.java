package com.unesco.core.services.dataService.account.studentService;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IStudentDataService extends IDataService<StudentDTO> {
    PageResultDTO<StudentDTO> getPage(FilterQueryDTO filter);
    StudentDTO getByUser(long userId);
    List<StudentDTO> getByGroup(long groupId);
}
