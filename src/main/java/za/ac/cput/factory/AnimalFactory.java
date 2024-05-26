package za.ac.cput.factory;

import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.util.HashSet;
import java.util.Set;

public class AnimalFactory {

    public static Animal buildAnimal(long animalCode, String name, int age, String type, Set<MedicalRecord> medicalRecords) {
        if (Helper.isNullorZero(animalCode) || Helper.isNullorEmpty(name) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(type) || medicalRecords == null || medicalRecords.isEmpty()) {
            return null;
        }

        Animal animal = new Animal.Builder()
                .setAnimalCode(animalCode)
                .setName(name)
                .setAge(age)
                .setType(type)
                .setMedicalRecords(new HashSet<>())
                .build();

        Set<MedicalRecord> updatedMedicalRecords = new HashSet<>();
        for (MedicalRecord record : medicalRecords) {
            MedicalRecord updatedRecord = new MedicalRecord.Builder()
                    .copy(record)
                    .setAnimal(animal)
                    .build();
            updatedMedicalRecords.add(updatedRecord);
        }

        animal = new Animal.Builder()
                .copy(animal)
                .setMedicalRecords(updatedMedicalRecords)
                .build();

        return animal;
    }

    public static Animal buildAnimal(String name, int age, String type, Set<MedicalRecord> medicalRecords) {
        if (Helper.isNullorEmpty(name) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(type) || medicalRecords == null || medicalRecords.isEmpty()) {
            return null;
        }

        long animalCode = Helper.generateAnimalCode();

        Animal animal = new Animal.Builder()
                .setAnimalCode(animalCode)
                .setName(name)
                .setAge(age)
                .setType(type)
                .setMedicalRecords(new HashSet<>())
                .build();

        Set<MedicalRecord> updatedMedicalRecords = new HashSet<>();
        for (MedicalRecord record : medicalRecords) {
            MedicalRecord updatedRecord = new MedicalRecord.Builder()
                    .copy(record)
                    .setAnimal(animal)
                    .build();
            updatedMedicalRecords.add(updatedRecord);
        }

        animal = new Animal.Builder()
                .copy(animal)
                .setMedicalRecords(updatedMedicalRecords)
                .build();

        return animal;
    }
}
