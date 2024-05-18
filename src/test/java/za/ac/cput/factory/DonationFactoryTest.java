package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DonationFactoryTest {

    @Test
    public void testCreateDonation() {
        Campaign campaign = new Campaign.CampaignBuilder()
                .setCampaignId("CAMP001")
                .setName("Adopt-a-Pet Campaign")
                .setObjective("Raising funds to support the local animal shelter")
                .setStartDate(LocalDateTime.now().minusDays(30))
                .setEndDate(LocalDateTime.now().plusDays(30))
                .build();

        String donationId = "ABC123";
        float donationAmount = 250.50f;
        LocalDateTime donationDate = LocalDateTime.now();

        Donation donation = new Donation.DonationBuilder()
                .setDonationId(donationId)
                .setAmount(donationAmount)
                .setDate(donationDate)
                .setCampaign(campaign)
                .build();

        assertNotNull(donation);
        assertEquals(donationId, donation.getDonationId());
        assertEquals(donationAmount, donation.getAmount(), 0.01);
        assertEquals(campaign, donation.getCampaign());

        System.out.println(donation);
    }
}