package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    private static final String CBR_API_FORMAT = "dd/MM/yyyy";
    private static final String CBR_API_ANSWER_FORMAT = "dd.MM.yyyy";

    private static final SimpleDateFormat defaultFormat = new SimpleDateFormat(DEFAULT_FORMAT);
    private static final SimpleDateFormat cbrFormat = new SimpleDateFormat(CBR_API_FORMAT);
    private static final SimpleDateFormat cbrAnswerFormat = new SimpleDateFormat(CBR_API_ANSWER_FORMAT);


    public static String getDefaultFormat() {
        return DEFAULT_FORMAT;
    }

    public static Date parseDateDefault(String date) throws ParseException {
        return defaultFormat.parse(date);
    }

    public static Date parseDateCbrAns(String date) throws ParseException {
        return cbrAnswerFormat.parse(date);
    }

    public static String formatDateCbr(Date date) {
        return cbrFormat.format(date);
    }

    public static String formatDateCbrAns(Date date) {
        return cbrAnswerFormat.format(date);
    }

}
