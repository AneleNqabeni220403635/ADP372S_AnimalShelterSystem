package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalRecordServiceTest {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private DogService dogService;

    private static Dog roxy;
    private static MedicalRecord medicalRecordForRoxy;

    @Test
    @Order(1)
    void testCreateDog() {
        long generatedDogId = Helper.generateDogId();

        Dog roxy = new Dog.Builder()
                .setDogId(generatedDogId)
                .setAge(8)
                .setBreed("Pitbull")
                .setCageNumber(107)
                .setGender("Female")
                .setName("Roxy")
                .setSize("Medium")
                .build();

        roxy = dogService.create(roxy);
        assertNotNull(roxy);
        assertNotNull(roxy.getDogId());
        System.out.println("Created Dog: " + roxy);
        System.out.println("Dog ID generated: " + roxy.getDogId());
    }

    @Test
    @Order(2)
    void testCreateAndReadMedicalRecord() {
        long generatedDogId = Helper.generateDogId();
        Dog roxy = new Dog.Builder()
                .setDogId(generatedDogId)
                .setAge(8)
                .setBreed("Pitbull")
                .setCageNumber(107)
                .setGender("Female")
                .setName("Roxy")
                .setSize("Medium")
                .build();
        roxy = dogService.create(roxy);


        medicalRecordForRoxy = new MedicalRecord.Builder()
                .setDog(roxy)
                .setBehaviour("Calm")
                .setMedication("Vitamins")
                .setNextCheckup(LocalDate.of(2025, 12, 1))
                .setVaccinationDate(LocalDate.of(2025, 6, 1))
                .build();
        medicalRecordForRoxy = medicalRecordService.create(medicalRecordForRoxy);
        assertNotNull(medicalRecordForRoxy);
        assertNotNull(medicalRecordForRoxy.getId());
        assertEquals(roxy.getName(), medicalRecordForRoxy.getDog().getName());
        System.out.println("Created Medical Record: " + medicalRecordForRoxy);

        MedicalRecord readMedicalRecord = medicalRecordService.read(medicalRecordForRoxy.getId());
        assertNotNull(readMedicalRecord);
        assertEquals(medicalRecordForRoxy.getId(), readMedicalRecord.getId());
        System.out.println("Read Medical Record: " + readMedicalRecord);
    }

    @Test
    @Order(3)
    void testUpdateMedicalRecord() {
        MedicalRecord updatedRecord = new MedicalRecord.Builder()
                .copy(medicalRecordForRoxy)
                .setBehaviour("Aggressive")
                .build();

        MedicalRecord updated = medicalRecordService.update(updatedRecord);
        assertNotNull(updated);
        assertEquals("Aggressive", updated.getBehaviour());
        System.out.println("Updated Medical Record: " + updated);
    }

    @Test
    @Order(4)
    void testDeleteMedicalRecord() {
        medicalRecordService.delete(medicalRecordForRoxy.getId());
        MedicalRecord deletedMedicalRecord = medicalRecordService.read(medicalRecordForRoxy.getId());
        assertNull(deletedMedicalRecord);
        System.out.println("Deleted Medical Record");
    }
    @Test
    @Order(5)
    void testGetAllMedicalRecords() {
        Set<MedicalRecord> medicalRecords = medicalRecordService.getall();
        assertNotNull(medicalRecords);
        System.out.println("All Medical Records: " + medicalRecords);
    }
}
