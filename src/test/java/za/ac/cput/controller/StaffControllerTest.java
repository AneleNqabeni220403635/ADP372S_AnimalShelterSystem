package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Staff;
import za.ac.cput.factory.StaffFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaffControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/staff";

    private static Staff staff;

    @BeforeAll
    public static void setUp() {
        staff = StaffFactory.createStaff(1L, "Hope S", "cleaner", 5000, LocalDate.of(2024, 5, 15), "Making sure the shelter is clean", "Does what is expected of her");
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Staff> postResponse = restTemplate.postForEntity(url, staff, Staff.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Staff staffSaved = postResponse.getBody();
        assertEquals(staff.getName(), staffSaved.getName());
        System.out.println("Saved data: " + staffSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + staff.getStaffId();
        System.out.println("URL: " + url);
        ResponseEntity<Staff> response = restTemplate.getForEntity(url, Staff.class);
        assertNotNull(response.getBody());
        assertEquals(staff.getStaffId(), response.getBody().getStaffId());
        System.out.println("Read Staff: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Staff updatedStaff = new Staff.Builder().copy(staff).setName("Updated Name").build();
        ResponseEntity<Staff> postResponse = restTemplate.postForEntity(url, updatedStaff, Staff.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Staff staffUpdated = postResponse.getBody();
        assertEquals(updatedStaff.getName(), staffUpdated.getName());
        System.out.println("Updated Staff data: " + staffUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + staff.getStaffId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted Staff ***");
    }

    @Test
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All Staff: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
