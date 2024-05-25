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

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimalControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animal";

    private static Animal animal;
    private static MedicalRecord medicalRecord;

    @BeforeAll
    public static void setUp() {
        animal = AnimalFactory.buildAnimal(123456789L, "Lion", 5, "Mammal", medicalRecord);
        medicalRecord = MedicalRecordFactory.buildMedicalRecord(123456789L, LocalDate.now(), "Antibiotics", "Calm", LocalDate.now().plusMonths(6));
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Animal> postResponse = restTemplate.postForEntity(url, animal, Animal.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Animal animalSaved = postResponse.getBody();
        assertEquals(animal.getAnimalCode(), animalSaved.getAnimalCode());
        System.out.println("Saved data: " + animalSaved);

        ResponseEntity<MedicalRecord> postMedicalRecord = restTemplate.postForEntity(url, medicalRecord, MedicalRecord.class);
        assertNotNull(postMedicalRecord);
        assertNotNull(postMedicalRecord.getBody());
        MedicalRecord medicalRecordSaved = postMedicalRecord.getBody();
        System.out.println("Saved data: " + medicalRecordSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + animal.getAnimalCode();
        System.out.println("URL: " + url);
        ResponseEntity<Animal> response = restTemplate.getForEntity(url, Animal.class);
        assertEquals(animal.getAnimalCode(), response.getBody().getAnimalCode());
        System.out.println("Read Animal: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update" + animal.getAnimalCode();
        Animal newAnimal = new Animal.Builder().copy(animal).setName("Elephant").build();
        ResponseEntity<Animal> postResponse = restTemplate.postForEntity(url, newAnimal, Animal.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Animal animalUpdated = postResponse.getBody();
        assertEquals(newAnimal.getAnimalCode(), animalUpdated.getAnimalCode());
        System.out.println("Update Animal: " + animalUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL +"/delete/"+ animal.getAnimalCode();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted animal ***");
    }

    @Test
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Long> entity = new HttpEntity<>(null,headers);
        ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.GET, entity, Long.class);
        System.out.println("Show All Animals: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
    @Test
    void findAllAnimalsByNameAndAge() {
        String url = BASE_URL + "/Lion/5";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response.getBody());
        System.out.println("Animals found by name and age: ");
        System.out.println(response.getBody());
    }
}