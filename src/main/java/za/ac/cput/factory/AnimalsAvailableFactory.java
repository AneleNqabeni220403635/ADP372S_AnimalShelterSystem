package za.ac.cput.factory;

import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

public class AnimalsAvailableFactory {

    public static AnimalsAvailable createAnimalAvailable(String species, String breed, String gender, double weight, boolean available, MedicalRecord medicalRecord) {

        if (Helper.isNullorEmpty(species) || Helper.isNullorEmpty(breed) || Helper.isNullorEmpty(gender) || Helper.isLessThanOrEqualToZero((int) weight)) {
            return null;
        }


        Long newAnimalId = Helper.generateAnimalId();


        return new AnimalsAvailable.Builder()
                .setId(newAnimalId)
                .setSpecies(species)
                .setBreed(breed)
                .setGender(gender)
                .setWeight(weight)
                .setAvailable(available)
                .setMedicalRecord(medicalRecord)
                .build();
    }
}