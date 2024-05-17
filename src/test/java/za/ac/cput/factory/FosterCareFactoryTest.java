package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.domain.FosterRecord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FosterCareFactoryTest {

    @Test
    void testCreateFosterCareSuccess() {
        // Prepare test data
        String caregiverName = "John Doe";
        String caregiverContact = "1234567890";
        String caregiverAddress = "123 Main St";
        String homeType = "Apartment";
        String capacity = "5";
        String experienceLevel = "High";
        String currentStatus = "Active";
        String notes = "Experienced caregiver";
        List<FosterRecord> fosterRecords = new ArrayList<>();  // Empty list for this test

        // Create FosterCare object using the factory
        FosterCare fosterCare = FosterCareFactory.createFosterCare(
                caregiverName,
                caregiverContact,
                caregiverAddress,
                homeType,
                capacity,
                experienceLevel,
                currentStatus,
                notes,
                fosterRecords
        );

        assertNotNull(fosterCare);
        assertEquals(caregiverName, fosterCare.getCaregoverName());
        assertEquals(caregiverContact, fosterCare.getCaregiverContact());
        assertEquals(caregiverAddress, fosterCare.getCaregiverAddress());
        assertEquals(homeType, fosterCare.getHometype());
        assertEquals(capacity, fosterCare.getCapacity());
        assertEquals(experienceLevel, fosterCare.getExperienceLevel());
        assertEquals(currentStatus, fosterCare.getCurrentStatus());
        assertEquals(notes, fosterCare.getNotes());
        assertEquals(fosterRecords, fosterCare.getFosterRecords());
    }

    @Test
    void testCreateFosterCareFailure() {
        String caregiverName = "";
        String caregiverContact = "1234567890";
        String caregiverAddress = "123 Main St";
        String homeType = "Apartment";
        String capacity = "5";
        String experienceLevel = "High";
        String currentStatus = "Active";
        String notes = "Experienced caregiver";
        List<FosterRecord> fosterRecords = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FosterCareFactory.createFosterCare(
                    caregiverName,
                    caregiverContact,
                    caregiverAddress,
                    homeType,
                    capacity,
                    experienceLevel,
                    currentStatus,
                    notes,
                    fosterRecords
            );
        });

        String expectedMessage = "Caregiver name cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}


