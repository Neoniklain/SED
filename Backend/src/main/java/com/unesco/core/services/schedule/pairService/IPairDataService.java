package com.unesco.core.services.schedule.pairService;

import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.shedule.PairDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IPairDataService extends IDataService<PairDTO> {
    List<PairDTO> FindIntersections(PairDTO pairModel);
    List<PairDTO> GetPage(FilterQueryDTO filter);
    List<PairDTO> GetAllByProfessor(long professorId);
    List<PairDTO> GetAllByDepartament(long departmentId);
    List<PairDTO> GetAllByGroup(long groupId);
    List<PairDTO> GetAllByLesson(long lessonId);
}
