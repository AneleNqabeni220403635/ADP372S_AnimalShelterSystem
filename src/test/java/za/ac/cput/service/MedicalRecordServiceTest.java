package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalRecordServiceTest {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private AnimalService animalService;

    private static Animal snow;
    private static MedicalRecord medicalRecordForSnow;

    @Test
    @Order(1)
    void testCreateAnimal() {
        snow = new Animal.Builder()
                .setAnimalCode(10L)
                .setName("Snow")
                .setAge(7)
                .setType("Wolf")
                .build();
        snow = animalService.create(snow);
        assertNotNull(snow);
        assertEquals(10L, snow.getAnimalCode());
        assertEquals("Snow", snow.getName());
        assertEquals(7, snow.getAge());
        assertEquals("Wolf", snow.getType());
        System.out.println("Created Animal: " + snow);
    }

    @Test
    @Order(2)
    void testCreateAndReadMedicalRecord() {
        medicalRecordForSnow = new MedicalRecord.Builder()
                .setAnimal(snow)
                .setBehaviour("Calm")
                .setMedication("Vitamins")
                .setNextCheckup(LocalDate.of(2024, 12, 1))
                .setVaccinationDate(LocalDate.of(2024, 6, 1))
                .build();
        medicalRecordForSnow = medicalRecordService.create(medicalRecordForSnow);
        assertNotNull(medicalRecordForSnow);
        assertEquals(snow.getAnimalCode(), medicalRecordForSnow.getAnimal());
        System.out.println("Created Medical Record: " + medicalRecordForSnow);

        MedicalRecord readMedicalRecord = medicalRecordService.read(medicalRecordForSnow.getId());
        assertNotNull(readMedicalRecord);
        assertEquals(medicalRecordForSnow.getId(), readMedicalRecord.getId());
        System.out.println("Read Medical Record: " + readMedicalRecord);
    }

    @Test
    @Order(3)
    void testUpdateMedicalRecord() {
        medicalRecordForSnow = new MedicalRecord.Builder()
                .copy(medicalRecordForSnow)
                .setBehaviour("Aggressive")
                .build();
        MedicalRecord updated = medicalRecordService.update(medicalRecordForSnow);
        assertNotNull(updated);
        assertEquals("Aggressive", updated.getBehaviour());
        System.out.println("Updated Medical Record: " + updated);
    }

    @Test
    @Order(4)
    void testDeleteMedicalRecord() {
        medicalRecordService.delete(medicalRecordForSnow.getId());
        MedicalRecord deletedMedicalRecord = medicalRecordService.read(medicalRecordForSnow.getId());
        assertNull(deletedMedicalRecord);
        System.out.println("Deleted Medical Record");
    }

    @Test
    @Order(5)
    void testGetAllMedicalRecords() {
        Set<MedicalRecord> medicalRecords = medicalRecordService.getall();
        assertNotNull(medicalRecords);
        assertFalse(medicalRecords.isEmpty());
        System.out.println("All Medical Records: " + medicalRecords);
    }
}
