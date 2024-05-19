package za.ac.cput.factory;
import za.ac.cput.domain.FosterRecord;
import za.ac.cput.util.Helper;

public class FosterRecordFactory {
    public static FosterRecord buildFosterRecord(String animalId, String animalName, String breed, int age, String gender, String healthStatus,
                                                  String behaviorNotes, String specialCare, String dailyRoutine, String incidentReport) {


        if (Helper.isNullorEmpty(animalId) || Helper.isNullorEmpty(animalName) || Helper.isNullorEmpty(breed) ||
                Helper.isLessThanOrEqualToZero(age) || Helper.isNullorEmpty(gender) || Helper.isNullorEmpty(healthStatus) ||
                Helper.isNullorEmpty(behaviorNotes) || Helper.isNullorEmpty(specialCare) || Helper.isNullorEmpty(dailyRoutine) ||
                Helper.isNullorEmpty(incidentReport)) {
            return null;
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



