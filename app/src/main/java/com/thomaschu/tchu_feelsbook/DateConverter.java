package com.thomaschu.tchu_feelsbook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    private SimpleDateFormat fm;

    private static class DateFormatHolder {
        private static final DateConverter INSTANCE = new DateConverter();
    }

    private DateConverter() {
        fm = new SimpleDateFormat("YYYY-MM-DD'T'HH-mm-ss", Locale.CANADA);
    }

    /*
    * getISO8601FromDate takes a java.util.Date and converts it to ISO8601 format
    * @param {Date date} our target date
    * @return {SimpleDateFormat} formatted date (ISO8601)
    * */
    public static String getISO8601FromDate(Date date) {
        return DateFormatHolder.INSTANCE.fm.format(date);
    }

    /*
    * compareEmotionDates compares 2 emotions by their dates
    * @param {Emotion d1, Emotion d2} the 2 emotions to compare
    * @return {int} an integer based on comparison
    * */
    public static int compareEmotionDates(Emotion d1, Emotion d2) {
        return d1.getDate().compareTo(d2.getDate());
    }

    public static Date getDateFromString(String input) {
        Date result;
        // if we can't parse it, set to current date
        try {
            result = DateFormatHolder.INSTANCE.fm.parse(input);
        }catch(ParseException e) {
            result = new Date();
        }
        return result;
    }
}
