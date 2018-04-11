package com.unesco.core.models;

import java.util.List;

public class DepartmentPairModel {
    private String prof_fio;
    private List<PairModel> pairs;

    public String getProf_fio() {
        return prof_fio;
    }

    public void setProf_fio(String prof_fio) {
        this.prof_fio = prof_fio;
    }

    public List<PairModel> getPairs() {
        return pairs;
    }

    public void setPairs(List<PairModel> pairs) {
        this.pairs = pairs;
    }

    public DepartmentPairModel(String prof_fio, List<PairModel> pairs) {
        this.prof_fio = prof_fio;
        this.pairs = pairs;
    }
}
