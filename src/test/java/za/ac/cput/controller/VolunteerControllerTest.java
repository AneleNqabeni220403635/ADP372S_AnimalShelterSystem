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
import za.ac.cput.domain.Volunteer;
import za.ac.cput.factory.VolunteerFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VolunteerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/volunteer";

    private static Volunteer volunteer;

    @BeforeAll
    public static void setUp() {
        volunteer = VolunteerFactory.createVolunteer(1L, "Thabiso M", 30, "123456789", "thabiso@gmail.com", "Weekdays");
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Volunteer> postResponse = restTemplate.postForEntity(url, volunteer, Volunteer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Volunteer volunteerSaved = postResponse.getBody();
        assertEquals(volunteer.getName(), volunteerSaved.getName());
        System.out.println("Saved data: " + volunteerSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + volunteer.getVolunteerId();
        System.out.println("URL: " + url);
        ResponseEntity<Volunteer> response = restTemplate.getForEntity(url, Volunteer.class);
        assertNotNull(response.getBody());
        assertEquals(volunteer.getVolunteerId(), response.getBody().getVolunteerId());
        System.out.println("Read Volunteer: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Volunteer updatedVolunteer = new Volunteer.Builder().copy(volunteer).setName("Updated Name").build();
        ResponseEntity<Volunteer> postResponse = restTemplate.postForEntity(url, updatedVolunteer, Volunteer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Volunteer volunteerUpdated = postResponse.getBody();
        assertEquals(updatedVolunteer.getName(), volunteerUpdated.getName());
        System.out.println("Updated Volunteer data: " + volunteerUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + volunteer.getVolunteerId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted Volunteer ***");
    }

    @Test
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All Volunteers: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
