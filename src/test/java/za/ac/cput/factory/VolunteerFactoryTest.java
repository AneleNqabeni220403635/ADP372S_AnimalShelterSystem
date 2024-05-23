package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Volunteer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VolunteerFactoryTest {

    @Test
    void createVolunteer() {
        // Given
        long volunteerId = 1;
        String name = "Ntombi Ndlovu";
        int age = 25;
        String contactNumber = "+27673456789";
        String email = "ntombindlovu@gmail.com";
        String availability = "Weekends";

        Volunteer volunteer = VolunteerFactory.createVolunteer(volunteerId, name, age, contactNumber, email, availability);

        assertNotNull(volunteer);
        assertEquals(volunteerId, volunteer.getVolunteerId());
        assertEquals(name, volunteer.getName());
        assertEquals(age, volunteer.getAge());
        assertEquals(contactNumber, volunteer.getContactNumber());
        assertEquals(email, volunteer.getEmail());
        assertEquals(availability, volunteer.getAvailability());
    }
}
