package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.factory.DonationFactory;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DonationFactoryTest {
    private Donation donation1;
    private Donation donation2;
    private Donation donation3;

    @BeforeEach
    void setUp() {
        donation1 = DonationFactory.buildDonation("ABC123", 21300.00F, LocalDate.of(2024, 3, 21), "CAMP001",
                "Paws for a Cause", LocalDate.of(2024, 3, 23), LocalDate.of(2024, 12, 30),
                new String[]{"Provide Shelter and Food for Animals in Need", "Support Medical Treatment and Veterinary Care"});
        donation2 = DonationFactory.buildDonation("XYZ456", 15000.0F, LocalDate.of(2024, 4, 1), "CAMP002",
                "Education for All", LocalDate.of(2024, 3, 30), LocalDate.of(2024, 12, 31),
                new String[]{"Provide Quality Education", "Scholarship Programs"});
        donation3 = DonationFactory.buildDonation("", 500.0F, LocalDate.of(2024, 5, 10), "",
                "", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 6, 30),
                new String[]{"Provide Access to Clean Water", "Community Education"});
    }

    @Test
    void buildDonationWithValidData() {
        assertNotNull(donation2);
        System.out.println(donation2);

    }

    @Test
    void buildDonationWithInvalidData() {
        assertNull(donation3);
        System.out.println(donation3);
    }

}