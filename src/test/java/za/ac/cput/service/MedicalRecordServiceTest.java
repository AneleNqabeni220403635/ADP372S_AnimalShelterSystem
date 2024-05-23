package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalFactory;
import za.ac.cput.factory.MedicalRecordFactory;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordServiceTest {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private AnimalService animalService;
    private static MedicalRecord medicalRecord1;
    private static MedicalRecord medicalRecord2;
    private static Animal animal1;
    private static Animal animal2;

    @BeforeEach
    void setUp() {
        animal1 = AnimalFactory.buildAnimal("Leo", 5, "Lion", null);
        animal2 = AnimalFactory.buildAnimal("Roxy", 3, "Tiger", null);
        medicalRecord1 = MedicalRecordFactory.buildMedicalRecord(1L, LocalDate.now().minusMonths(2), "Antibiotics", "Calm", LocalDate.now().plusMonths(1));
        medicalRecord2 = MedicalRecordFactory.buildMedicalRecord(2L, LocalDate.now().minusMonths(3), "Vitamins", "Aggressive", LocalDate.now().plusMonths(2));
    }

    @Test
    void a_create() {
        Animal createdAnimal = animalService.create(animal1);
        assertNotNull(createdAnimal);
        MedicalRecord medicalRecordWithAnimal1 = new MedicalRecord.Builder()
                .copy(medicalRecord1)
                .setAnimal(createdAnimal.getAnimalCode())
                .build();
        MedicalRecord createdMedicalRecord = medicalRecordService.create(medicalRecordWithAnimal1);
        assertNotNull(createdMedicalRecord);
        assertEquals(medicalRecord1.getMedication(), createdMedicalRecord.getMedication());
    }

    @Test
    void b_read() {
        Animal createdAnimal = animalService.create(animal2);
        assertNotNull(createdAnimal);

        MedicalRecord medicalRecordWithAnimal = new MedicalRecord.Builder()
                .copy(medicalRecord2)
                .setAnimal(createdAnimal.getAnimalCode())
                .build();
        MedicalRecord createdMedicalRecord = medicalRecordService.create(medicalRecordWithAnimal);
        assertNotNull(createdMedicalRecord);

        MedicalRecord readMedicalRecord = medicalRecordService.read(createdMedicalRecord.getAnimal());
        assertNotNull(readMedicalRecord);
        assertEquals(createdMedicalRecord.getMedication(), readMedicalRecord.getMedication());
    }

    @Test
    void c_update() {
        Animal createdAnimal = animalService.create(animal1);
        assertNotNull(createdAnimal);

        MedicalRecord medicalRecordWithAnimal = new MedicalRecord.Builder()
                .copy(medicalRecord1)
                .setAnimal(createdAnimal.getAnimalCode())
                .build();
        MedicalRecord createdMedicalRecord = medicalRecordService.create(medicalRecordWithAnimal);
        assertNotNull(createdMedicalRecord);

        createdAnimal = new Animal.Builder()
                .copy(createdAnimal)
                .setAge(6)
                .build();
        Animal updatedAnimal = animalService.update(createdAnimal);
        assertEquals(6, updatedAnimal.getAge());
    }
    @Test
    void e_delete() {
        Animal createdAnimal = animalService.create(animal1);
        assertNotNull(createdAnimal);

        MedicalRecord medicalRecordWithAnimal = new MedicalRecord.Builder()
                .copy(medicalRecord1)
                .setAnimal(createdAnimal.getAnimalCode())
                .build();
        MedicalRecord createdMedicalRecord = medicalRecordService.create(medicalRecordWithAnimal);
        assertNotNull(createdMedicalRecord);

        medicalRecordService.delete(createdMedicalRecord.getAnimal());

        Animal deletedAnimal = animalService.read(createdAnimal.getAnimalCode());
        assertNull(deletedAnimal);

        MedicalRecord deletedMedicalRecord = medicalRecordService.read(createdMedicalRecord.getAnimal());
        assertNull(deletedMedicalRecord);
    }
    @Test
    void d_getall() {
        medicalRecordService.create(medicalRecord1);
        medicalRecordService.create(medicalRecord2);
        Set<MedicalRecord> medicalRecords = medicalRecordService.getall();
        assertTrue(medicalRecords.size() >= 2);
    }
}