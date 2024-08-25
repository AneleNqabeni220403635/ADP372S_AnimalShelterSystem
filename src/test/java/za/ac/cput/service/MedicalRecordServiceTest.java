package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.MedicalRecordFactory;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalRecordServiceTest {

    @Autowired
    private MedicalRecordService medicalRecordService;

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

    private final MedicalRecord medicalRecord = MedicalRecordFactory.buildMedicalRecord(1L,dog,cat,LocalDate.now(),"Med1","Good",LocalDate.now().plusMonths(6),"Annual checkup");

    @Test
    @Order(1)
    void testCreate() {
        MedicalRecord createdRecord = medicalRecordService.create(medicalRecord);
        assertNotNull(createdRecord);
        assert medicalRecord != null;
        assertEquals(medicalRecord.getDescription(), createdRecord.getDescription());
        System.out.println("Created: " + createdRecord);
    }

    @Test
    @Order(2)
    void testRead() {
        MedicalRecord readRecord = medicalRecordService.read(medicalRecord.getId());
        assertNotNull(readRecord);
        assertEquals(medicalRecord.getDescription(), readRecord.getDescription());
        System.out.println("Read: " + readRecord);
    }

    @Test
    @Order(3)
    void testUpdate() {
        assert medicalRecord != null;
        MedicalRecord updatedRecord = new MedicalRecord.Builder()
                .copy(medicalRecord)
                .setDescription("Updated checkup")
                .build();
        MedicalRecord updated = medicalRecordService.update(updatedRecord);
        assertNotNull(updated);
        assertEquals("Updated checkup", updated.getDescription());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        Set<MedicalRecord> records = medicalRecordService.getall();
        assertFalse(records.isEmpty());
        System.out.println("All Medical Records: " + records);
    }

    @Test
    @Order(5)
    void testDelete() {
        assert medicalRecord != null;
        medicalRecordService.delete(medicalRecord.getId());
        MedicalRecord deleted = medicalRecordService.read(medicalRecord.getId());
        assertNull(deleted);
        System.out.println("Deleted");
    }

}
