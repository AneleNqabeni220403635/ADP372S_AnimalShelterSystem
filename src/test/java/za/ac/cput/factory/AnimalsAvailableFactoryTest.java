package za.ac.cput.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.domain.MedicalRecord;

public class AnimalsAvailableFactoryTest {

    @Test
    public void testCreateAnimalsAvailable() {

        Long id = 1L;
        String species = "Dog";
        String breed = "Labrador Retriever";
        String gender = "Male";
        Double weight = 25.5;
        Boolean available = true;
        MedicalRecord medicalRecord = new MedicalRecord(/* Provide necessary parameters for MedicalRecord */);


        AnimalsAvailable animalsAvailable = AnimalsAvailableFactory.createAnimalsAvailable(id, species, breed, gender, weight, available, medicalRecord);


        Assertions.assertNotNull(animalsAvailable);
        Assertions.assertEquals(id, animalsAvailable.getId());
        Assertions.assertEquals(species, animalsAvailable.getSpecies());
        Assertions.assertEquals(breed, animalsAvailable.getBreed());
        Assertions.assertEquals(gender, animalsAvailable.getGender());
        Assertions.assertEquals(weight, animalsAvailable.getWeight());
        Assertions.assertEquals(available, animalsAvailable.getAvailable());
        Assertions.assertEquals(medicalRecord, animalsAvailable.getMedicalRecord());
    }
}