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

    public static long generatePetOwnerId() {
        return System.currentTimeMillis();
    }

    public static long generateEmployeeId() {
        return System.currentTimeMillis();
    }

    public static long generateCatId() {
        return System.currentTimeMillis();
    }

    public static long generateApplicantId() {
        return System.currentTimeMillis();
    }

    public static long generateSaleId() {
        return System.currentTimeMillis();
    }

    public static long generateOwnerRecordId() {
        return System.currentTimeMillis();
    }

    public static long generateMedicalRecordId() {
        return System.currentTimeMillis();
    }

    public static long generateVolunteerId() {
        return System.currentTimeMillis();
    }



}

