package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.IncidentReport;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentReportFactoryTest
{
    private Long validId;
    private Animal validAnimal;
    private String validIncidentType;
    private LocalDateTime validIncidentDate;
    private String validDescription;
    private String validActionsTaken;
    private String validReportedBy;

    @BeforeEach
    protected void setup()
    {
        validId = 1L;
        validAnimal = new Animal.Builder().setAnimalCode(10101L).build();
        validIncidentType = "Injury";
        validIncidentDate = LocalDateTime.now();
        validDescription = "Animal got injured.";
        validActionsTaken = "Treated with medicine.";
        validReportedBy = "Cpt Jack Sparrow";
    }

    @Test
    protected void testCreateIncidentReportWithValidInput()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, validDescription, validActionsTaken, validReportedBy);
        assertNotNull(incidentReport);
        assertEquals(validId, incidentReport.getId());
        assertEquals(validAnimal, incidentReport.getAnimal());
        assertEquals(validIncidentType, incidentReport.getIncidentType());
        assertEquals(validIncidentDate, incidentReport.getIncidentDate());
    }

    @Test
    protected void testCreateIncidentReportWithNullId()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(null, validAnimal, validIncidentType, validIncidentDate, validDescription, validActionsTaken, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithInvalidAnimal()
    {
        Animal invalidAnimal = new Animal.Builder().setAnimalCode(0L).build();
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, invalidAnimal, validIncidentType, validIncidentDate, validDescription, validActionsTaken, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithNullIncidentType()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, null, validIncidentDate, validDescription, validActionsTaken, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithEmptyIncidentType()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, "", validIncidentDate, validDescription, validActionsTaken, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithNullDescription()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, null, validActionsTaken, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithEmptyDescription()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, "", validActionsTaken, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithNullActionsTaken()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, validDescription, null, validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithEmptyActionsTaken()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, validDescription, "", validReportedBy);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithNullReportedBy()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, validDescription, validActionsTaken, null);
        assertNull(incidentReport);
    }

    @Test
    protected void testCreateIncidentReportWithEmptyReportedBy()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(validId, validAnimal, validIncidentType, validIncidentDate, validDescription, validActionsTaken, "");
        assertNull(incidentReport);
    }
}
