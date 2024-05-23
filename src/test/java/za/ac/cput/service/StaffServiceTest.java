package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Staff;
import za.ac.cput.factory.StaffFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    private static Staff staff1;
    private static Staff staff2;

    @BeforeEach
    void setUp() {
        staff1 = StaffFactory.createStaff(1L, "Busi H", "Manager", 5000.00, LocalDate.of(2022, 5, 20),
                "Managing the team", "Excellent");
        staff2 = StaffFactory.createStaff(2L, "Charity N", "Clerk", 3500.00, LocalDate.of(2022, 5, 25),
                "Data entry", "Good");
    }

    @Test
    void a_create() {
        Staff created = staffService.create(staff1);
        assertNotNull(created);
        assertEquals(staff1.getName(), created.getName());
    }

    @Test
    void b_read() {
        staffService.create(staff2);
        Staff read = staffService.read(staff2.getStaffId());
        assertNotNull(read);
        assertEquals(staff2.getName(), read.getName());
    }

    @Test
    void c_update() {
        staffService.create(staff1);
        Staff created = staffService.read(staff1.getStaffId());
        assertNotNull(created);
        created = new Staff.Builder()
                .copy(created)
                .setName("Updated Name")
                .build();
        Staff updated = staffService.update(created);
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void e_delete() {
        staffService.create(staff1);
        Staff created = staffService.read(staff1.getStaffId());
        assertNotNull(created);
        staffService.delete(created.getStaffId());
        Staff deleted = staffService.read(created.getStaffId());
        assertNull(deleted);
    }

    @Test
    void d_getall() {
        staffService.create(staff1);
        staffService.create(staff2);
        Set<Staff> staffSet = staffService.getall();
        assertTrue(staffSet.size() >= 2);
    }
}
