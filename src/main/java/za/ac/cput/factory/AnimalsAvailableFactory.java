package za.ac.cput.factory;

import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

public class AnimalsAvailableFactory {

    public static AnimalsAvailable createAnimalsAvailable(Long animalCode, String species, String breed, String gender,
                                                          Double weight, Boolean available, MedicalRecord medicalRecord) {
        if (Helper.isNullorZero(animalCode) || Helper.isNullorEmpty(species) || Helper.isNullorEmpty(breed) ||
                Helper.isNullorEmpty(gender) || weight == null || available == null || medicalRecord == null) {
            return null;
        }

        return new AnimalsAvailable.Builder()
                .setAnimalCode(animalCode)
                .setSpecies(species)
                .setBreed(breed)
                .setGender(gender)
                .setWeight(weight)
                .setAvailable(available)
                .setMedicalRecord(medicalRecord)
                .build();
    }
}