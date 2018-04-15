package com.unesco.core.services.sheduleService;

import com.unesco.core.entities.Professor;
import com.unesco.core.models.*;
import com.unesco.core.models.additional.JSONResponseStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ISheduleService {

    List<PairModel> getPairs(ProfessorModel professor);

    List<PairModel> getOddPairs(ProfessorModel proffesor);
    List<PairModel> getEvenPairs(ProfessorModel proffesor);

    List<PairModel> getPairs(GroupModel group);
    List<PairModel> getOddPairs(GroupModel group);
    List<PairModel> getEvenPairs(GroupModel group);

    List<DepartmentSheduleModel> getPairs(DepartmentModel department);
    List<DepartmentSheduleModel> getOddPairs(DepartmentModel department);
    List<DepartmentSheduleModel> getEvenPairs(DepartmentModel department);

    PairModel getPair(int id);
    JSONResponseStatus deletePair(int id);
    JSONResponseStatus savePair(PairModel pairModel);

}
