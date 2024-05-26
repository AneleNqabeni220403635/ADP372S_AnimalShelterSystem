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
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalFactory;
import za.ac.cput.factory.MedicalRecordFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class AnimalControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animal";

    private static Animal snow;

    @BeforeAll
    public static void setUp() {
        snow = AnimalFactory.buildAnimal(10L, "Snow", 7, "Wolf", null);
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Animal> postResponse = restTemplate.postForEntity(url, snow, Animal.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Animal animalSaved = postResponse.getBody();
        assertEquals(snow.getAnimalCode(), animalSaved.getAnimalCode());
        System.out.println("Saved Animal: " + animalSaved);

        MedicalRecord snowMedicalRecord = MedicalRecordFactory.buildMedicalRecord(
                animalSaved,
                LocalDate.now(),
                "None",
                "Calm",
                LocalDate.now().plusMonths(6)
        );
        String medicalRecordUrl = "http://localhost:8080/medicalRecord/create";
        ResponseEntity<MedicalRecord> postMedicalRecord = restTemplate.postForEntity(medicalRecordUrl, snowMedicalRecord, MedicalRecord.class);
        assertNotNull(postMedicalRecord);
        assertNotNull(postMedicalRecord.getBody());
        MedicalRecord medicalRecordSaved = postMedicalRecord.getBody();
        System.out.println("Saved Medical Record: " + medicalRecordSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + snow.getAnimalCode();
        System.out.println("URL: " + url);
        ResponseEntity<Animal> response = restTemplate.getForEntity(url, Animal.class);
        assertEquals(snow.getAnimalCode(), response.getBody().getAnimalCode());
        System.out.println("Read Animal: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Animal newAnimal = new Animal.Builder().copy(snow).setName("Snowy").build();
        ResponseEntity<Animal> postResponse = restTemplate.postForEntity(url, newAnimal, Animal.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Animal animalUpdated = postResponse.getBody();
        assertEquals(newAnimal.getAnimalCode(), animalUpdated.getAnimalCode());
        System.out.println("Updated Animal: " + animalUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + snow.getAnimalCode();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted animal ***");
    }

    @Test
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Show All Animals: " + response.getBody());
    }
}