package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Applicant;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantFactoryTest
{
    private LocalDate validApplicationDate;
    private String validApplicationStatus;

    @BeforeEach
    protected void setup()
    {
        validApplicationDate = LocalDate.now();
        validApplicationStatus = "Application_Submitted";
    }

    @Test
    protected void testCreateApplicantWithValidInput()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, validApplicationStatus);
        assertNotNull(applicant);
        assertEquals(validApplicationDate, applicant.getApplicationDate());
        assertEquals(validApplicationStatus, applicant.getApplicationStatus());
    }

    @Test
    protected void testCreateApplicantWithInvalidApplicationStatus()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, "");
        assertNull(applicant);
    }

    @Test
    protected void testCreateApplicantWithNullApplicationStatus()
    {
        Applicant applicant = ApplicantFactory.createApplicant(validApplicationDate, null);
        assertNull(applicant);
    }

}
