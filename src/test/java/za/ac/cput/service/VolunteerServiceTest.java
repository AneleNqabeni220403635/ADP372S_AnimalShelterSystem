package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Volunteer;
import za.ac.cput.factory.VolunteerFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class VolunteerServiceTest {

    @Autowired
    private VolunteerService volunteerService;

    private static Volunteer volunteer1;
    private static Volunteer volunteer2;

    @BeforeEach
    void setUp() {
        volunteer1 = VolunteerFactory.createVolunteer(1L, "Thabiso M", 30, "123456789", "thabiso.com", "Weekdays");
        volunteer2 = VolunteerFactory.createVolunteer(2L, "Lili M", 25, "987654321", "lilim.com", "Weekends");
    }

    @Test
    void a_create() {
        Volunteer created = volunteerService.create(volunteer1);
        assertNotNull(created);
        assertEquals(volunteer1.getName(), created.getName());
    }

    @Test
    void b_read() {
        volunteerService.create(volunteer2);
        Volunteer read = volunteerService.read(volunteer2.getVolunteerId());
        assertNotNull(read);
        assertEquals(volunteer2.getName(), read.getName());
    }

    @Test
    void c_update() {
        volunteerService.create(volunteer1);
        Volunteer created = volunteerService.read(volunteer1.getVolunteerId());
        assertNotNull(created);
        created = new Volunteer.Builder()
                .copy(created)
                .setName("Updated Name")
                .build();
        Volunteer updated = volunteerService.update(created);
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void e_delete() {
        volunteerService.create(volunteer1);
        Volunteer created = volunteerService.read(volunteer1.getVolunteerId());
        assertNotNull(created);
        volunteerService.delete(created.getVolunteerId());
        Volunteer deleted = volunteerService.read(created.getVolunteerId());
        assertNull(deleted);
    }

    @Test
    void d_getall() {
        volunteerService.create(volunteer1);
        volunteerService.create(volunteer2);
        Set<Volunteer> volunteers = volunteerService.getall();
        assertTrue(volunteers.size() >= 2);
    }
}
