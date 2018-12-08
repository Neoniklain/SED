package com.unesco.core.dto.shedule;

import com.unesco.core.dto.shedule.PairDTO;

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
