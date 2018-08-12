package com.unesco.core.dto.enums;

public enum PointTypes {

    Visitation("Посещение")
            ;

    private final String text;

    PointTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
