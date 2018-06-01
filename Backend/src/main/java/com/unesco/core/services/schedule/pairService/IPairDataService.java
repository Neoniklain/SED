package com.unesco.core.services.schedule.pairService;

import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.PairModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IPairDataService extends IDataService<PairModel> {
    List<PairModel> FindIntersections(PairModel pairModel);
    List<PairModel> GetPage(FilterQuery filter);
    List<PairModel> GetAllByProfessor(long professorId);
    List<PairModel> GetAllByDepartament(long departmentId);
    List<PairModel> GetAllByGroup(long groupId);
    List<PairModel> GetAllByLesson(long lessonId);
}
