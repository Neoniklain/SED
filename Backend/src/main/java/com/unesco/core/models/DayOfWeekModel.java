package com.unesco.core.models;

import java.util.Calendar;

public enum DayOfWeekModel {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье"),
            ;

    private final String text;

    DayOfWeekModel(final String text) {
        this.text = text;
    }

    public int toCalendarDay() {
        switch (text) {
            case "Понедельник":
                return Calendar.MONDAY;
            case "Вторник":
                return Calendar.TUESDAY;
            case "Среда":
                return Calendar.WEDNESDAY;
            case "Четверг":
                return Calendar.THURSDAY;
            case "Пятница":
                return Calendar.FRIDAY;
            case "Суббота":
                return Calendar.SATURDAY;
            case "Воскресенье":
                return Calendar.SUNDAY;
        }
        return 0;
    }

    @Override
    public String toString() {
        return text;
    }

}
