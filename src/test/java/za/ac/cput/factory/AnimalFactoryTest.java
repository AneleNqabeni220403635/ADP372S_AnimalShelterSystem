package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class AnimalFactoryTest {
   @Test
    public void testBuildAnimal() {
       String name = "Leo";
       int age = 5;
       String type = "Dog";

      MedicalRecord medicalRecord = new MedicalRecord.Builder()
              .setAnimal(1L)
              .setVaccinationDate(LocalDate.now())
              .setMedication("Antibiotics")
              .setBehaviour("Calm")
              .setNextCheckup(LocalDate.now().plusMonths(6))
              .build();

      Animal animal = AnimalFactory.buildAnimal(name, age, type, medicalRecord);

      assertNotNull(animal);
      System.out.println(animal);
      System.out.println("**Test Passed**");
    }

}
