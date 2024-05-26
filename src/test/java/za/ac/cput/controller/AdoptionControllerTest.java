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
import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.Animal;
import za.ac.cput.factory.AdoptionFactory;
import za.ac.cput.factory.AnimalFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdoptionControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/adoption";

    private static Adoption adoption;
    private static Animal animal;

    @BeforeAll
    public static void setUp() {
        animal = AnimalFactory.buildAnimal(123456789L, "Dog", "2", LocalDate.now(), "Available");
        adoption = AdoptionFactory.createAdoption(123L, "Kitty Kat", LocalDate.now(), "Pending", animal);
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Adoption> postResponse = restTemplate.postForEntity(url, adoption, Adoption.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Adoption adoptionSaved = postResponse.getBody();
        assertEquals(adoption.getApplicantName(), adoptionSaved.getApplicantName());
        System.out.println("Saved data: " + adoptionSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + adoption.getAdoptionId();
        System.out.println("URL: " + url);
        ResponseEntity<Adoption> response = restTemplate.getForEntity(url, Adoption.class);
        assertEquals(adoption.getAdoptionId(), response.getBody().getAdoptionId());
        System.out.println("Read Adoption: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update/" + adoption.getAdoptionId();
        Adoption updatedAdoption = new Adoption.Builder().copy(adoption).setStatus("Approved").build();
        ResponseEntity<Adoption> postResponse = restTemplate.postForEntity(url, updatedAdoption, Adoption.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Adoption adoptionUpdated = postResponse.getBody();
        assertEquals(updatedAdoption.getStatus(), adoptionUpdated.getStatus());
        System.out.println("Updated Adoption data: " + adoptionUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + adoption.getAdoptionId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted Adoption ***");
    }

    @Test
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Long> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.GET, entity, Long.class);
        System.out.println("Show All Adoptions: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}