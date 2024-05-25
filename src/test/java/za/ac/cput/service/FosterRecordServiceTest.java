package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import za.ac.cput.domain.FosterRecord;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class FosterRecordServiceTest {
    @Autowired
    private FosterRecordService service;

    private static FosterRecord fosterRecord = new FosterRecord.Builder()
            .setAnimalId("224")
            .setAnimalName("Frosty")
            .setBreed("Rottweiller")
            .setAge(10)
            .setGender("female")
            .setHealthStatus("Healthy")
            .setBehaviorNotes("Playful")
            .setSpecialCare("None")
            .setDailyRoutine("Training")
            .setIncidentReport("No incident")
            .build();

    @Test
    @Order(1)
    void create() {
        FosterRecord created = service.create(fosterRecord);
        assertNotNull(created);
        assertEquals(fosterRecord.getAnimalId(), created.getAnimalId());
        System.out.println("Created: " + created);
    }

    @Test
    @Order(2)
    void read() {
        FosterRecord read = service.read(fosterRecord.getAnimalId());
        assertNotNull(read);
        assertEquals(fosterRecord.getAnimalId(), read.getAnimalId());
        System.out.println("Read: " + read);
    }


    @Test
    @Order(3)
    void update() {
        FosterRecord updatedFosterRecord = new FosterRecord.Builder().copy(fosterRecord)
                .setAnimalId("358")
                .build();
        FosterRecord updated = service.update(updatedFosterRecord);
        assertNotNull(updated);
        assertEquals(updatedFosterRecord.getAnimalId(), updated.getAnimalId());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        FosterRecord success = service.delete(fosterRecord.getAnimalId());
        assertNotNull(success);
        FosterRecord deleted = service.read(fosterRecord.getAnimalId());
        assertNull(deleted);
        System.out.println("Deleted: " + deleted);
    }

}