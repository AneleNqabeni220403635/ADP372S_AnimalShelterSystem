package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Donation;
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
        System.out.println(donation1.toString());
        donation2 = DonationFactory.buildDonation("XYZ456", 15000F, LocalDateTime.now().minusDays(30), "CAMP002", "ASPCA Donation Drive", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Provide funds, supplies, and volunteer support to ASPCA-affiliated animal shelters to help them care for and rehome animals in need.");
        assertNotNull(donation2);
        System.out.println(donation2.toString());
        donation3 = DonationFactory.buildDonation("FHY219", 5000F, LocalDateTime.now().minusDays(30), "CAMP003", "Petco Foundation \"Think Adoption First\" Campaign", LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30), "Encourage pet adoption from shelters rather than buying from breeders or pet stores, and raise funds to support the operations and programs of local animal shelters and rescue organizations.");
        assertNotNull(donation3);
        System.out.println(donation3.toString());
    }

    @Test
    void b_create() {
        Donation savedDonation = donationService.create(donation1);
        assertNotNull(savedDonation);
        assertNotNull(savedDonation.getDonationId());
        System.out.println("Saved Donation:");
        System.out.println(savedDonation.toString());
    }

    @Test
    void c_read() {
        Donation createdDonation = donationService.create(donation1);
        assertNotNull(createdDonation);

        Donation retrievedDonation = donationService.read(createdDonation.getDonationId());
        assertNotNull(retrievedDonation);
        assertEquals(createdDonation.getDonationId(), retrievedDonation.getDonationId());
        System.out.println("Retrieved Donation:");
        System.out.println(retrievedDonation.toString());
    }

    @Disabled
    @Test
    void d_delete() {
        Donation savedDonation = donationService.create(donation1);
        assertNotNull(savedDonation);

        boolean isDeleted = donationService.delete(savedDonation.getDonationId());
        assertTrue(isDeleted);

        Donation deletedDonation = donationService.read(savedDonation.getDonationId());
        assertNull(deletedDonation);

        System.out.println("Deleted Donation:");
        System.out.println(savedDonation.toString());
    }

//    @Disabled
//    @Test
//    void d_delete() {
//        Donation savedDonation = donationService.save(donation1);
//        assertNotNull(savedDonation);
//
//        donationService.delete(savedDonation.getDonationId());
//
//        Donation deletedDonation = donationService.read(savedDonation.getDonationId());
//        assertNull(deletedDonation);
//
//        System.out.println("Deleted Donation:");
//        System.out.println(savedDonation.toString());
//    }

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