package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.MedicalRecord;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordFactoryTest {

    private Dog dog;

    @BeforeEach
    public void setUp() {
        dog = DogFactory.buildDog("Rex", "Large", 4, "Male", "German Shepherd", 104, null);
    }

    @Test
    public void testBuildMedicalRecordWithIdSuccess() {
        Long id = 1L;
        LocalDate vaccinationDate = LocalDate.now();
        String medication = "Antibiotics";
        String behaviour = "Aggressive";
        LocalDate nextCheckup = LocalDate.now().plusMonths(6);

        MedicalRecord medicalRecord = MedicalRecordFactory.buildMedicalRecord(id, dog, vaccinationDate, medication, behaviour, nextCheckup);
        System.out.println("Passed: Medical Record ID Success passed***");

    }
}
