package com.unesco.core.services.sheduleService;

import com.unesco.core.entities.Professor;
import com.unesco.core.models.DepartmentModel;
import com.unesco.core.models.GroupModel;
import com.unesco.core.models.PairModel;
import com.unesco.core.models.ProfessorModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ISheduleService {

    List<PairModel> getPairs(ProfessorModel professor);
    List<PairModel> getOddPairs(ProfessorModel proffesor);
    List<PairModel> getEvenPairs(ProfessorModel proffesor);

    List<PairModel> getOddPairs(GroupModel group);
    List<PairModel> getEvenPairs(GroupModel group);

    Map<ProfessorModel, List<PairModel>> getOddPairs(DepartmentModel department);
    Map<ProfessorModel, List<PairModel>> getEvenPairs(DepartmentModel department);

    PairModel getPair(int id);

}
