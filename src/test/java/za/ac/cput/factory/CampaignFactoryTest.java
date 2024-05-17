package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Campaign;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CampaignFactoryTest {

    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;

    @BeforeEach
    void setUp() {
        campaign1 = CampaignFactory.buildCampaign("CAMP001", "Paws for a Cause",
                LocalDate.of(2024, 3, 23), LocalDate.of(2024, 12, 30),
                new String[]{"Provide Shelter and Food for Animals in Need", "Support Medical Treatment and Veterinary Care"});

        campaign2 = CampaignFactory.buildCampaign("", "",
                LocalDate.of(2024, 4, 1), LocalDate.of(2024, 6, 30),
                new String[]{"Provide Scholarships", "Improve School Infrastructure"});

        campaign3 = CampaignFactory.buildCampaign("CAMP003", "Clean Oceans Initiative",
                LocalDate.of(2024, 5, 15), LocalDate.of(2024, 11, 30),
                new String[]{"Promote Recycling", "Reduce Plastic Waste"});
    }

    @Test
    void buildCampaignWithValidData() {
        assertNotNull(campaign1);
        System.out.println(campaign1);

    }

    @Test
    void buildCampaignWithInvalidData() {
        assertNull(campaign2);
        System.out.println(campaign2);
    }


}