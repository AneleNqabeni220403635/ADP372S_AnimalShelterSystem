package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.domain.FosterRecord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FosterCareFactoryTest {

    @Test
    public void testCreateFosterCare() {
                String caregiverName = "Lindiwe Magagula";
                int caregiverContact = 123456789;
                String caregiverAddress = " Main Street";
                String homeType = "House";
                String capacity = "3";
                String experienceLevel = "Experienced";
                String currentStatus = "Active";
                String notes = "No special notes";

                List<FosterRecord> fosterRecords = new ArrayList<>();


                FosterCare fosterCare = new FosterCare.Builder()
                        .setCaregiverName(caregiverName)
                        .setCaregiverContact(caregiverContact)
                        .setCaregiverAddress(caregiverAddress)
                        .setHomeType(homeType)
                        .setCapacity(capacity)
                        .setExperienceLevel(experienceLevel)
                        .setCurrentStatus(currentStatus)
                        .setNotes(notes)
                        .Build();

                assertNotNull(fosterCare);
                assertEquals(caregiverName, fosterCare.getCaregiverName());
                assertEquals(caregiverContact, fosterCare.getCaregiverContact());
                assertEquals(caregiverAddress, fosterCare.getCaregiverAddress());
                assertEquals(homeType, fosterCare.getHomeType());
                assertEquals(capacity, fosterCare.getCapacity());
                assertEquals(experienceLevel, fosterCare.getExperienceLevel());
                assertEquals(currentStatus, fosterCare.getCurrentStatus());
                assertEquals(notes, fosterCare.getNotes());
                assertEquals(fosterRecords, fosterCare.getFosterRecords());
            }

            @Test
            void buildFosterCare_InvalidInput_NullReturned() {

                FosterCare fosterCare = FosterCareFactory.buildFosterCare(
                        null,
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                assertNull(fosterCare);
            }
        }



