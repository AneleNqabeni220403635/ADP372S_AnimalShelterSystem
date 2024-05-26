package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.factory.CampaignFactory;
import za.ac.cput.factory.DonationFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class DonationServiceTest {

    @Autowired
    private DonationService donationService;

    private Donation donation1;
    private Donation donation2;
    private Donation donation3;

    @BeforeEach
    void a_setUp() {

        donation1 = DonationFactory.buildDonation("ABC123", 21300F, LocalDateTime.now().minusDays(30));
        donation2 = DonationFactory.buildDonation("XYZ456", 15000F, LocalDateTime.now().minusDays(30));
        donation3 = DonationFactory.buildDonation("FHY219", 5000F, LocalDateTime.now().minusDays(30));
    }

    @Test
    void b_create() {
        Donation savedDonation = donationService.create(donation1);
        assertNotNull(savedDonation);
        assertNotNull(savedDonation.getDonationId());
        System.out.println("Saved Donation:");
        System.out.println(savedDonation);
    }

    @Test
    void c_read() {
        Donation createdDonation = donationService.create(donation1);
        assertNotNull(createdDonation);

        Donation retrievedDonation = donationService.read(createdDonation.getDonationId());
        assertNotNull(retrievedDonation);
        assertEquals(createdDonation.getDonationId(), retrievedDonation.getDonationId());
        System.out.println("Retrieved Donation:");
        System.out.println(retrievedDonation);
    }

    @Test
    void d_update() {
        Donation updatedDonation1 = new Donation.DonationBuilder().copy(donation1).setAmount(56000)
                .build();
        Donation updated = donationService.update(updatedDonation1);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Disabled
    @Test
    void e_delete() {
        Donation savedDonation = donationService.create(donation1);
        assertNotNull(savedDonation);

        donationService.delete(savedDonation.getDonationId());

        Donation deletedDonation = donationService.read(savedDonation.getDonationId());
        assertNull(deletedDonation);

        System.out.println("Deleted Donation:");
        System.out.println(savedDonation);
    }


    @Test
    void e_getAll() {
        System.out.println(donationService.getAll());

    }
}