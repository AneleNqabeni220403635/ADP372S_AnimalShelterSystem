package za.ac.cput.service;

import za.ac.cput.domain.Volunteer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.factory.VolunteerFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VolunteerServiceTest {

    @Autowired
    private VolunteerService volunteerService;

    private static Volunteer volunteer;

    @BeforeEach
    void setUp() {
        volunteer= VolunteerFactory.buildVolunteer(3L,"Ketty","Ngodi","0835479366","ketty@gmail.com","Dosert street","online");
    }

    @Test
    @Order(1)
    void testCreate() {
        Volunteer createdVolunteer = volunteerService.create(volunteer);
        assertNotNull(createdVolunteer);
        assertEquals(volunteer.getFirstName(), createdVolunteer.getFirstName());
        System.out.println("Created: " + createdVolunteer);
    }

    @Test
    @Order(2)
    void testRead() {
        Volunteer readVolunteer = volunteerService.read(volunteer.getId());
        assertNotNull(readVolunteer);
        assertEquals(volunteer.getFirstName(), readVolunteer.getFirstName());
        System.out.println("Read: " + readVolunteer);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Volunteer updatedVolunteer = new Volunteer.Builder()
                .copy(volunteer)
                .setFirstName("Memo")
                .build();
        Volunteer updated = volunteerService.update(updatedVolunteer);
        assertNotNull(updated);
        assertEquals(updatedVolunteer.getFirstName(), updated.getFirstName());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        Set<Volunteer> volunteers = volunteerService.getall();
        assertNotNull(volunteers);
        assertFalse(volunteers.isEmpty());
        System.out.println("All Volunteers: " + volunteers);
    }

    @Test
    @Order(5)
    void testDelete() {
        volunteerService.delete(volunteer.getId());
        Volunteer deleted = volunteerService.read(volunteer.getId());
        assertNull(deleted);
        System.out.println("Deleted");
    }

}
