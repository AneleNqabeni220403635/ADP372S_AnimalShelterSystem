package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Volunteer;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class StaffFactoryTest {

    @Test
    public void testCreateStaff() {
        Volunteer volunteer = new Volunteer.Builder()
                .setFirstName("Thandiwe")
                .setLastName("Mhlongo")
                .setEmail("thandiwemhlongo@gmail.com")
                .setPhoneNumber("0724358575")
                .setHoursPerWeek(10)
                .setRole("Dog Walker")
                .build();

        String responsibility = "Training new volunteers";
        Date trainingDate = new Date();
        String performanceEvaluation = "Excellent";

        Staff staff = StaffFactory.createStaff(responsibility, trainingDate, performanceEvaluation, volunteer);

        assertNotNull(staff);
        assertEquals(responsibility, staff.getResponsibility());
        assertEquals(trainingDate, staff.getTrainingDate());
        assertEquals(performanceEvaluation, staff.getPerformanceEvaluation());
        assertEquals(volunteer, staff.getVolunteer());

        System.out.println(staff);
    }
}
