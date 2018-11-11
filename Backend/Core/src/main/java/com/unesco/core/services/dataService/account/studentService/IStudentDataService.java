package com.unesco.core.services.dataService.account.studentService;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.StudentJournalDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IStudentDataService extends IDataService<StudentDTO> {
    PageResultDTO<StudentDTO> getPage(FilterQueryDTO filter);

    StudentDTO getByUser(long userId);

    List<StudentDTO> getByGroup(long groupId);

    List<StudentJournalDTO> getByGroupAndLesson(long groupId, long lessonId);

    ResponseStatusDTO<StudentDTO> saveStudentsSubgroup(List<StudentJournalDTO> studentJournal, LessonDTO lesson);
}
