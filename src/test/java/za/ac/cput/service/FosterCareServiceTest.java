package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.FosterCare;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class FosterCareServiceTest {
   @Autowired
    private FosterCareService service;

    private static FosterCare fosterCare = new FosterCare.Builder()
            .setCaregiverName("Siphosethu")
            .setCaregiverContact(123456798)
            .setCaregiverAddress("20 Plane Road")
            .setHomeType("Apartment")
            .setCapacity("6")
            .setExperienceLevel("Experienced")
            .setCurrentStatus("Active")
            .setNotes("No special notes")
            .Build();

    @Test
    @Order(1)
    void create() {
        FosterCare created = service.create(fosterCare);
        assertNotNull(created);
        assertEquals(fosterCare.getCaregiverName(), created.getCaregiverName());
        System.out.println("Created: " + created);
    }

    @Test
    @Order(2)
    void read() {
        FosterCare read = service.read(fosterCare.getCaregiverName());
        assertNotNull(read);
        assertEquals(fosterCare.getCaregiverName(), read.getCaregiverName());
        System.out.println("Read: " + read);
    }

    @Test
    @Order(3)
    void update() {
        FosterCare updatedFosterCare = new FosterCare.Builder().copy(fosterCare)
                .setCaregiverName("Ryan")
                .Build();
        FosterCare updated = service.update(updatedFosterCare);
        assertNotNull(updated);
        assertEquals(updatedFosterCare.getCaregiverName(), updated.getCaregiverName());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        boolean success = service.delete(fosterCare.getCaregiverName());
        assertTrue(success);
        FosterCare deleted = service.read(fosterCare.getCaregiverName());
        assertNull(deleted);
        System.out.println("Deleted: " + deleted);
    }

    @Test
    @Order(5)
    void findAll() {
        Set<FosterCare> fosterRecord = service.findAll();
        assertNull(fosterRecord);
        assertFalse(fosterRecord.isEmpty());
        System.out.println("All Records: " + fosterRecord);
    }
}