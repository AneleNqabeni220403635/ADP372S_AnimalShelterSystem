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
import za.ac.cput.domain.FosterCare;
import za.ac.cput.domain.FosterRecord;
import za.ac.cput.factory.FosterCareFactory;
import za.ac.cput.factory.FosterRecordFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class FosterCareControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        private final String BASE_URL = "http://localhost:8080/fosterCare";

        private static FosterCare fosterCare;
        private static FosterRecord fosterRecord;

        @BeforeAll
        public static void setUp() {
            fosterCare = FosterCareFactory.buildFosterCare("Thando",123789456,"50 Orchards Road","House","4","Experienced","Active","None", (List<FosterRecord>) fosterRecord);
            fosterRecord = FosterRecordFactory.buildFosterRecord("225","Snoopy","Pitbull",9,"male","healthy","naughty","none","walking","none");
        }

        @Test
        void create() {
            String url = BASE_URL + "/create";
            ResponseEntity<FosterCare> postResponse = restTemplate.postForEntity(url, fosterCare, FosterCare.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            FosterCare fosterCare1 = postResponse.getBody();
            assertEquals(fosterCare.getCaregiverName(), fosterCare1.getCaregiverName());
            System.out.println("Saved" + fosterCare1);

            ResponseEntity<FosterRecord> postFosterRecord = restTemplate.postForEntity(url, fosterRecord, FosterRecord.class);
            assertNotNull(postFosterRecord);
            assertNotNull(postFosterRecord.getBody());
            FosterRecord fosterRecord1 = postFosterRecord.getBody();
            System.out.println("Saved" + fosterRecord1);

        }

        @Test
        void read() {
            String url = BASE_URL + "/read" + fosterCare.getCaregiverName();
            System.out.println("URL: " + url);
            ResponseEntity<FosterCare> response = restTemplate.getForEntity(url, FosterCare.class);
            assertEquals(fosterCare.getCaregiverName(), response.getBody().getCaregiverName());
            System.out.println("Read:" + response.getBody());
        }

        @Test
        void update() {
            String url = BASE_URL + "/update" + fosterCare.getCaregiverName();
            FosterCare newFosterCare = new FosterCare.Builder().copy(fosterCare).setCaregiverName("Lindiwe").Build();
            ResponseEntity<FosterCare> postresponse = restTemplate.postForEntity(url, newFosterCare, FosterCare.class);
            assertNotNull(postresponse);
            assertNotNull(postresponse.getBody());
            FosterCare fosterCare1 = postresponse.getBody();
            assertEquals(newFosterCare.getCaregiverName(), fosterCare1.getCaregiverName());
            System.out.println("Updated" + fosterCare1);
        }

        @Test
        void delete() {
            String url = BASE_URL + "/delete" + fosterCare.getCaregiverName();
            System.out.println("URL: " + url);
            restTemplate.delete(url);
            System.out.println("success");
        }

        @Test
        void getAll() {
            String url = BASE_URL + "/getAll";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("Show All");
            System.out.println(response);
            System.out.println(response.getBody());
        }
    }
