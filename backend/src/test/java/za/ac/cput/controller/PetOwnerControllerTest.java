package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.PetOwnerFactory;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetOwnerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalRescue/petOwner";

    private PetOwner petOwner;

    @BeforeEach
    public void setUp() {
        petOwner = PetOwnerFactory.buildPetOwner(1L,"Manahil","Jawed", "12345678", "abc@gmail.com","abc street");
    }

    @Test
    @Order(1)
    void testCreatePetOwner() {
        String url = BASE_URL + "/create";
        // Log the dog object being sent
        System.out.println("Sending PetOwner object: " + petOwner);
        ResponseEntity<PetOwner> response = restTemplate.postForEntity(url, petOwner, PetOwner.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Change to HttpStatus.OK if needed
        assertNotNull(response.getBody());
        PetOwner createdPetOwner = response.getBody();
        assertEquals(petOwner.getFirstName(), createdPetOwner.getFirstName());
        System.out.println("Created PetOwner: " + createdPetOwner);
    }

    @Test
    @Order(2)
    void testReadPetOwner() {
        // Ensure the dog is created before reading
        assertNotNull(petOwner.getId(), "PetOwner ID should not be null");
        // Correct URL format
        String url = BASE_URL + "/read/" + petOwner.getId();
        // Log the URL being accessed
        System.out.println("Request URL: " + url);
        ResponseEntity<PetOwner> response = restTemplate.getForEntity(url, PetOwner.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        PetOwner readPetOwner = response.getBody();
        // Log the details of the read dog
        System.out.println("Read PetOwner: " + readPetOwner);
    }

    @Test
    @Order(3)
    void testUpdatePetOwner() {
        String url = BASE_URL + "/update";
        PetOwner updatedPetOwner = new PetOwner.Builder()
                .copy(petOwner)
                .setFirstName("Manahil")
                .setLastName("Jawed")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PetOwner> request = new HttpEntity<>(updatedPetOwner, headers);
        // Using exchange method for PUT request
        ResponseEntity<PetOwner> response = restTemplate.exchange(url, HttpMethod.PUT, request, PetOwner.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        PetOwner updated = response.getBody();
        assertEquals(updatedPetOwner.getFirstName(), updated.getFirstName());
        System.out.println("Updated PetOwner: " + updated);
    }

    @Test
    @Order(4)
    void testGetAllPetOwners() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show All PetOwners: " + response.getBody());
    }


    @Test
    @Order(5)
    void testDeletePetOwner() {
        // DELETE the dog
        String deleteUrl = BASE_URL + "/delete/" + petOwner.getId();
        restTemplate.delete(deleteUrl);
        // Verify the deletion by attempting to read the dog
        String readUrl = BASE_URL + "/read/" + petOwner.getId();
        ResponseEntity<PetOwner> response = restTemplate.getForEntity(readUrl, PetOwner.class);
        // Log the result
        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());

    }

}
