package com.unesco.core.utils;

import com.unesco.core.dto.plan.SemesterNumberYear;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    /**
     * Приводит двремя у даты к 12 чсасам
     * Во избежание смещения от часового пояса
     *
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
     *
     * @param startDate Дата от которой ведется счет
     * @param date      Дата для которой нужно определить четность
     * @return 0 нечетная 1 четная
     */
    public static int getPrityWeek(Date date, Date startDate) {

        if (startDate.compareTo(date) < 0) return 0;

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        int startWeekNumber = startCal.get(Calendar.WEEK_OF_YEAR);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentWeekNumber = cal.get(Calendar.WEEK_OF_YEAR);

        startWeekNumber = currentWeekNumber - startWeekNumber + 1;
        return startWeekNumber % 2 == 0 ? 0 : 1;
    }

    public static StartEndDate getPeriodForYearAndSemester(int semester, int year) {
        StartEndDate result = new StartEndDate();
        Calendar calendar = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (semester == 2) {
            calendar.set(year, 0, 1);
            result.startDate = calendar.getTime();
            calendar.set(year, 8, 1);
            calendar.add(Calendar.DATE, -1);
            result.endDate = calendar.getTime();
        } else {
            calendar.set(year, 8, 1);
            result.startDate = calendar.getTime();
            calendar.set(year + 1, 0, 1);
            calendar.add(Calendar.DATE, -1);
            result.endDate = calendar.getTime();
        }
        return result;
    }

    public static SemesterNumberYear getYearAndSemesterForPeriod(Date startDate, Date endDate) {
        SemesterNumberYear result = new SemesterNumberYear();
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        result.setYear(end.get(Calendar.YEAR));
        result.setSemester(end.get(Calendar.MONTH) > 8 ? 1 : 2 );
        return result;
    }

}
