package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Campaign;
import za.ac.cput.domain.Donation;
import za.ac.cput.domain.MedicalRecord;
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
        donation1 = DonationFactory.buildDonation("ABC123", 21300F, LocalDateTime.now().minusDays(30), "CAMP001", "Adopt-a-Pet Campaign", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Raising funds to support the local animal shelter");
        assertNotNull(donation1);
        System.out.println(donation1);
        donation2 = DonationFactory.buildDonation("XYZ456", 15000F, LocalDateTime.now().minusDays(30), "CAMP002", "ASPCA Donation Drive", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Provide funds, supplies, and volunteer support to ASPCA-affiliated animal shelters to help them care for and rehome animals in need.");
        assertNotNull(donation2);
        System.out.println(donation2);
        donation3 = DonationFactory.buildDonation("FHY219", 5000F, LocalDateTime.now().minusDays(30), "CAMP003", "Petco Foundation \"Think Adoption First\" Campaign", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Encourage pet adoption from shelters rather than buying from breeders or pet stores, and raise funds to support the operations and programs of local animal shelters and rescue organizations.");
        assertNotNull(donation3);
        System.out.println(donation3);
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


    @Test
    void e_getAll() {
        List<Donation> allDonations = donationService.getAll();
        assertFalse(allDonations.isEmpty());
        System.out.println("All Donations:");
        for (Donation donation : allDonations) {
            System.out.println(donation.toString());
        }
    }
}