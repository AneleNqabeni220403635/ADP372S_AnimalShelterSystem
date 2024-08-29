package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Cat;
import za.ac.cput.factory.CatFactory;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalshelter/cat";

    private static Cat whiskers;

    @BeforeEach
    public void setUp() {

        whiskers = CatFactory.buildCat(6L,"Whiskers", "Small", 3, "Female", "Pitbull", 201);
    }

    @Test
    @Order(1)
    void testCreateCat() {

        String url = BASE_URL + "/create";
        // Log the cat object being sent
        System.out.println("Sending Dog object: " + whiskers);
        ResponseEntity<Cat> response = restTemplate.postForEntity(url,whiskers,Cat.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Change to HttpStatus.OK if needed
        assertNotNull(response.getBody());
        Cat createdCat = response.getBody();
        assertEquals(whiskers.getName(), createdCat.getName());
        System.out.println("Created Cat: " + createdCat);
    }

    @Test
    @Order(2)
    void testReadCat() {
        // Ensure the dog is created before reading
        assertNotNull(whiskers.getCatId(), "Cat ID should not be null");
        // Correct URL format
        String url = BASE_URL + "/read/" + whiskers.getCatId();
        // Log the URL being accessed
        System.out.println("Request URL: " + url);
        ResponseEntity<Cat> response = restTemplate.getForEntity(url, Cat.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        Cat readCat = response.getBody();
        // Log the details of the read dog
        System.out.println("Read Dog: " + readCat);
    }

    @Test
    @Order(3)
    void testUpdateCat() {
        String url = BASE_URL + "/update";
        Cat updatedCat = new Cat.Builder()
                .copy(whiskers)
                .setName("Snowy")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Cat> request = new HttpEntity<>(updatedCat, headers);

        // Using exchange method for PUT request
        ResponseEntity<Cat> response = restTemplate.exchange(url, HttpMethod.PUT, request, Cat.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Cat updated = response.getBody();
        assertEquals(updatedCat.getName(), updated.getName());
        System.out.println("Updated Cat: " + updated);
    }

    @Test
    @Order(4)
    void testGetAllCats() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show All Cats: " + response.getBody());
    }


    @Test
    @Order(5)
    void testDeleteCat() {
        // DELETE the dog
        String deleteUrl = BASE_URL + "/delete/" + whiskers.getCatId();
        restTemplate.delete(deleteUrl);
        // Verify the deletion by attempting to read the dog
        String readUrl = BASE_URL + "/read/" + whiskers.getCatId();
        ResponseEntity<Cat> response = restTemplate.getForEntity(readUrl, Cat.class);
        // Log the result
        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());
    }



}
