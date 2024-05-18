package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class AnimalFactoryTest {
   @Test
    public void testCreateAnimal() {
       long animalCode = 2;
       String name = "Leo";
       int age = 5;
       String type = "Dog";
       MedicalRecord medicalRecord = MedicalRecordFactory.buildDefaultMedicalRecord(animalCode);

       Animal animal = AnimalFactory.buildAnimal(animalCode, name, age, type, medicalRecord);
       assertNotNull(animal);
       System.out.println(animal);
       System.out.println("**Test Passed**");
    }

}
