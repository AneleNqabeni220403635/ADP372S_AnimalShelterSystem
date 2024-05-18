package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.MedicalRecord;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MedicalRecordFactoryTest {
    @Test
    void buildMedicalRecordWithAllDetails() {
        long animal = 12345L;
        LocalDate vaccinationDate = LocalDate.now().plusDays(1);
        String medication = "Vaccine A";
        String behaviour = "Aggressive";
        LocalDate nextCheckup = LocalDate.now().plusMonths(6);

        MedicalRecord medicalRecord = MedicalRecordFactory.buildMedicalRecord(animal, vaccinationDate, medication, behaviour, nextCheckup);
        assertNotNull(medicalRecord);
        assertEquals(animal, medicalRecord.getAnimal());
        assertEquals(vaccinationDate, medicalRecord.getVaccinationDate());
        assertEquals(medication, medicalRecord.getMedication());
        assertEquals(behaviour, medicalRecord.getBehaviour());
        assertEquals(nextCheckup, medicalRecord.getNextCheckup());

        System.out.println(medicalRecord);
        System.out.println("**Test Passed**");
    }
}
