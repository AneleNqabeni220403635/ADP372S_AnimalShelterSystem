package za.ac.cput.controller;

import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.MedicalRecordFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalRecordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalRescue/medicalRecord";

    private MedicalRecord medicalRecord;

    @BeforeEach
    public void setUp() {

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

        medicalRecord =MedicalRecordFactory.buildMedicalRecord(4L,dog,cat,LocalDate.now(),"Med1","Good",LocalDate.now().plusMonths(6),"Annual checkup");
    }

    @Test
    @Order(1)
    void testCreateMedicalRecord() {
        String url = BASE_URL + "/create";

        System.out.println("Sending Medical Record object: " + medicalRecord);
        ResponseEntity<MedicalRecord> response = restTemplate.postForEntity(url, medicalRecord, MedicalRecord.class);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Change to HttpStatus.OK if needed
        assertNotNull(response.getBody());
        MedicalRecord createdMedicalRecord = response.getBody();
        assertEquals(medicalRecord.getDescription(), createdMedicalRecord.getDescription());
        System.out.println("Created Medical Record: " + createdMedicalRecord);
    }

    @Test
    @Order(2)
    void testReadMedicalRecord() {

        assertNotNull(medicalRecord.getId(), "MedicalRecord ID should not be null");

        String url = BASE_URL + "/read/" + medicalRecord.getId();

        System.out.println("Request URL: " + url);
        ResponseEntity<MedicalRecord> response = restTemplate.getForEntity(url, MedicalRecord.class);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        MedicalRecord readMedicalRecord = response.getBody();

        System.out.println("Read MedicalRecord: " + readMedicalRecord);
    }

    @Test
    @Order(3)
    void testUpdateMedicalRecord() {
        String url = BASE_URL + "/update";
        MedicalRecord updatedMedicalRecord = new MedicalRecord.Builder()
                .copy(medicalRecord)
                .setMedication("abc")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MedicalRecord> request = new HttpEntity<>(updatedMedicalRecord, headers);

        ResponseEntity<MedicalRecord> response = restTemplate.exchange(url, HttpMethod.PUT, request, MedicalRecord.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        MedicalRecord updated = response.getBody();
        assertEquals(updatedMedicalRecord.getDescription(), updated.getDescription());
        System.out.println("Updated MedicalRecord: " + updated);
    }


    @Test
    @Order(4)
    void testGetAllMedicalRecords() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show All MedicalRecords: " + response.getBody());
    }

    @Test
    @Order(5)
    void testDeleteMedicalRecord() {

        String deleteUrl = BASE_URL + "/delete/" + medicalRecord.getId();
        restTemplate.delete(deleteUrl);

        String readUrl = BASE_URL + "/read/" + medicalRecord.getId();
        ResponseEntity<MedicalRecord> response = restTemplate.getForEntity(readUrl, MedicalRecord.class);

        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());

    }

}
