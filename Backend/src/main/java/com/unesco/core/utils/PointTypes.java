package com.unesco.core.utils;

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
