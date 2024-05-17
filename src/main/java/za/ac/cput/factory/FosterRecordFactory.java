package za.ac.cput.factory;
import za.ac.cput.domain.FosterRecord;
public class FosterRecordFactory {
    public static FosterRecord createFosterRecord(String animalId,
                                                      String animalName,
                                                      String breed,
                                                      String age,
                                                      String gender,
                                                      String healthStatus,
                                                      String behaviorNotes,
                                                      String specialCare,
                                                      String dailyRoutine,
                                                      String incidentReport) {

            if (animalId == null || animalId.isEmpty()) {
                throw new IllegalArgumentException("Animal ID cannot be null or empty");
            }

            if (animalName == null || animalName.isEmpty()) {
                throw new IllegalArgumentException("Animal name cannot be null or empty");
            }

            if (breed == null || breed.isEmpty()) {
                throw new IllegalArgumentException("Breed cannot be null or empty");
            }

            if (age == null || age.isEmpty()) {
                throw new IllegalArgumentException("Age cannot be null or empty");
            }

            if (gender == null || gender.isEmpty()) {
                throw new IllegalArgumentException("Gender cannot be null or empty");
            }

            if (healthStatus == null || healthStatus.isEmpty()) {
                throw new IllegalArgumentException("Health status cannot be null or empty");
            }

            if (behaviorNotes == null || behaviorNotes.isEmpty()) {
                throw new IllegalArgumentException("Behavior notes cannot be null or empty");
            }

            if (specialCare == null || specialCare.isEmpty()) {
                throw new IllegalArgumentException("Special care cannot be null or empty");
            }

            if (dailyRoutine == null || dailyRoutine.isEmpty()) {
                throw new IllegalArgumentException("Daily routine cannot be null or empty");
            }

            if (incidentReport == null || incidentReport.isEmpty()) {
                throw new IllegalArgumentException("Incident report cannot be null or empty");
            }

            return new FosterRecord.Builder()
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
        }
    }


