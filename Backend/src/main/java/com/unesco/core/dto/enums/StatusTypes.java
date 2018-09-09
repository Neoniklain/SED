package com.unesco.core.dto.enums;

public enum StatusTypes {

    OK("Ok"),
    ERROR("Error"),
    WARNING("Warning"),
    NOT_FOUND("Not found"),
    ACCESS_DENIED("Access denied"),
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
