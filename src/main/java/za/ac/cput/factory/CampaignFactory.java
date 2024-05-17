package za.ac.cput.factory;

import za.ac.cput.domain.Campaign;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Arrays;

public class CampaignFactory {
    public static Campaign buildCampaign(String campaignId, String name, LocalDate startDate, LocalDate endDate, String[] objectives) {
        if (Helper.isNullorEmpty(campaignId) || Helper.isNullorEmpty(name) || Helper.IsValidLocalDate(startDate)
                || Helper.IsValidLocalDate(endDate) || Helper.isNullorEmpty(Arrays.toString(objectives))) {
            return null;
        }

        return new Campaign.CampaignBuilder()
                .setCampaignId(campaignId)
                .setName(name)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setObjectives(objectives)
                .build();
    }
}