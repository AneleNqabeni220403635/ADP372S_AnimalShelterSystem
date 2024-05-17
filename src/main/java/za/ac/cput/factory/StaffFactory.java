package za.ac.cput.factory;

import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Volunteer;

import java.util.Date;

public class StaffFactory {
    public static Staff createStaff(String responsibility, Date trainingDate, String performanceEvaluation, Volunteer volunteer) {
        return new Staff.Builder()
                .setResponsibility(responsibility)
                .setTrainingDate(trainingDate)
                .setPerformanceEvaluation(performanceEvaluation)
                .setVolunteer(volunteer)
                .build();
    }
}
