package za.ac.cput.factory;

import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DonationFactory {
    public static Donation buildDonation(String donationId, float amount, LocalDateTime date) {
        if(Helper.isNullorEmpty(donationId) || amount < 0 || date == null) {
            return null;
        }
        return new Donation.DonationBuilder()
                .setDonationId(donationId)
                .setAmount(amount)
                .setDate(date)
                .build();
    }
}