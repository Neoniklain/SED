package com.unesco.core.managers.schedule.pairManager.interfaces.pair;

import com.unesco.core.managers.IManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.PairModel;

import java.util.List;

public interface IPairManager extends IManager<PairModel> {

    ResponseStatus CheckIntersection(List<PairModel> pairsForValidate);
}
