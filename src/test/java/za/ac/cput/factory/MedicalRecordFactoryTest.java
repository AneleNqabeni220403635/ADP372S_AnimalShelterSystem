package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordFactoryTest {

    @Test
    void testBuildMedicalRecordWithId() {
        Dog dog = new Dog.Builder().setDogId(1L).build();
        MedicalRecord record = MedicalRecordFactory.buildMedicalRecord(1L, dog, null, LocalDate.now(), "Antibiotics", "Calm", LocalDate.now().plusMonths(6), "Routine check");

        assertNotNull(record);
        assertEquals(1L, record.getId());
        assertEquals(dog, record.getDog());
        assertEquals("Antibiotics", record.getMedication());
    }

    @Test
    void testBuildMedicalRecordWithoutId() {
        Dog dog = new Dog.Builder().setDogId(1L).build();
        MedicalRecord record = MedicalRecordFactory.buildMedicalRecord(dog, null, LocalDate.now(), "Vaccination", "Active", LocalDate.now().plusMonths(6), "Initial vaccination");

        assertNotNull(record);
        assertNotNull(record.getId());
        assertEquals(dog, record.getDog());
    }

    @Test
    void testBuildMedicalRecordWithInvalidData() {
        MedicalRecord record = MedicalRecordFactory.buildMedicalRecord(null, null, null, "", "", null, "");

        assertNull(record);
    }
}
