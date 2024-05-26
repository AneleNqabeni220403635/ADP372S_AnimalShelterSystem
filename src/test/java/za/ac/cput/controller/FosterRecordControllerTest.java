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
import za.ac.cput.domain.FosterRecord;
import za.ac.cput.factory.FosterRecordFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FosterRecordControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        private final String BASE_URL = "http://localhost:8080/afosterRecord";

        private static FosterRecord fosterRecord;

        @BeforeAll
        public static void setUp(){
            fosterRecord = FosterRecordFactory.buildFosterRecord("246","Dipsy","Pitbull",10,"Female","healthy","playful","None","training","no incident");
        }

        @Test
        void create() {
            String url = BASE_URL + "/create";
            ResponseEntity<FosterRecord> postResponse = restTemplate.postForEntity(url, fosterRecord, FosterRecord.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            FosterRecord fosterRecord1 = postResponse.getBody();
            assertNotNull(fosterRecord1.getAnimalId(), fosterRecord1.getAnimalName());
            assertEquals(fosterRecord.getAnimalId(), fosterRecord1.getAnimalId());
            System.out.println("saved: " + fosterRecord1);
        }

        @Test
        void read() {
            String url = BASE_URL + "/read";
            System.out.println("url: " + url);
            ResponseEntity<FosterRecord> response = restTemplate.getForEntity(url, FosterRecord.class);
            assertEquals(fosterRecord.getAnimalId(), response.getBody().getAnimalId());
            System.out.println("Read FosterRecord: " + response.getBody());
        }

        @Test
        void update() {
            String url = BASE_URL + "/update" + fosterRecord.getAnimalId();
            FosterRecord updateFosterRecord = new FosterRecord.Builder().copy(fosterRecord).setAnimalId("654").build();
            ResponseEntity<FosterRecord> postResponse = restTemplate.postForEntity(url, updateFosterRecord, FosterRecord.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            FosterRecord fosterRecord1 = postResponse.getBody();
            assertEquals(updateFosterRecord.getAnimalId(), fosterRecord1.getAnimalId());
            System.out.println("updated: " + fosterRecord1);
        }

        @Test
        void delete() {
            String url = BASE_URL + "/delete" + fosterRecord.getAnimalId();
            System.out.println("url: " + url);
            restTemplate.delete(url);
            System.out.println("success");
        }

        @Test
        void getAll() {
            String url = BASE_URL + "/getAll";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("Show All FosterRecord");
            System.out.println(response);
            System.out.println(response.getBody());

        }
    }
