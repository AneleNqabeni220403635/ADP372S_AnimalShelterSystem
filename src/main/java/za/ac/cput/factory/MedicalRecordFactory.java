package za.ac.cput.factory;

import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class MedicalRecordFactory {

    public static MedicalRecord buildMedicalRecord(Long id, Dog dog, LocalDate vaccinationDate, String medication, String behaviour, LocalDate nextCheckup) {
        if (!isValid(id, dog, medication, behaviour, vaccinationDate, nextCheckup)) {
            return null;
        }

        return new MedicalRecord.Builder()
                .setId(id)
                .setDog(dog)
                .setVaccinationDate(vaccinationDate)
                .setMedication(medication)
                .setBehaviour(behaviour)
                .setNextCheckup(nextCheckup)
                .build();
    }

    public static MedicalRecord buildMedicalRecord(Dog dog, LocalDate vaccinationDate, String medication, String behaviour, LocalDate nextCheckup) {
        if (!isValid(dog, medication, behaviour, vaccinationDate, nextCheckup)) {
            return null;
        }

        Long id = Helper.generateMedicalRecordId();

        return new MedicalRecord.Builder()
                .setId(id)
                .setDog(dog)
                .setVaccinationDate(vaccinationDate)
                .setMedication(medication)
                .setBehaviour(behaviour)
                .setNextCheckup(nextCheckup)
                .build();
    }

    private static boolean isValid(Long id, Dog dog, String medication, String behaviour, LocalDate vaccinationDate, LocalDate nextCheckup) {
        return !Helper.isNullorZero(id) && dog != null && !Helper.isNullorEmpty(medication) && !Helper.isNullorEmpty(behaviour) && vaccinationDate != null && nextCheckup != null;
    }

    private static boolean isValid(Dog dog, String medication, String behaviour, LocalDate vaccinationDate, LocalDate nextCheckup) {
        return dog != null && !Helper.isNullorEmpty(medication) && !Helper.isNullorEmpty(behaviour) && vaccinationDate != null && nextCheckup != null;
    }
}
