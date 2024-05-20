package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
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
    private MedicalRecordService medicalRecordService;

    private static MedicalRecord medicalRecord1;
    private static MedicalRecord medicalRecord2;

    @BeforeEach
    void setUp() {
        medicalRecord1 = MedicalRecordFactory.buildMedicalRecord(1L, LocalDate.now().minusMonths(2), "Antibiotics", "Calm", LocalDate.now().plusMonths(1));
        medicalRecord2 = MedicalRecordFactory.buildMedicalRecord(2L, LocalDate.now().minusMonths(3), "Vitamins", "Aggressive", LocalDate.now().plusMonths(2));
    }

    @Test
    void a_create() {
        MedicalRecord created = medicalRecordService.create(medicalRecord1);
        assertNotNull(created);
        assertEquals(medicalRecord1.getMedication(), created.getMedication());
    }

    @Test
    void b_read() {
        MedicalRecord created = medicalRecordService.create(medicalRecord2);
        MedicalRecord read = medicalRecordService.read(created.getAnimal());
        assertNotNull(read);
        assertEquals(created.getMedication(), read.getMedication());
    }

    @Test
    void c_update() {
        MedicalRecord created = medicalRecordService.create(medicalRecord1);
        created = new MedicalRecord.Builder()
                .copy(created)
                .setBehaviour("Aggressive")
                .build();
        MedicalRecord updated = medicalRecordService.update(created);
        assertEquals("Aggressive", updated.getBehaviour());
    }

    @Test
    void d_getall() {
        medicalRecordService.create(medicalRecord1);
        medicalRecordService.create(medicalRecord2);
        Set<MedicalRecord> medicalRecords = medicalRecordService.getall();
        assertTrue(medicalRecords.size() >= 2);
    }
}