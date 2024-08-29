package za.ac.cput.controller;

import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.ApplicantFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicantControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalshelter/applicant";
    private Applicant applicant;

    @BeforeEach
    public void setUp() {
        PetOwner petOwner = new PetOwner.Builder()
                .setId(1L)
                .setFirstName("Jack")
                .setLastName("Parrow")
                .setContactNo("0721234567")
                .setEmailAddress("parrowj@cput.ac.za")
                .setStreetAddress("80 Constitution Street, Cape Town, 7925")
                .build();

        Dog dog = new Dog.Builder()
                .setDogId(2L)
                .setName("Buddy")
                .setSize("Large")
                .setAge(5)
                .setGender("Male")
                .setBreed("Golden Retriever")
                .setCageNumber(0)
                .build();

        Cat cat = new Cat.Builder()
                .setCatId(6L)
                .setName("Whiskers")
                .setSize("Large")
                .setAge(3)
                .setGender("Female")
                .setBreed("Siamese")
                .setCageNumber(5)
                .build();

        applicant = ApplicantFactory.buildApplicant(2L,petOwner, LocalDate.now(), dog, cat, "Pending");
    }

    @Test
    @Order(1)
    void testCreateApplicant() {

        String url = BASE_URL + "/create";

        System.out.println("Sending Applicant object: " + applicant);
        ResponseEntity<Applicant> response = restTemplate.postForEntity(url, applicant, Applicant.class);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Applicant createdApplicant = response.getBody();
        assertEquals(applicant.getStatus(), createdApplicant.getStatus());
        System.out.println("Created Applicant: " + createdApplicant);
    }

    @Test
    @Order(2)
    void testReadApplicant() {

        assertNotNull(applicant.getId(), "Applicant ID should not be null");

        String url = BASE_URL + "/read/" + applicant.getId();

        System.out.println("Request URL: " + url);
        ResponseEntity<Applicant> response = restTemplate.getForEntity(url, Applicant.class);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        Applicant readApplicant = response.getBody();

        System.out.println("Read Dog: " + readApplicant);
    }

    @Test
    @Order(3)
    void testUpdateApplicant() {
        String url = BASE_URL + "/update";
        Applicant updatedApplicant = new Applicant.Builder()
                .copy(applicant)
                .setStatus("abc")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Applicant> request = new HttpEntity<>(updatedApplicant, headers);

        ResponseEntity<Applicant> response = restTemplate.exchange(url, HttpMethod.PUT, request, Applicant.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Applicant updated = response.getBody();
        assertEquals(updatedApplicant.getId(), updated.getId());
        System.out.println("Updated Applicant: " + updated);
    }

    @Test
    @Order(4)
    void testGetAllApplicants() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show ALL Applicants: " + response.getBody());
    }

    @Test
    @Order(5)
    void testDeleteApplicant() {

        String deleteUrl = BASE_URL + "/delete/" + applicant.getId();
        restTemplate.delete(deleteUrl);

        String readUrl = BASE_URL + "/read/" + applicant.getId();
        ResponseEntity<Applicant> response = restTemplate.getForEntity(readUrl, Applicant.class);

        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());
    }

}
