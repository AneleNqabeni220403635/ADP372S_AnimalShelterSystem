package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantFactoryTest {
    private PetOwner petOwner;
    private Dog dog;
    private Cat cat;

    @BeforeEach
    void setUp() {
        petOwner = new PetOwner.Builder()
                .setId(1L)
                .setFirstName("Jack")
                .setContactNo("parrowj@cput.ac.za")
                .build();

        dog = new Dog.Builder()
                .setDogId(1L)
                .setName("Buddy")
                .setSize("Medium")
                .setAge(5)
                .setGender("Male")
                .setBreed("Labrador")
                .setCageNumber(101)
                .build();

        cat = new Cat.Builder()
                .setCatId(1L)
                .setName("Whiskers")
                .setSize("Small")
                .setAge(4)
                .setGender("Female")
                .setBreed("Siamese")
                .setCageNumber(201)
                .build();
    }

    @Test
    void testBuildApplicantWithId() {
        Applicant applicant = ApplicantFactory.buildApplicant(1L, petOwner, LocalDate.now(), dog, cat, "Pending");

        assertNotNull(applicant);
        assertEquals(1L, applicant.getId());
        assertEquals(petOwner, applicant.getPetOwner());
        assertEquals(LocalDate.now(), applicant.getApplicationDate());
        assertEquals(dog, applicant.getDogId());
        assertEquals(cat, applicant.getCatId());
        assertEquals("Pending", applicant.getStatus());
    }

    @Test
    void testBuildApplicantWithoutId() {
        Applicant applicant = ApplicantFactory.buildApplicant(petOwner, LocalDate.now(), dog, cat, "Approved");

        assertNotNull(applicant);
        assertNotNull(applicant.getId());
        assertEquals(petOwner, applicant.getPetOwner());
        assertEquals(LocalDate.now(), applicant.getApplicationDate());
        assertEquals(dog, applicant.getDogId());
        assertEquals(cat, applicant.getCatId());
        assertEquals("Approved", applicant.getStatus());
    }

    @Test
    void testBuildApplicantWithInvalidData() {
        Applicant applicant = ApplicantFactory.buildApplicant(null, null, null, null, "");

        assertNull(applicant);
    }

    @Test
    void testBuildApplicantWithNullPetOwner() {
        Applicant applicant = ApplicantFactory.buildApplicant(1L, null, LocalDate.now(), dog, cat, "Pending");

        assertNull(applicant);
    }

    @Test
    void testBuildApplicantWithNullApplicationDate() {
        Applicant applicant = ApplicantFactory.buildApplicant(1L, petOwner, null, dog, cat, "Pending");

        assertNull(applicant);
    }

    @Test
    void testBuildApplicantWithNullStatus() {
        Applicant applicant = ApplicantFactory.buildApplicant(1L, petOwner, LocalDate.now(), dog, cat, null);

        assertNull(applicant);
    }
}
