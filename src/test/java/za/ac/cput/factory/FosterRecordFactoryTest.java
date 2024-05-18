package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.domain.FosterRecord;

import static org.junit.jupiter.api.Assertions.*;

class FosterRecordFactoryTest {

    @Test
    public void testCreateFosterRecord() {
                String animalId = "123";
                String animalName = "Dog";
                String breed = "Bulldog";
                int age = 3;
                String gender = "Male";
                String healthStatus = "Healthy";
                String behaviorNotes = "Friendly";
                String specialCare = "None";
                String dailyRoutine = "Regular walks";
                String incidentReport = "No incidents";


        FosterRecord fosterRecord = new FosterRecord.Builder()
                .setAnimalId(animalId)
                .setAnimalName(animalName)
                .setBreed(breed)
                .setAge(age)
                .setGender(gender)
                .setHealthStatus(healthStatus)
                .setBehaviorNotes(behaviorNotes)
                .setSpecialCare(specialCare)
                .setDailyRoutine(dailyRoutine)
                .setIncidentReport(incidentReport)
                .build();


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
            void buildFosterRecord_InvalidInput_NullReturned() {
                // Act
                FosterRecord fosterRecord = FosterRecordFactory.buildFosterRecord(
                        null,
                        "",
                        "",
                        0,
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                );

                assertNull(fosterRecord);
            }
        }


