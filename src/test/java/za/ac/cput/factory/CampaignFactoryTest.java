package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CampaignFactoryTest {

    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;

    @BeforeEach
    void setUp() {
        campaign1 = CampaignFactory.buildCampaign("CAMP001","Adopt-a-Pet Campaign",LocalDateTime.now().minusDays(30),LocalDateTime.now().plusDays(30),"Raising funds to support the local animal shelter","NDW001", 400000,LocalDateTime.now());

        campaign2 = CampaignFactory.buildCampaign("CAMP002", "ASPCA Donation Drive",LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30),"Provide funds, supplies, and volunteer support to ASPCA-affiliated animal shelters to help them care for and rehome animals in need.", "VTW002", 234000,LocalDateTime.now());

        campaign3 = campaign2;

    }

    @Test
    public void testCreateCampaign() {
        assertNotNull(campaign1);
        System.out.println(campaign1);

    }

    @Test
    void testCreateInvalidCampaign() {
        assertNull(campaign2);
    }

    @Test
    void testCreateIdentity() {
        assertEquals(campaign3, campaign2);
    }

}