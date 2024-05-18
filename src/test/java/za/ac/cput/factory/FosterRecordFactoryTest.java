package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.FosterRecord;

import static org.junit.jupiter.api.Assertions.*;

class FosterRecordFactoryTest {

    @Test
    void testCreateFosterRecordSuccess() {
        // Prepare test data
        String animalId = "123";
        String animalName = "Buddy";
        String breed = "Golden Retriever";
        String age = "2 years";
        String gender = "Male";
        String healthStatus = "Healthy";
        String behaviorNotes = "Friendly";
        String specialCare = "None";
        String dailyRoutine = "Morning walk";
        String incidentReport = "No incidents";

        // Create FosterRecord object using the factory
        FosterRecord fosterRecord = FosterRecordFactory.createFosterRecord(
                animalId,
                animalName,
                breed,
                age,
                gender,
                healthStatus,
                behaviorNotes,
                specialCare,
                dailyRoutine,
                incidentReport
        );

        // Verify the object was created successfully with correct attributes
        assertNotNull(fosterRecord);
        assertEquals(animalId, fosterRecord.getAnimalId());
        assertEquals(animalName, fosterRecord.getAnimalName());
        assertEquals(breed, fosterRecord.getBreed());
        assertEquals(age, fosterRecord.getAge());
        assertEquals(gender, fosterRecord.getGender());
        assertEquals(healthStatus, fosterRecord.getHealthStatus());
        assertEquals(behaviorNotes, fosterRecord.getBehaviorNotes());
        assertEquals(specialCare, fosterRecord.getSpecialCare());
        assertEquals(dailyRoutine, fosterRecord.getDailyRoutine());
        assertEquals(incidentReport, fosterRecord.getIncidentReport());
    }

    @Test
    void testCreateFosterRecordFailure() {
        // Prepare test data with an invalid animal ID (null or empty)
        String animalId = "";
        String animalName = "Buddy";
        String breed = "Golden Retriever";
        String age = "2 years";
        String gender = "Male";
        String healthStatus = "Healthy";
        String behaviorNotes = "Friendly";
        String specialCare = "None";
        String dailyRoutine = "Morning walk";
        String incidentReport = "No incidents";

        // Verify that an exception is thrown due to invalid animal ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FosterRecordFactory.createFosterRecord(
                    animalId,
                    animalName,
                    breed,
                    age,
                    gender,
                    healthStatus,
                    behaviorNotes,
                    specialCare,
                    dailyRoutine,
                    incidentReport
            );
        });

        String expectedMessage = "Animal ID cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Add more tests for other validation scenarios if needed
}




