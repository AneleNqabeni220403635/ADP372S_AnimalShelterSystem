package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.OwnerRecord;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.OwnerRecordFactory;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerRecordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalshelter/ownerRecord";

    private OwnerRecord ownerRecord;

    @BeforeEach
    public void setUp() {
        PetOwner petOwner = new PetOwner.Builder()
                .setId(1L)
                .setFirstName("John")
                .setLastName("Doe")
                .setContactNo("1234567890")
                .setEmailAddress("abc@gmail.com")
                .setStreetAddress("abc")
                .build();

        Dog dog = new Dog.Builder()
                .setDogId(1L)
                .setName("Buddy")
                .setSize("Large")
                .setAge(5)
                .setGender("Male")
                .setBreed("Golden Retriever")
                .setCageNumber(0)
                .build();

        Cat cat = new Cat.Builder()
                .setCatId(5L)
                .setName("Whiskers")
                .setSize("Large")
                .setAge(3)
                .setGender("Female")
                .setBreed("Siamese")
                .setCageNumber(5)
                .build();

        ownerRecord = OwnerRecordFactory.buildOwnerRecord(2L,dog,cat,petOwner, LocalDate.now(),LocalDate.now().plusMonths(6));
    }

    @Test
    @Order(1)
    void testCreateOwnerRecord() {
        String url = BASE_URL + "/create";
        // Log the dog object being sent
        System.out.println("Sending OwnerRecord object: " + ownerRecord);
        ResponseEntity<OwnerRecord> response = restTemplate.postForEntity(url, ownerRecord, OwnerRecord.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Change to HttpStatus.OK if needed
        assertNotNull(response.getBody());
        OwnerRecord createdOwnerRecord = response.getBody();
        assertEquals(ownerRecord.getTakenDate(), createdOwnerRecord.getTakenDate());
        System.out.println("Created OwnerRecord: " + createdOwnerRecord);
    }

    @Test
    @Order(2)
    void testReadOwnerRecord() {
        // Ensure the dog is created before reading
        assertNotNull(ownerRecord.getId(), "OwnerRecord ID should not be null");
        // Correct URL format
        String url = BASE_URL + "/read/" + ownerRecord.getId();
        // Log the URL being accessed
        System.out.println("Request URL: " + url);
        ResponseEntity<OwnerRecord> response = restTemplate.getForEntity(url, OwnerRecord.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        OwnerRecord readOwnerRecord = response.getBody();
        // Log the details of the read dog
        System.out.println("Read OwnerRecord: " + readOwnerRecord);
    }

    @Test
    @Order(3)
    void testUpdateOwnerRecord() {
        String url = BASE_URL + "/update";
        OwnerRecord updatedOwnerRecord = new OwnerRecord.Builder()
                .copy(ownerRecord)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OwnerRecord> request = new HttpEntity<>(updatedOwnerRecord, headers);
        // Using exchange method for PUT request
        ResponseEntity<OwnerRecord> response = restTemplate.exchange(url, HttpMethod.PUT, request, OwnerRecord.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        OwnerRecord updated = response.getBody();
        assertEquals(updatedOwnerRecord.getTakenDate(), updated.getTakenDate());
        System.out.println("Updated OwnerRecord: " + updated);
    }


    @Test
    @Order(4)
    void testGetAllOwnerRecords() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show All OwnerRecords: " + response.getBody());
    }

    @Test
    @Order(5)
    void testDeleteOwnerRecord() {
        // DELETE the dog
        String deleteUrl = BASE_URL + "/delete/" + ownerRecord.getId();
        restTemplate.delete(deleteUrl);
        // Verify the deletion by attempting to read the dog
        String readUrl = BASE_URL + "/read/" + ownerRecord.getId();
        ResponseEntity<OwnerRecord> response = restTemplate.getForEntity(readUrl, OwnerRecord.class);
        // Log the result
        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());

    }

}
