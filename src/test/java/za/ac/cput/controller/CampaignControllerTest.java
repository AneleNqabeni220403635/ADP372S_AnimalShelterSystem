package za.ac.cput.controller;

/* CampaignControllerTest.java
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
import za.ac.cput.factory.CampaignFactory;
import za.ac.cput.factory.DonationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CampaignControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/campaign";

    private static Campaign campaign;
    private static Donation donation;

    @BeforeAll
    public static void setUp() {
        campaign = CampaignFactory.buildCampaign("CAMP023", "Petco Foundation's Holiday Wishes Campaign", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30)," Raise funds and provide grants to support animal shelters", "MDY093", 125000,LocalDateTime.now());
        donation = DonationFactory.buildDonation("MDY093", 125000, LocalDateTime.now());
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Campaign> postResponse = restTemplate.postForEntity(url, campaign, Campaign.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Campaign campaignSaved = postResponse.getBody();
        assertEquals(campaign.getCampaignId(), campaignSaved.getCampaignId());
        System.out.println("Saved data: " + campaignSaved);

        ResponseEntity<Donation> postDonation = restTemplate.postForEntity(url,donation, Donation.class);
        assertNotNull(postDonation);
        assertNotNull(postDonation.getBody());
        Donation donationSaved = postDonation.getBody();
        System.out.println("Saved data: " + donationSaved);

    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + campaign.getCampaignId();
        System.out.println("URL: " + url);
        ResponseEntity<Campaign> response = restTemplate.getForEntity(url, Campaign.class);
        assertEquals(campaign.getCampaignId(), response.getBody().getCampaignId());
        System.out.println("Read Campaign: " + response.getBody());
    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update" + campaign.getCampaignId();
        Campaign newCampaign = new Campaign.CampaignBuilder().copy(campaign).setName("Adopt Don't Shop").build();
        ResponseEntity<Campaign> postResponse = restTemplate.postForEntity(url, newCampaign, Campaign.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Campaign campaignUpdated = postResponse.getBody();
        assertEquals(newCampaign.getCampaignId(), campaignUpdated.getCampaignId());
        System.out.println("Update Campaign: " + campaignUpdated);

    }

    @Test
    void d_delete() {
        String url = BASE_URL +"/delete/"+ campaign.getCampaignId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: **** Deleted campaign ***");
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