package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Employee;
import za.ac.cput.util.Helper;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeFactoryTest {

    @BeforeEach
    void setUp() {
        // No setup required for Employee tests as no shared state or complex initialization is needed.
    }

    @Test
    void testBuildEmployeeWithId() {
        Employee employee = EmployeeFactory.buildEmployee(1L, "Thandiwe", "Mhlongo", "123-456-7890", "thandiwe@gmail.com");

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("Thandiwe", employee.getFirstName());
        assertEquals("Mhlongo", employee.getLastName());
        assertEquals("123-456-7890", employee.getContactNo());
        assertEquals("thandiwe@gmail.com", employee.getEmailAddress());
    }

    @Test
    void testBuildEmployeeWithoutId() {
        Employee employee = EmployeeFactory.buildEmployee("Jane", "Smith", "987-654-3210", "jane.smith@gmail.com");

        assertNotNull(employee);
        assertNotNull(employee.getId());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("Smith", employee.getLastName());
        assertEquals("987-654-3210", employee.getContactNo());
        assertEquals("jane.smith@gmail.com", employee.getEmailAddress());
    }

    @Test
    void testBuildEmployeeWithInvalidData() {
        Employee employee = EmployeeFactory.buildEmployee(0L, "", "", "", "");

        assertNull(employee);
    }

    @Test
    void testBuildEmployeeWithNullFirstName() {
        Employee employee = EmployeeFactory.buildEmployee(1L, null, "Mhlongo", "123-456-7890", "thandiwe@gmail.com");

        assertNull(employee);
    }

    @Test
    void testBuildEmployeeWithNullLastName() {
        Employee employee = EmployeeFactory.buildEmployee(1L, "Thandiwe", null, "123-456-7890", "thandiwe@gmail.com");

        assertNull(employee);
    }

    @Test
    void testBuildEmployeeWithNullContactNo() {
        Employee employee = EmployeeFactory.buildEmployee(1L, "Thandiwe", "Mhlongo", null, "thandiwe@gmail.com");

        assertNull(employee);
    }

    @Test
    void testBuildEmployeeWithNullEmailAddress() {
        Employee employee = EmployeeFactory.buildEmployee(1L, "Thandiwe", "Mhlongo", "123-456-7890", null);

        assertNull(employee);
    }
}

