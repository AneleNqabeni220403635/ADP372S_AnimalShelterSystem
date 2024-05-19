package za.ac.cput.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.MedicalRecordFactory;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordServiceTest {
    @Autowired
    private MedicalRecordService service;

    private static final MedicalRecord medicalRecord = MedicalRecordFactory.buildMedicalRecord(
            12345L,
            LocalDate.of(2023, 5, 19),
            "Vaccine A",
            "Aggressive",
            LocalDate.of(2023, 11, 19)
    );

    @Test
    @Order(1)
    void create() {
        MedicalRecord created = service.create(medicalRecord);
        assertNotNull(created);
        assertEquals(medicalRecord.getAnimal(), created.getAnimal());
        System.out.println("Created: " + created);
    }

    @Test
    @Order(2)
    void read() {
        MedicalRecord read = service.read(medicalRecord.getAnimal());
        assertNotNull(read);
        assertEquals(medicalRecord.getAnimal(), read.getAnimal());
        System.out.println("Read: " + read);
    }

    @Test
    @Order(3)
    void update() {
        MedicalRecord updatedRecord = new MedicalRecord.Builder().copy(medicalRecord)
                .setMedication("Vaccine B")
                .build();
        MedicalRecord updated = service.update(updatedRecord);
        assertNotNull(updated);
        assertEquals(updatedRecord.getMedication(), updated.getMedication());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        boolean success = service.delete(medicalRecord.getAnimal());
        assertTrue(success);
        MedicalRecord deleted = service.read(medicalRecord.getAnimal());
        assertNull(deleted);
        System.out.println("Deleted: " + deleted);
    }

    @Test
    @Order(5)
    void findAll() {
        Set<MedicalRecord> records = service.findAll();
        assertNotNull(records);
        assertFalse(records.isEmpty());
        System.out.println("All Medical Records: " + records);
    }
}