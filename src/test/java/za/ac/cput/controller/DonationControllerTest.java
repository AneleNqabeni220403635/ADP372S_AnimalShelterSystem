package za.ac.cput.controller;

/* DonationControllerTest.java
Controller Test Class
Author: Asanda Mbangata (222927259)
Date: 25 May 2024
*/

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.factory.DonationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DonationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/donation";

    private static Donation donation;

    @BeforeAll
    public static void setUp() {
        donation = DonationFactory.buildDonation("MDY093", 125000, LocalDateTime.now());
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Donation> postResponse = restTemplate.postForEntity(url, donation, Donation.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Donation donationSaved = postResponse.getBody();
        assertEquals(donation.getDonationId(), donationSaved.getDonationId());
        System.out.println("Saved data: " + donationSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + donation.getDonationId();
        System.out.println("URL: " + url);
        ResponseEntity<Donation> response = restTemplate.getForEntity(url, Donation.class);
        assertEquals(donation.getDonationId(), response.getBody().getDonationId());
        System.out.println("Read Animal: " + response.getBody());
    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update" + donation.getDonationId();
        Donation newDonation = new Donation.DonationBuilder().copy(donation).setAmount(250000).build();
        ResponseEntity<Donation> postResponse = restTemplate.postForEntity(url, newDonation, Donation.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Donation donationUpdated = postResponse.getBody();
        assertEquals(newDonation.getDonationId(), donationUpdated.getDonationId());
        System.out.println("Update Animal: " + donationUpdated);
    }

    @Test
    void d_delete() {
        String url = BASE_URL +"/delete/"+ donation.getDonationId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted donation ***");
    }

    @Test
    void e_getAll() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}