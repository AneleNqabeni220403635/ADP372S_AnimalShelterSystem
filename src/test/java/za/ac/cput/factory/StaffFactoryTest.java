package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Staff;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StaffFactoryTest {

    @Test
    void createStaff() {
        long staffId = 1;
        String name = "Thandiwe Mhlongo";
        String position = "Manager";
        double salary = 10000.00;
        LocalDate hireDate = LocalDate.of(2022, 5, 15);
        String responsibility = "Manage staff and operations";
        String performanceEvaluation = "Exceeds Expectations";

        Staff staff = StaffFactory.createStaff(staffId, name, position, salary, hireDate, responsibility, performanceEvaluation);

        assertNotNull(staff);
        assertEquals(staffId, staff.getStaffId());
        assertEquals(name, staff.getName());
        assertEquals(position, staff.getPosition());
        assertEquals(salary, staff.getSalary());
        assertEquals(hireDate, staff.getHireDate());
        assertEquals(responsibility, staff.getResponsibility());
        assertEquals(performanceEvaluation, staff.getPerformanceEvaluation());
    }
}
