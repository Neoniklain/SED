package com.unesco.core.dto.enums;

public enum AccessRightType {

    // News
    CREATE_OR_UPDATE_NEWS("CREATE_OR_UPDATE_NEWS"),
    // Tasks
    CREATE_TASK("CREATE_TASK"),
    VIEW_TASK("VIEW_TASK"),
    // Schedule
    VIEW_SCHEDULE("VIEW_SCHEDULE"),
    // Jhournal
    VIEW_JOURNAL("VIEW_JOURNAL"),
    EDIT_JOURNAL("EDIT_JOURNAL"),
    EDIT_LESSONS("EDIT_LESSONS"),
    ;

    private final String text;

    AccessRightType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
