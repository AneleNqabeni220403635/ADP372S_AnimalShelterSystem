package za.ac.cput.factory;

import za.ac.cput.domain.Volunteer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerFactoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testBuildVolunteerWithId() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, "Alice", "Smith", "555-1234", "alice.smith@example.com", "123 Elm Street", "Weekends");

        assertNotNull(volunteer);
        assertEquals(1L, volunteer.getId());
        assertEquals("Alice", volunteer.getFirstName());
        assertEquals("Smith", volunteer.getLastName());
        assertEquals("555-1234", volunteer.getContactNo());
        assertEquals("alice.smith@example.com", volunteer.getEmailAddress());
        assertEquals("123 Elm Street", volunteer.getStreetAddress());
        assertEquals("Weekends", volunteer.getAvailability());
    }

    @Test
    void testBuildVolunteerWithoutId() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer("Bob", "Johnson", "555-5678", "bob.johnson@example.com", "456 Oak Avenue", "Weekdays");

        assertNotNull(volunteer);
        assertNotNull(volunteer.getId());
        assertEquals("Bob", volunteer.getFirstName());
        assertEquals("Johnson", volunteer.getLastName());
        assertEquals("555-5678", volunteer.getContactNo());
        assertEquals("bob.johnson@example.com", volunteer.getEmailAddress());
        assertEquals("456 Oak Avenue", volunteer.getStreetAddress());
        assertEquals("Weekdays", volunteer.getAvailability());
    }

    @Test
    void testBuildVolunteerWithInvalidData() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(0L, null, null, null, null, null, null);

        assertNull(volunteer);
    }

    @Test
    void testBuildVolunteerWithNullFirstName() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, null, "Smith", "555-1234", "alice.smith@example.com", "123 Elm Street", "Weekends");

        assertNull(volunteer);
    }

    @Test
    void testBuildVolunteerWithNullLastName() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, "Alice", null, "555-1234", "alice.smith@example.com", "123 Elm Street", "Weekends");

        assertNull(volunteer);
    }

    @Test
    void testBuildVolunteerWithNullContactNo() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, "Alice", "Smith", null, "alice.smith@example.com", "123 Elm Street", "Weekends");

        assertNull(volunteer);
    }

    @Test
    void testBuildVolunteerWithNullEmailAddress() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, "Alice", "Smith", "555-1234", null, "123 Elm Street", "Weekends");

        assertNull(volunteer);
    }

    @Test
    void testBuildVolunteerWithNullStreetAddress() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, "Alice", "Smith", "555-1234", "alice.smith@example.com", null, "Weekends");

        assertNull(volunteer);
    }

    @Test
    void testBuildVolunteerWithNullAvailability() {
        Volunteer volunteer = VolunteerFactory.buildVolunteer(1L, "Alice", "Smith", "555-1234", "alice.smith@example.com", "123 Elm Street", null);

        assertNull(volunteer);
    }
}
