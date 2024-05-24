package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Campaign;
import za.ac.cput.factory.CampaignFactory;
import za.ac.cput.factory.DonationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CampaignServiceTest {

   @Autowired
    private CampaignService campaignService;

    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;

    @BeforeEach
    void a_setUp() {
        campaign1 = CampaignFactory.buildCampaign("CAMP001","Adopt-a-Pet Campaign", LocalDateTime.now().minusDays(30),LocalDateTime.now().plusDays(30), "Raising funds to support the local animal shelter");
        assertNotNull(campaign1);
        System.out.println(campaign1);
        campaign2 =CampaignFactory.buildCampaign("CAMP002", "ASPCA Donation Drive", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Provide funds, supplies, and volunteer support to ASPCA-affiliated animal shelters to help them care for and rehome animals in need.");
        assertNotNull(campaign2);
        System.out.println(campaign2);
        campaign3 = CampaignFactory.buildCampaign("CAMP003", "Petco Foundation \"Think Adoption First\" Campaign", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Encourage pet adoption from shelters rather than buying from breeders or pet stores, and raise funds to support the operations and programs of local animal shelters and rescue organizations.");
        assertNotNull(campaign3);
        System.out.println(campaign3);

    }

    @Test
    void b_create() {
        Campaign camp1 = campaignService.create(campaign1);
        assertNotNull(camp1);
        System.out.println(camp1);
        Campaign camp2 = campaignService.create(campaign2);
        assertNotNull(camp2);
        System.out.println(camp2);
    }

    @Test
    void c_read() {
        Campaign savedCampaign = campaignService.create(campaign1);
        assertNotNull(savedCampaign);

        Campaign retrievedCampaign = campaignService.read(savedCampaign.getCampaignId());
        assertNotNull(retrievedCampaign);
        assertEquals(savedCampaign.getCampaignId(), retrievedCampaign.getCampaignId());
        assertEquals(savedCampaign.getName(), retrievedCampaign.getName());
    }

    @Test
    void d_update() {
        Campaign modifiedCampaign1 = new Campaign.CampaignBuilder().copy(campaign2).setName("Tails of Hope")
                .build();
        Campaign updated = campaignService.update(modifiedCampaign1);
        assertNotNull(updated);
        System.out.println(updated);
    }
    @Disabled
    @Test
    void e_delete() {
        Campaign savedCampaign = campaignService.create(campaign1);
        assertNotNull(savedCampaign);

        campaignService.delete(savedCampaign.getCampaignId());

        Campaign deletedCampaign = campaignService.read(savedCampaign.getCampaignId());
        assertNull(deletedCampaign);

        System.out.println("Deleted Campaign:");
        System.out.println(savedCampaign);
    }

    @Test
    void f_getAll() {
        System.out.println(campaignService.getAll());
    }
}