package com.unesco.core.dto.enums;

public enum AccessRightType {

    // News
    CREATE_NEWS("CREATE_NEWS"),
    EDIT_NEWS("EDIT_NEWS"),
    // Tasks
    CREATE_TASK("CREATE_TASK"),
    VIEW_TASK("VIEW_TASK"),
    // Schedule
    VIEW_SCHEDULE("VIEW_SCHEDULE");

    private final String text;

    AccessRightType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
