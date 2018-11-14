package com.unesco.core.utils;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    /**
     * Приводит двремя у даты к 12 чсасам
     * Во избежание смещения от часового пояса
     * @param date Дата для приведения
     * @return Возвращает дату с 12 часами
     */
    public static Date getZeroTimeDate(Date date) {
        Date res = date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        res = calendar.getTime();
        return res;
    }

    /**
     * Определяет четность недели от заданной даты
     * @param startDate Дата от которой ведется счет
     * @param date Дата для которой нужно определить четность
     * @return 0 нечетная 1 четная
     */
    public static int getPrityWeek(Date startDate, Date date) {

        if(startDate.compareTo(date) < 0) return 0;

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        int startWeekNumber = startCal.get(Calendar.WEEK_OF_YEAR);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentWeekNumber = cal.get(Calendar.WEEK_OF_YEAR);
        startWeekNumber = currentWeekNumber - startWeekNumber;

        return startWeekNumber % 2 == 0 ? 1 : 0;
    }



}
