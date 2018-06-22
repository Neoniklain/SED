package com.unesco.core.managers.schedule.pairManager.interfaces.pair;

import com.unesco.core.managers.IManager;
import com.unesco.core.managers.IValidateManager;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.shedule.PairDTO;

import java.util.List;

public interface IPairManager extends IManager<PairDTO>, IValidateManager {

    ResponseStatusDTO CheckIntersection(List<PairDTO> pairsForValidate);
}
