package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordFactoryTest {

    private Animal animal;

    @BeforeEach
    public void setUp() {
        animal = new Animal.Builder()
                .setAnimalCode(1L)
                .setName("Buddy")
                .setAge(5)
                .setType("Dog")
                .build();
    }

    @Test
    public void testBuildMedicalRecordSuccess() {
        LocalDate vaccinationDate = LocalDate.now().plusMonths(1);
        String medication = "Antibiotics";
        String behaviour = "Calm";
        LocalDate nextCheckup = LocalDate.now().plusMonths(3);

        MedicalRecord medicalRecord = MedicalRecordFactory.buildMedicalRecord(animal, vaccinationDate, medication, behaviour, nextCheckup);

        assertNotNull(medicalRecord);
        assertEquals(animal, medicalRecord.getAnimal());
        assertEquals(vaccinationDate, medicalRecord.getVaccinationDate());
        assertEquals(medication, medicalRecord.getMedication());
        assertEquals(behaviour, medicalRecord.getBehaviour());
        assertEquals(nextCheckup, medicalRecord.getNextCheckup());
    }
}