package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Employee;
import za.ac.cput.factory.EmployeeFactory;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalshelter/employee";

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = EmployeeFactory.buildEmployee(3L,"Manahil","Jawed", "12345678", "abc@gmail.com");
    }

    @Test
    @Order(1)
    void testCreateEmployee() {
        String url = BASE_URL + "/create";
        // Log the dog object being sent
        System.out.println("Sending Employee object: " + employee);
        ResponseEntity<Employee> response = restTemplate.postForEntity(url, employee, Employee.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Change to HttpStatus.OK if needed
        assertNotNull(response.getBody());
        Employee createdEmployee = response.getBody();
        assertEquals(employee.getFirstName(), createdEmployee.getFirstName());
        System.out.println("Created Employee: " + createdEmployee);
    }

    @Test
    @Order(2)
    void testReadEmployee() {
        // Ensure the dog is created before reading
        assertNotNull(employee.getId(), "Employee ID should not be null");
        // Correct URL format
        String url = BASE_URL + "/read/" + employee.getId();
        // Log the URL being accessed
        System.out.println("Request URL: " + url);
        ResponseEntity<Employee> response = restTemplate.getForEntity(url, Employee.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        Employee readEmployee = response.getBody();
        // Log the details of the read dog
        System.out.println("Read Employee: " + readEmployee);
    }

    @Test
    @Order(3)
    void testUpdateEmployee() {
        String url = BASE_URL + "/update";
        Employee updatedEmployee = new Employee.Builder()
                .copy(employee)
                .setFirstName("Manahil")
                .setLastName("Jawed")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> request = new HttpEntity<>(updatedEmployee, headers);
        // Using exchange method for PUT request
        ResponseEntity<Employee> response = restTemplate.exchange(url, HttpMethod.PUT, request, Employee.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Employee updated = response.getBody();
        assertEquals(updatedEmployee.getFirstName(), updated.getFirstName());
        System.out.println("Updated Employee: " + updated);
    }

    @Test
    @Order(4)
    void testGetAllEmployees() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show All Employees: " + response.getBody());
    }


    @Test
    @Order(5)
    void testDeleteEmployee() {
        // DELETE the dog
        String deleteUrl = BASE_URL + "/delete/" + employee.getId();
        restTemplate.delete(deleteUrl);
        // Verify the deletion by attempting to read the dog
        String readUrl = BASE_URL + "/read/" + employee.getId();
        ResponseEntity<Employee> response = restTemplate.getForEntity(readUrl, Employee.class);
        // Log the result
        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());

    }

}
