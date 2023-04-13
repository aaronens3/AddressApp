package com.example.addressapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final String PATRO_DATA = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern(PATRO_DATA);

    public static String format (LocalDate data) {
        if (data == null) {
            return null;
        }
        return FORMATADOR_DATA.format(data);
    }
    public static LocalDate parse (String data) {
        try {
            return FORMATADOR_DATA.parse(data, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    public static boolean validData (String data) {
        return DateUtil.parse(data) != null;
    }
}
