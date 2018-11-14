package com.unesco.core.utils;

import java.util.Calendar;

public class DayOfWeekHelper {
    public static int getWeekNumber(String dayName) {
        switch (dayName) {
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
            default:
                return Calendar.MONDAY;
        }
    }
}
