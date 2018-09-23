package com.unesco.core.services.dataService.schedule.pairService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IPairDataService extends IDataService<PairDTO> {
    List<PairDTO> findIntersections(PairDTO pairModel);
    List<PairDTO> getPage(FilterQueryDTO filter);
    List<PairDTO> getAllByProfessor(long professorId);
    List<PairDTO> getAllByDepartament(long departmentId);
    List<PairDTO> getAllByGroup(long groupId);
    List<PairDTO> getAllByLesson(long lessonId);
}
