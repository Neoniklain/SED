package com.unesco.core.models;

import com.unesco.core.models.shedule.PairDTO;

import java.util.List;

public class SheduleDTO {

    private List<PairDTO> pairs;

    public List<PairDTO> getPairs() {
        return pairs;
    }

    public void setPairs(List<PairDTO> pairs) {
        this.pairs = pairs;
    }

}
