package com.vakifbank.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    public static String randomFirstName() {
        List<String> names = List.of("Kerem", "Ahmet", "Mehmet", "Ayse", "Elif");
        return names.get(random.nextInt(names.size()));
    }

    public static String randomLastName() {
        List<String> surnames = List.of("Yilmaz", "Kaya", "Demir", "Celik");
        return surnames.get(random.nextInt(surnames.size()));
    }

    public static String randomEmail() {
        return "test" + System.currentTimeMillis() + "@mail.com";
    }

    public static LocalDate randomDateOfBirth() {
        int year = 1985 + random.nextInt(15); // 1985–1999
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28); // safe
        return LocalDate.of(year, month, day);
    }

    public static String randomSubject() {
        List<String> subjects = List.of("Maths", "Physics", "Chemistry");
        return subjects.get(random.nextInt(subjects.size()));
    }
}
