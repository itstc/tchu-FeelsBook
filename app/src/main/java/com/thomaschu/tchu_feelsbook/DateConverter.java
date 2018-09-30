package com.thomaschu.tchu_feelsbook;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {

    private SimpleDateFormat fm;
    private SimpleDateFormat dateTofm;
    private SimpleDateFormat datefmt;
    private SimpleDateFormat timefmt;

    private static class DateFormatHolder {
        private static final DateConverter INSTANCE = new DateConverter();
    }

    private DateConverter() {
        fm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        fm.setLenient(false);
        dateTofm = new SimpleDateFormat("yyyy-MM-dd");
        datefmt = new SimpleDateFormat("dd/MM/yy");
        timefmt = new SimpleDateFormat("HH:mm:ss");
    }

    /*
    * getISO8601FromDate takes a java.util.Date and converts it to ISO8601 format
    * @param {Date date} our target date
    * @return {SimpleDateFormat} formatted date (ISO8601)
    * */
    public static String getISO8601FromDate(Date date) {
        return DateFormatHolder.INSTANCE.fm.format(date);
    }

    public static String getDateFormatString(Date date) {
        return DateFormatHolder.INSTANCE.datefmt.format(date);
    }

    public static String formatDateToISO(String date) {
        try {
            Date newDate = DateFormatHolder.INSTANCE.datefmt.parse(date);
            return DateFormatHolder.INSTANCE.dateTofm.format(newDate);

        }catch(ParseException e) {
            return "";
        }
    }

    public static String getTimeFormatString(Date date) {
        return DateFormatHolder.INSTANCE.timefmt.format(date);
    }

    /*
    * compareEmotionDates compares 2 emotions by their dates
    * @param {Emotion d1, Emotion d2} the 2 emotions to compare
    * @return {int} an integer based on comparison
    * */
    public static int compareEmotionDates(Emotion e1, Emotion e2) {
        Date d1 = getDateFromString(e1.getDate());
        Date d2 = getDateFromString(e2.getDate());

        return d1.compareTo(d2);
    }

    public static boolean isValidDate(String input) {
        try {
            DateFormatHolder.INSTANCE.fm.parse(input);
            return true;
        } catch(ParseException e) {
            return false;
        }
    }

    public static Date getDateFromString(String input) {
        Date result;
        // if we can't parse it, set to current date
        try {
            result = DateFormatHolder.INSTANCE.fm.parse(input);
            Log.d("DATE", result.toString());
        }catch(ParseException e) {
            result = new Date();
        }
        return result;
    }
}
