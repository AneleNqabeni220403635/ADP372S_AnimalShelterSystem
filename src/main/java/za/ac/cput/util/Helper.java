package za.ac.cput.util;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Helper {
    private static final AtomicLong counter = new AtomicLong(1000);

    public static boolean isNullorEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullorZero(Long l) {
        return l == null || l == 0;
    }

    public static boolean isLessThanOrEqualToZero(int num) {
        return num <= 0;
    }

    public static boolean isValidAnimal(Long animal) {
        return animal != null && animal > 0;
    }

    public static boolean isValidLocalDateVaccinationDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    public static boolean isValidLocalDateNextCheckUp(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    public static boolean isValidMedication(String medication) {
        return !isNullorEmpty(medication);
    }

    public static boolean isValidBehaviour(String behaviour) {
        return !isNullorEmpty(behaviour);
    }

    public static Long generateAnimalCode() {
        return counter.incrementAndGet();
    }

    public static boolean IsValidLocalDate(LocalDate endDate) {
        return !IsValidLocalDate(endDate);
    }
}
