package za.ac.cput.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.Animal;

import java.time.LocalDate;

public class AdoptionFactoryTest {

    @Test
    public void testCreateAdoption() {

        Long adoptionId = 1L;
        String applicantName = "Kitty Kat";
        LocalDate applicationDate = LocalDate.now();
        String status = "Pending";

        Animal animal = AnimalFactory.buildAnimal(123456789L, "Leo", 5, "Lion", null);

        Adoption adoption = AdoptionFactory.createAdoption(adoptionId, applicantName, applicationDate, status, animal);

        Assertions.assertNotNull(adoption);
        Assertions.assertEquals(adoptionId, adoption.getAdoptionId());
        Assertions.assertEquals(applicantName, adoption.getApplicantName());
        Assertions.assertEquals(applicationDate, adoption.getApplicationDate());
        Assertions.assertEquals(status, adoption.getStatus());
        Assertions.assertEquals(animal, adoption.getAnimal());
    }
}