package za.ac.cput.factory;


import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

public class AnimalFactory {

    public static Animal buildAnimal(long animalCode, String name, int age, String type, MedicalRecord medicalRecord) {
        if (Helper.isNullorZero(animalCode) || Helper.isNullorEmpty(name) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(type) || medicalRecord == null) {
            return null;
        }


        return new Animal.Builder().setAnimalCode(animalCode)
                .setAnimalCode(animalCode)
                .setName(name)
                .setAge(age)
                .setType(type)
                .setMedicalRecord(medicalRecord)
                .build();
    }

    public static Animal buildAnimal(String name, int age, String type, MedicalRecord medicalRecord) {
        if (Helper.isNullorEmpty(name) || Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(type) || medicalRecord == null) {
            return null;
        }
            long AnimalCode = Helper.generateAnimalCode();

            return new Animal.Builder()
                    .setName(name)
                    .setAge(age)
                    .setType(type)
                    .setMedicalRecord(medicalRecord)
                    .build();
        }


    }

