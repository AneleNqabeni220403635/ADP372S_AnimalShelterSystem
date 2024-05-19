package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.domain.MedicalRecord;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalsAvailableFactoryTest {
    @Test
    void testBuildAnimalsAvailable() {
        long animalCode = 2;
        String species = "Dog";
        String breed = "Labrador";
        String gender = "Male";
        double weight = 25.5;
        boolean available = true;
        MedicalRecord medicalRecord = MedicalRecordFactory.buildDefaultMedicalRecord(animalCode);

        AnimalsAvailable animalsAvailable = AnimalsAvailableFactory.createAnimalAvailable(
                species,breed, gender, weight, available, medicalRecord);

        assertNotNull(animalsAvailable);
        assertTrue(animalsAvailable.getAvailable());
        assertNotNull(animalsAvailable.getMedicalRecord());
        System.out.println(animalsAvailable);
        System.out.println("**Test Passed**");
    }
}