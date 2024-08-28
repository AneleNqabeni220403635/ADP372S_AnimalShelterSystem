package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantFactoryTest
{
    private LocalDate validApplicationDate;
    private String validApplicationStatus;
    private PetOwner validPetOwner;
    private Dog validDog;
    private Cat validCat;

    @BeforeEach
    protected void setup()
    {
        validApplicationDate = LocalDate.now();
        validApplicationStatus = "Application_Submitted";
        validPetOwner = PetOwnerFactory.buildPetOwner("Jack", "Parrow", "0211234567", "parrowj@cput.ac.za", "1 Roeland Street, Cape Town, 8000");
        validDog = DogFactory.buildDog("Simba", "Medium", 7, "Male", "Rottweiler", 104);
        validCat = CatFactory.buildCat("Garfield", "Large",5, "Male", "Maincoon", 55 );
    }

    @Test
    protected void testCreateApplicantWithValidInput_Dog()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, validApplicationStatus, validPetOwner, validDog, null);
        assertNotNull(applicant);
        assertEquals(validApplicationDate, applicant.getApplicationDate());
        assertEquals(validApplicationStatus, applicant.getApplicationStatus());
        assertEquals(validPetOwner, applicant.getPetOwner());
        assertEquals(validDog, applicant.getDog());
    }

    @Test
    protected void testCreateApplicantWithValidInput_Cat()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, validApplicationStatus, validPetOwner, null, validCat);
        assertNotNull(applicant);
        assertEquals(validApplicationDate, applicant.getApplicationDate());
        assertEquals(validApplicationStatus, applicant.getApplicationStatus());
        assertEquals(validPetOwner, applicant.getPetOwner());
        assertEquals(validCat, applicant.getCat());
    }

    @Test
    protected void testCreateApplicantWithInvalidApplicationStatus()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, "", validPetOwner, null, null);
        assertNull(applicant);
    }

    @Test
    protected void testCreateApplicantWithNullApplicationStatus()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, null, validPetOwner, validDog, null);
        assertNull(applicant);
    }

    @Test
    protected void testCreateApplicantWithNullPetOwner()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, null, null, validDog, null);
        assertNull(applicant);
    }
}
