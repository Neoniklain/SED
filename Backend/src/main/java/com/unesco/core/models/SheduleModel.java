package com.unesco.core.models;

import com.unesco.core.models.shedule.PairModel;

import java.util.List;

public class SheduleModel {

    private List<PairModel> pairs;

    public List<PairModel> getPairs() {
        return pairs;
    }

    public void setPairs(List<PairModel> pairs) {
        this.pairs = pairs;
    }

}
