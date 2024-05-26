package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnimalFactoryTest {
    @Test
    public void testBuildAnimal() {
        String name = "Leo";
        int age = 5;
        String type = "Dog";

        Set<MedicalRecord> medicalRecords = new HashSet<>();
        MedicalRecord medicalRecord1 = new MedicalRecord.Builder()
                .setVaccinationDate(LocalDate.now())
                .setMedication("Antibiotics")
                .setBehaviour("Calm")
                .setNextCheckup(LocalDate.now().plusMonths(6))
                .build();
        medicalRecords.add(medicalRecord1);

        Animal animal = AnimalFactory.buildAnimal(name, age, type, medicalRecords);

        assertNotNull(animal);
        System.out.println(animal);
        System.out.println("**Test Passed**");
    }
}