package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.MethodName.class)
class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    private static Animal animal1;
    private static Animal animal2;
    private static MedicalRecord medicalRecord1;
    private static MedicalRecord medicalRecord2;

    @BeforeEach
    void setUp() {
        medicalRecord1 = MedicalRecordFactory.buildMedicalRecord(1L, LocalDate.now().minusMonths(2), "Antibiotics", "Calm", LocalDate.now().plusMonths(1));
        medicalRecord2 = MedicalRecordFactory.buildMedicalRecord(2L, LocalDate.now().minusMonths(3), "Vitamins", "Aggressive", LocalDate.now().plusMonths(2));

        animal1 = AnimalFactory.buildAnimal("Leo", 5, "Lion", medicalRecord1);
        animal2 = AnimalFactory.buildAnimal("Roxy", 3, "Tiger", medicalRecord2);

        medicalRecordService.create(medicalRecord1);
        medicalRecordService.create(medicalRecord2);
    }

    @Test
    void a_create() {
        if (medicalRecord1 == null) throw new AssertionError();
        if (animal1 == null) throw new AssertionError();

        medicalRecordService.create(medicalRecord1);
        Animal created = animalService.create(animal1);
        assertNotNull(created);
        assertEquals(animal1.getName(), created.getName());
    }

    @Test
    void b_read() {
        medicalRecordService.create(medicalRecord2);
        Animal created = animalService.create(animal2);

        Animal read = animalService.read(created.getAnimalCode());
        assertNotNull(read);
        assertEquals(created.getName(), read.getName());
    }

    @Test
    void c_update() {
        medicalRecordService.create(medicalRecord1);
        Animal created = animalService.create(animal1);
        created = new Animal.Builder()
                .copy(created)
                .setAge(6)
                .build();
        Animal updated = animalService.update(created);
        assertEquals(6, updated.getAge());
    }
    @Test
    void e_delete() {
        medicalRecordService.create(medicalRecord1);
        Animal created = animalService.create(animal1);
        assertNotNull(created);
        animalService.delete(created.getAnimalCode());
        Animal deleted = animalService.read(created.getAnimalCode());
        assertNull(deleted);

        MedicalRecord deletedRecord = medicalRecordService.read(medicalRecord1.getAnimal());
        assertNull(deletedRecord);
    }
    @Test
    void d_getall() {
         System.out.println(animalService.getall());
        System.out.println(medicalRecordService.getall());
    }


}