package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Employee;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    private static Employee employee = new Employee.Builder()
            .setId(2L)
            .setFirstName("Thandiwe")
            .setLastName("Mhlongo")
            .setContactNo("1234567890")
            .setEmailAddress("thandiwe@gmail.com")
            .build();

    @Test
    @Order(1)
    void testCreate() {
        Employee createdEmployee = employeeService.create(employee);
        assertNotNull(createdEmployee);
        assertEquals(employee.getFirstName(), createdEmployee.getFirstName());
        System.out.println("Created: " + createdEmployee);
    }

    @Test
    @Order(2)
    void testRead() {
        Employee readEmployee = employeeService.read(employee.getId());
        assertNotNull(readEmployee);
        assertEquals(employee.getId(), readEmployee.getId());
        System.out.println("Read: " + readEmployee);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Employee updatedEmployee = new Employee.Builder()
                .copy(employee)
                .setContactNo("0987654321")
                .build();
        Employee updated = employeeService.update(updatedEmployee);
        assertNotNull(updated);
        assertEquals("0987654321", updated.getContactNo());
        System.out.println("Updated: " + updated);
    }


    @Test
    @Order(4)
    void testGetAll() {
        Set<Employee> employees = employeeService.getall();
        assertFalse(employees.isEmpty());
        System.out.println("All Employees: " + employees);
    }

    @Test
    @Order(5)
    void testDelete() {
        employeeService.delete(employee.getId());
        Employee deleted = employeeService.read(employee.getId());
        assertNull(deleted);
        System.out.println("Deleted");
    }

}
