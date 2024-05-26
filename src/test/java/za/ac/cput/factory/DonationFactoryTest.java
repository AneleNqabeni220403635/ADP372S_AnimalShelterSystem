package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DonationFactoryTest {
    private Donation donation1;
    private  Donation donation2;
    private  Donation donation3;

    @BeforeEach
    void setUp() {
        donation1 = DonationFactory.buildDonation("NDW001", 233600, LocalDateTime.now());

        donation2 = DonationFactory.buildDonation("", 70000,LocalDateTime.now());

        donation3 = donation1;

    }

    @Test
    public void testCreateValidDonation() {
        assertNotNull(donation1);
        System.out.println(donation1);

    }

    @Test
    void testCreateInvalidDonation() {
        assertNull(donation2);
        System.out.println(donation2);
    }

    @Test
    void testCreateIdentity() {
        assertEquals(donation3, donation1);
    }
}