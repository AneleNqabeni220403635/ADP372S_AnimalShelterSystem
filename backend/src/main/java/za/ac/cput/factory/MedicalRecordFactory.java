package za.ac.cput.factory;

import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class MedicalRecordFactory {

    public static MedicalRecord buildMedicalRecord(Long id, Dog dog, Cat cat, LocalDate vaccinationDate, String medication, String behaviour, LocalDate nextCheckup, String description) {
        if (Helper.isNullorZero(id) || (dog == null && cat == null) || vaccinationDate == null || Helper.isNullorEmpty(medication) || Helper.isNullorEmpty(behaviour) || nextCheckup == null || Helper.isNullorEmpty(description)) {
            return null;
        }

        return new MedicalRecord.Builder()
                .setId(id)
                .setDog(dog)
                .setCat(cat)
                .setVaccinationDate(vaccinationDate)
                .setMedication(medication)
                .setBehaviour(behaviour)
                .setNextCheckup(nextCheckup)
                .setDescription(description)
                .build();
    }

    public static MedicalRecord buildMedicalRecord(Dog dog, Cat cat, LocalDate vaccinationDate, String medication, String behaviour, LocalDate nextCheckup, String description) {
        if ((dog == null && cat == null) || vaccinationDate == null || Helper.isNullorEmpty(medication) || Helper.isNullorEmpty(behaviour) || nextCheckup == null || Helper.isNullorEmpty(description)) {
            return null;
        }

        Long id = Helper.generateMedicalRecordId(); // Assuming Helper has a method to generate an ID

        return new MedicalRecord.Builder()
                .setId(id)
                .setDog(dog)
                .setCat(cat)
                .setVaccinationDate(vaccinationDate)
                .setMedication(medication)
                .setBehaviour(behaviour)
                .setNextCheckup(nextCheckup)
                .setDescription(description)
                .build();
    }
}
