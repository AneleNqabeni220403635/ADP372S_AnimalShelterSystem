package za.ac.cput.factory;

import za.ac.cput.domain.Campaign;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CampaignFactory {
    public static Campaign buildCampaign(String campaignId, String name, LocalDateTime startDate, LocalDateTime endDate, String objective) {
        return new Campaign.CampaignBuilder()
                .setCampaignId(campaignId)
                .setName(name)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setObjective(objective)
                .build();
    }
}