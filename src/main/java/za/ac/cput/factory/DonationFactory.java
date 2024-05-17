package za.ac.cput.factory;

import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Arrays;

public class DonationFactory {
    public static Donation buildDonation(String donationId, float amount, LocalDate date, String campaignId, String campaignName,
                                         LocalDate campaignStartDate, LocalDate campaignEndDate, String[] objectives) {
        if (Helper.isNullorEmpty(donationId) || amount <= 0 || Helper.IsValidLocalDate(date) ||
                Helper.isNullorEmpty(campaignId) || Helper.isNullorEmpty(campaignName) ||
                Helper.IsValidLocalDate(campaignStartDate) || Helper.IsValidLocalDate(campaignEndDate) ||
                Helper.isNullorEmpty(Arrays.toString(objectives))) {
            return null;
        }

        Campaign campaign = new Campaign.CampaignBuilder()
                .setCampaignId(campaignId)
                .setName(campaignName)
                .setStartDate(campaignStartDate)
                .setEndDate(campaignEndDate)
                .setObjectives(objectives)
                .build();

        return new Donation.DonationBuilder()
                .setDonationId(donationId)
                .setAmount(amount)
                .setDate(date)
                .setCampaign(campaign)
                .build();
    }
}