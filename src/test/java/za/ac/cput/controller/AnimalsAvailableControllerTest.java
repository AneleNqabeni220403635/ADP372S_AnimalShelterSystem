package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalsAvailableFactory;
import za.ac.cput.service.AnimalsAvailableService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnimalsAvailableControllerTest {

    @Autowired
    private AnimalsAvailableController animalsAvailableController;

    @Autowired
    private AnimalsAvailableService animalsAvailableService;

    private MedicalRecord medicalRecord1;
    private MedicalRecord medicalRecord2;
    private AnimalsAvailable animalsAvailable1;
    private AnimalsAvailable animalsAvailable2;

    @BeforeEach
    void setUp() {
        animalsAvailable1 = AnimalsAvailableFactory.createAnimalAvailable("Felis catus", "British ShortHair", "Male", 4.34, true, medicalRecord1);
        animalsAvailable2 = AnimalsAvailableFactory.createAnimalAvailable("", "", "Female", 5.4, true, medicalRecord2);
    }

    @Test
    void create() {
        AnimalsAvailable created = animalsAvailableController.create(animalsAvailable1);
        assertNotNull(created);
        assertEquals(animalsAvailable1.getAnimalCode(), created.getAnimalCode());
    }

    @Test
    void read() {
        AnimalsAvailable created = animalsAvailableController.create(animalsAvailable2);
        AnimalsAvailable read = animalsAvailableController.read(created.getAnimalCode());
        assertNotNull(read);
        assertEquals(created.getAnimalCode(), read.getAvailable());
    }

    @Test
    void update() {
        AnimalsAvailable created = animalsAvailableController.create(animalsAvailable1);
        created = new AnimalsAvailable.Builder()
                .copy(created)
                .setGender("Male")
                .build();
        AnimalsAvailable updated = animalsAvailableController.update(created);
        assertEquals("Male", updated.getGender());
    }

    @Test
    void delete() {
        AnimalsAvailable created = animalsAvailableController.create(animalsAvailable1);
        animalsAvailableController.delete(created.getAnimalCode());
        AnimalsAvailable deleted = animalsAvailableService.read(created.getAnimalCode());
        assertNull(deleted);
    }

    @Test
    void getAll() {
        animalsAvailableController.create(animalsAvailable1);
        animalsAvailableController.create(animalsAvailable2);
        Set<AnimalsAvailable> animalsAvailable = animalsAvailableController.getall();
        assertTrue(animalsAvailable.size() >= 2);
    }
}