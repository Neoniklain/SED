package com.unesco.core.services.dataService.schedule.pairService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IPairDataService extends IDataService<PairDTO> {
    List<PairDTO> getAllByProfessor(long professorId, int semester, int year);
    List<PairDTO> getAllByDepartament(long departmentId, int semester, int year);
    List<PairDTO> getAllByGroup(long groupId, int semester, int year);
    List<PairDTO> getAllByLesson(long lessonId, int semester, int year);
}
