package za.ac.cput.service;

import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.ApplicantFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicantServiceTest {

    @Autowired
    private ApplicantService applicantService;

    private static Applicant applicant;

    @BeforeEach
    public void setUp() {

        PetOwner petOwner = new PetOwner.Builder()
                .setId(1L)
                .setFirstName("Jack")
                .setLastName("Parrow")
                .setContactNo("0721234567")
                .setEmailAddress("parrowj@cput.ac.za")
                .setStreetAddress("80 Constitution Street, Cape Town, 7925")
                .build();

        Dog dog = new Dog.Builder()
                .setDogId(1L)
                .setName("Buddy")
                .setSize("Large")
                .setAge(5)
                .setGender("Male")
                .setBreed("Golden Retriever")
                .setCageNumber(0)
                .build();

        Cat cat = new Cat.Builder()
                .setCatId(1L)
                .setName("Whiskers")
                .setSize("Large")
                .setAge(3)
                .setGender("Female")
                .setBreed("Siamese")
                .setCageNumber(5)
                .build();


        applicant = ApplicantFactory.buildApplicant(1L,petOwner, LocalDate.now(), dog, cat, "Pending");
    }

    @Test
    @Order(1)
    void testCreate() {
        Applicant createdApplicant = applicantService.create(applicant);
        assertNotNull(createdApplicant);
        assertEquals(applicant.getId(), createdApplicant.getId());
        System.out.println("Created: " + createdApplicant);
    }

    @Test
    @Order(2)
    void testRead() {
        Applicant readApplicant = applicantService.read(applicant.getId());
        assertNotNull(readApplicant);
        assertEquals(applicant.getId(), readApplicant.getId());
        System.out.println("Read: " + readApplicant);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Applicant updatedApplicant = new Applicant.Builder()
                .copy(applicant)
                .setStatus("Approved")
                .build();
        Applicant updated = applicantService.update(updatedApplicant);
        assertNotNull(updated);
        assertEquals("Approved", updated.getStatus());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        Set<Applicant> applicants = applicantService.getall();
        assertNotNull(applicants);
        assertFalse(applicants.isEmpty());
        System.out.println("All Applicants: " + applicants);
    }

    @Test
    @Order(5)
    void testDelete() {
        applicantService.delete(applicant.getId());
        Applicant deleted = applicantService.read(applicant.getId());
        assertNull(deleted);
        System.out.println("Deleted");
    }


}