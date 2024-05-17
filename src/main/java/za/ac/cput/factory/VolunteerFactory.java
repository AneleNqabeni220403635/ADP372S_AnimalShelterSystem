package za.ac.cput.factory;

import za.ac.cput.domain.Volunteer;
import za.ac.cput.domain.Staff;

import java.util.List;

public class VolunteerFactory {

    public static Volunteer createVolunteer(String firstName, String lastName, String email, String phoneNumber, int hoursPerWeek, String role, List<Staff> staffEntries) {
        Volunteer.Builder builder = new Volunteer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setHoursPerWeek(hoursPerWeek)
                .setRole(role);

        if (staffEntries != null) {
            for (Staff staff : staffEntries) {
                builder.addStaffEntry(staff);
            }
        }

        return builder.build();
    }
}
