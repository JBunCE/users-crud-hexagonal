package com.jbunce.userscrudhex.domain.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private final static String FORMAT = "yyyy-MM-dd";

    public static String toString(LocalDate date) {
        return DateTimeFormatter.ofPattern(FORMAT).format(date);
    }
    
}
