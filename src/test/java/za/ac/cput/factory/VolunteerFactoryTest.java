package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Volunteer;
import za.ac.cput.domain.Staff;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerFactoryTest {

    @Test
    public void testCreateVolunteer() {
        List<Staff> staffEntries = new ArrayList<>();

        Volunteer volunteer = VolunteerFactory.createVolunteer(
                "Ntombi",
                "Ndlovu",
                "ntombiNdlovu@gmail.com",
                "06714985595",
                15,
                "Cat Caregiver",
                staffEntries
        );

        assertNotNull(volunteer);
        assertEquals("Ntombi", volunteer.getFirstName());
        assertEquals("Ndlovu", volunteer.getLastName());
        assertEquals("ntombiNdlovu@gmail.com", volunteer.getEmail());
        assertEquals("06714985595", volunteer.getPhoneNumber());
        assertEquals(15, volunteer.getHoursPerWeek());
        assertEquals("Cat Caregiver", volunteer.getRole());
        assertNotNull(volunteer.getStaffEntries());
        assertEquals(staffEntries, volunteer.getStaffEntries());

        System.out.println(volunteer);
    }
}
