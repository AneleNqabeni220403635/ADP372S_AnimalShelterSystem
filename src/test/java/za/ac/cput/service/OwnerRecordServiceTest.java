package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.OwnerRecord;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.OwnerRecordFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)

public class  OwnerRecordServiceTest {

    @Autowired
    private OwnerRecordService ownerRecordService;

    private static OwnerRecord ownerRecord;

    @BeforeEach
    void setUp() {
        PetOwner petOwner = new PetOwner.Builder()
                .setId(1L)
                .setFirstName("John")
                .setLastName("Doe")
                .setContactNo("1234567890")
                .setEmailAddress("abc@gmail.com")
                .setStreetAddress("abc")
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
                .setCatId(2L)
                .setName("Whiskers")
                .setSize("Large")
                .setAge(3)
                .setGender("Female")
                .setBreed("Siamese")
                .setCageNumber(5)
                .build();

        ownerRecord = OwnerRecordFactory.buildOwnerRecord(1L,dog,cat,petOwner, LocalDate.now(),LocalDate.now().plusMonths(6));
    }

    @Test
    @Order(1)
    void testCreate() {
        OwnerRecord createdOwnerRecord = ownerRecordService.create(ownerRecord);
        assertNotNull(createdOwnerRecord);
        assertEquals(ownerRecord.getReturnDate(), createdOwnerRecord.getReturnDate());
        System.out.println("Created: " + createdOwnerRecord);
    }

    @Test
    @Order(2)
    void testRead() {
        OwnerRecord readOwnerRecord = ownerRecordService.read(ownerRecord.getId());
        assertNotNull(readOwnerRecord);
        assertEquals(ownerRecord.getReturnDate(), readOwnerRecord.getReturnDate());
        System.out.println("Read: " + readOwnerRecord);
    }

    @Test
    @Order(3)
    void testUpdate() {
        OwnerRecord updatedOwnerRecord = new OwnerRecord.Builder()
                .copy(ownerRecord)
                .setReturnDate(LocalDate.now().plusMonths(12))
                .build();
        OwnerRecord updated = ownerRecordService.update(updatedOwnerRecord);
        assertNotNull(updated);
        assertEquals(updatedOwnerRecord.getReturnDate(), updated.getReturnDate());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        Set<OwnerRecord> ownerRecords = ownerRecordService.getall();
        assertNotNull(ownerRecords);
        assertFalse(ownerRecords.isEmpty());
        System.out.println("All OwnerRecords: " + ownerRecords);
    }

    @Test
    @Order(5)
    void testDelete() {
        ownerRecordService.delete(ownerRecord.getId());
        OwnerRecord deleted = ownerRecordService.read(ownerRecord.getId());
        assertNull(deleted);
        System.out.println("Deleted");
    }
}
