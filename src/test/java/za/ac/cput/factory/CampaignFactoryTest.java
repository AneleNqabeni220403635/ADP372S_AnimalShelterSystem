package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CampaignFactoryTest {

    @Test
    public void testCreateCampaign() {
        String campaignId = "CAMP001";
        String name = "Adopt-a-Pet Campaign";
        String objective = "Raising funds to support the local animal shelter";
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        LocalDateTime endDate = LocalDateTime.now().plusDays(30);

        Campaign campaign = new Campaign.CampaignBuilder()
                .setCampaignId(campaignId)
                .setName(name)
                .setObjective(objective)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();

        assertNotNull(campaign);
        assertEquals(campaignId, campaign.getCampaignId());
        assertEquals(name, campaign.getName());
        assertEquals(objective, campaign.getObjective());
        assertEquals(startDate, campaign.getStartDate());
        assertEquals(endDate, campaign.getEndDate());

        System.out.println(campaign);
    }
}