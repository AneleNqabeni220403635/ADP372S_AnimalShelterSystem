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
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.MedicalRecordFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MedicalRecordControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/medicalrecord";

    private static MedicalRecord medicalRecord;

    @BeforeAll
    public static void setUp() {
        medicalRecord = MedicalRecordFactory.buildMedicalRecord(123456789L, LocalDate.now(), "Antibiotics", "Calm", LocalDate.now().plusMonths(6));
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<MedicalRecord> postResponse = restTemplate.postForEntity(url, medicalRecord, MedicalRecord.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        MedicalRecord medicalRecordSaved = postResponse.getBody();
        assertEquals(medicalRecord.getAnimal(), medicalRecordSaved.getAnimal());
        System.out.println("Saved data: " + medicalRecordSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + medicalRecord.getAnimal();
        System.out.println("URL: " + url);
        ResponseEntity<MedicalRecord> response = restTemplate.getForEntity(url, MedicalRecord.class);
        assertEquals(medicalRecord.getAnimal(), response.getBody().getAnimal());
        System.out.println("Read MedicalRecord: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update/" + medicalRecord.getAnimal();
        MedicalRecord updatedMedicalRecord = new MedicalRecord.Builder().copy(medicalRecord).setMedication("Painkillers").build();
        ResponseEntity<MedicalRecord> postResponse = restTemplate.postForEntity(url, updatedMedicalRecord, MedicalRecord.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        MedicalRecord medicalRecordUpdated = postResponse.getBody();
        assertEquals(updatedMedicalRecord.getAnimal(), medicalRecordUpdated.getAnimal());
        System.out.println("Updated MedicalRecord data: " + medicalRecordUpdated);
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + medicalRecord.getAnimal();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted MedicalRecord ***");
    }

    @Test
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Long> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.GET, entity, Long.class);
        System.out.println("Show All MedicalRecords: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}