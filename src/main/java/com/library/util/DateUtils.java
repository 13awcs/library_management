package com.library.util;

import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@UtilityClass
public class DateUtils {

    public static final Date INFINITY_DATE = new Date((9999 - 1900), 11, 31); //2023-01-01
    public static final String ONLY_DATE_FORMAT = "yyyy/MM/dd";
    public static final String SQL_DAY_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String ONLY_DATE_FORMAT_JP = "yyyy年MM月dd日";
    public static final String DATE_TIME_FORMAT_JP = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_TIME_FORMAT_WITHOUT_SS = "yyyy/MM/dd HH:mm";


    public static Date toDate(String dateStr, SimpleDateFormat simpleDateFormat) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date toDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String getDateCSVAsString(Date date) {
        DateFormat DATE_CSV_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
        return DATE_CSV_FORMAT.format(date);
    }

    public static String getDateFormatted(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date plusDayFromDate(int numberIncrease, Date dateStart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateStart);
        cal.add(Calendar.DATE, numberIncrease);
        return cal.getTime();
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getStartOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getStartOfMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year + 1900);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year + 1900);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static List<Date> getAllDateInRange(Date dateStart, Date dateEnd) {
        List<Date> result = new ArrayList<>();
        result.add(dateStart);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateStart);
        while (cal.getTime().before(dateEnd)) {
            cal.add(Calendar.DATE, 1);
            result.add(DateUtils.getStartOfDay(cal.getTime()));
        }

        return result;
    }

    public static int getAgeByBirthday(Date birthday) {
        if (birthday == null) {
            return 0;
        }
        int yearBirthday = birthday.getYear();
        int yearCurrent = new Date().getYear();
        return yearCurrent - yearBirthday;
    }

    public static boolean equals(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }
        return date1 != null && date2 != null && date1.getTime() == date2.getTime();
    }

    public static String toDateStr(Date date, DateFormat dateFormat) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }

}
