package com.unesco.core.dto.shedule;

public class PairParameters {
    private PairDTO pairModel;
    private boolean skipWarnings;

    public PairParameters() {
        this.pairModel = new PairDTO();
    }

    public PairDTO getPairModel() {
        return pairModel;
    }
    public void setPairModel(PairDTO pairModel) {
        this.pairModel = pairModel;
    }

    public boolean isSkipWarnings() {
        return skipWarnings;
    }
    public void setSkipWarnings(boolean skipWarnings) {
        this.skipWarnings = skipWarnings;
    }
}
