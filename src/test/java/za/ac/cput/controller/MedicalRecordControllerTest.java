package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalFactory;
import za.ac.cput.factory.MedicalRecordFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MedicalRecordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/medicalRecord";

    private static Animal snow;

    @BeforeAll
    public static void setUp() {
        snow = AnimalFactory.buildAnimal(10L, "Snow", 7, "Wolf", null);
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Animal> postAnimalResponse = restTemplate.postForEntity("/animal/create", snow, Animal.class);
        assertNotNull(postAnimalResponse);
        assertNotNull(postAnimalResponse.getBody());
        Animal animalSaved = postAnimalResponse.getBody();
        assertEquals(snow.getAnimalCode(), animalSaved.getAnimalCode());

        MedicalRecord snowMedicalRecord = MedicalRecordFactory.buildMedicalRecord(
                animalSaved,
                LocalDate.now(),
                "Vaccine A",
                "Calm",
                LocalDate.now().plusMonths(6)
        );

        ResponseEntity<MedicalRecord> postMedicalRecordResponse = restTemplate.postForEntity(url, snowMedicalRecord, MedicalRecord.class);
        assertNotNull(postMedicalRecordResponse);
        assertNotNull(postMedicalRecordResponse.getBody());
        MedicalRecord medicalRecordSaved = postMedicalRecordResponse.getBody();
        assertEquals(snow.getAnimalCode(), medicalRecordSaved.getAnimal().getAnimalCode());
        System.out.println("Saved Medical Record: " + medicalRecordSaved);
    }
    @Test
    void read() {
        String url = BASE_URL + "/read/" + snow.getAnimalCode();
        System.out.println("URL: " + url);
        ResponseEntity<MedicalRecord> response = restTemplate.getForEntity(url, MedicalRecord.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        MedicalRecord medicalRecord = response.getBody();
        assertEquals(snow.getAnimalCode(), medicalRecord.getAnimal().getAnimalCode());
        System.out.println("Read MedicalRecord: " + response.getBody());
    }
    @Test
    void update() {
        String readUrl = BASE_URL + "/read/" + snow.getAnimalCode();
        ResponseEntity<MedicalRecord> readResponse = restTemplate.getForEntity(readUrl, MedicalRecord.class);
        assertEquals(HttpStatus.OK, readResponse.getStatusCode());
        assertNotNull(readResponse.getBody());
        MedicalRecord existingMedicalRecord = readResponse.getBody();

        String updateUrl = BASE_URL + "/update";
        MedicalRecord updatedMedicalRecord = new MedicalRecord.Builder()
                .copy(existingMedicalRecord)
                .setMedication("Painkillers")
                .build();

        ResponseEntity<MedicalRecord> updateResponse = restTemplate.exchange(
                updateUrl,
                HttpMethod.PUT,
                new HttpEntity<>(updatedMedicalRecord),
                MedicalRecord.class
        );

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertNotNull(updateResponse.getBody());
        MedicalRecord medicalRecordUpdated = updateResponse.getBody();

        assertEquals(updatedMedicalRecord.getAnimal(), medicalRecordUpdated.getAnimal());
        assertEquals(updatedMedicalRecord.getMedication(), medicalRecordUpdated.getMedication());
        System.out.println("Updated MedicalRecord data: " + medicalRecordUpdated);
    }
    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + snow.getAnimalCode();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted MedicalRecord ***");
    }
    @Test
    void getall() {
        String url = BASE_URL + "/getall/" + snow.getAnimalCode();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity,String.class);
        System.out.println("Show All MedicalRecords: ");
        System.out.println(response.getBody());
    }

}

