package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DogFactoryTest {

    private Set<MedicalRecord> medicalRecords;

    @BeforeEach
    void setUp() {
        medicalRecords = new HashSet<>();
        medicalRecords.add(new MedicalRecord.Builder()
                .setId(1L)
                .setVaccinationDate(LocalDate.now())
                .setMedication("Antibiotics")
                .setBehaviour("Calm")
                .setNextCheckup(LocalDate.now().plusMonths(6))
                .build());
    }

    @Test
    @Order(1)
    void testBuildDogWithId() {
        Dog dog = DogFactory.buildDog(1L, "Buddy", "Medium", 5, "Male", "Labrador", 101);

        assertNotNull(dog);
        assertEquals(1L, dog.getDogId());
        assertEquals("Buddy", dog.getName());
        assertEquals("Medium", dog.getSize());
        assertEquals(5, dog.getAge());
        assertEquals("Male", dog.getGender());
        assertEquals("Labrador", dog.getBreed());
        assertEquals(101, dog.getCageNumber());
    }

    @Test
    @Order(1)
    void testBuildDogWithoutId() {
        Dog dog = DogFactory.buildDog("Max", "Large", 3, "Male", "German Shepherd", 102);

        assertNotNull(dog);
        assertNotNull(dog.getDogId());
        assertEquals("Max", dog.getName());
        assertEquals("Large", dog.getSize());
        assertEquals(3, dog.getAge());
        assertEquals("Male", dog.getGender());
        assertEquals("German Shepherd", dog.getBreed());
        assertEquals(102, dog.getCageNumber());

    }

    @Test
    @Order(1)
    void testBuildDogWithInvalidData() {
        Dog dog = DogFactory.buildDog("", "Small", -1, "", "", 0);

        assertNull(dog);
    }

    @Test
    @Order(1)
    void testBuildDogWithNullMedicalRecords() {
        Dog dog = DogFactory.buildDog("", "Small", 2, "Female", "Poodle", 103);

        assertNull(dog);
    }
}
