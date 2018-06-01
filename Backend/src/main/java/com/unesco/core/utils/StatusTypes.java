package com.unesco.core.utils;

public enum StatusTypes {

    OK("Ok"),
    ERROR("Error")
    ;

    private final String text;

    StatusTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
