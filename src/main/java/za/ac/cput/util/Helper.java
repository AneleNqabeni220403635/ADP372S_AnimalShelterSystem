package za.ac.cput.util;

public class Helper {

    public static boolean isNullorEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isLessThanOrEqualToZero(int number) {
        return number <= 0;
    }

    public static boolean isNullorZero(long number) {
        return number == 0;
    }

    public static long generateDogId() {
        return System.currentTimeMillis();
    }

    public static long generateMedicalRecordId() {
        return System.currentTimeMillis();
    }
}
