package za.ac.cput.factory;

import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CampaignFactory {
    public static Campaign buildCampaign(String campaignId, String name, LocalDateTime startDate, LocalDateTime endDate, String objective, String donationId, float amount, LocalDateTime date) {
        Donation donation = DonationFactory.buildDonation(donationId, amount, LocalDateTime.now());

        if(Helper.isNullorEmpty(name) || startDate == null || endDate == null || Helper.isNullorEmpty(objective) || donation == null){
            return  null;
        }


        return new Campaign.CampaignBuilder()
                .setCampaignId(campaignId)
                .setName(name)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setObjective(objective)
                .build();
    }
}