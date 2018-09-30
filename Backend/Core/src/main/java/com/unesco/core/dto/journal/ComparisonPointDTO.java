package com.unesco.core.dto.journal;

import com.unesco.core.dto.shedule.PairDTO;

import java.util.List;

public class ComparisonPointDTO {
    private PointTypeDTO type;
    private PairDTO pair;

    public PointTypeDTO getType() {
        return type;
    }
    public void setType(PointTypeDTO type) {
        this.type = type;
    }

    public PairDTO getPair() {
        return pair;
    }
    public void setPair(PairDTO pair) {
        this.pair = pair;
    }
}
