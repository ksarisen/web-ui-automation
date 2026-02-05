package com.vakifbank.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter MODAL_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM,yyyy");

    public static String formatDob(LocalDate date) {
        return date.format(MODAL_FORMAT);
    }
}
