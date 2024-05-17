package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.LostAndFound;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LostAndFoundFactoryTest
{
    @Test
    protected void testBuildLostAndFoundSuccess()
    {
        // Given
        Long id = 1L;
        String species = "Dog";
        String breed = "Labrador";
        String description = "Found near the park";
        LocalDateTime reportedDate = LocalDateTime.now();
        String status = "Found";
        String reporterContactName = "Jack Sparrow";
        String reporterContactNumber = "0721234567";

        // When
        LostAndFound lostAndFound = LostAndFoundFactory.buildLostAndFound(id, species, breed, description, reportedDate, status, reporterContactName, reporterContactNumber);

        // Then
        assertNotNull(lostAndFound);
        assertEquals(id, lostAndFound.getId());
        assertEquals(species, lostAndFound.getSpecies());
        assertEquals(breed, lostAndFound.getBreed());
        assertEquals(description, lostAndFound.getDescription());
        assertEquals(reportedDate, lostAndFound.getReportedDate());
        assertEquals(status, lostAndFound.getStatus());
        assertEquals(reporterContactName, lostAndFound.getReporterContactName());
        assertEquals(reporterContactNumber, lostAndFound.getReporterContactNumber());
    }

    @Test
    protected void testBuildLostAndFoundNullId()
    {
        // Given
        Long id = null;
        String species = "Dog";
        String breed = "Labrador";
        String description = "Found near the park";
        LocalDateTime reportedDate = LocalDateTime.now();
        String status = "Found";
        String reporterContactName = "Jack Sparrow";
        String reporterContactNumber = "0721234567";

        // When
        LostAndFound lostAndFound = LostAndFoundFactory.buildLostAndFound(id, species, breed, description, reportedDate, status, reporterContactName, reporterContactNumber);

        // Then
        assertNull(lostAndFound);
    }

    @Test
    protected void testBuildLostAndFoundEmptySpecies()
    {
        // Given
        Long id = 1L;
        String species = "";
        String breed = "Labrador";
        String description = "Found near the park";
        LocalDateTime reportedDate = LocalDateTime.now();
        String status = "Found";
        String reporterContactName = "Jack Sparrow";
        String reporterContactNumber = "0721234567";

        // When
        LostAndFound lostAndFound = LostAndFoundFactory.buildLostAndFound(id, species, breed, description, reportedDate, status, reporterContactName, reporterContactNumber);

        // Then
        assertNull(lostAndFound);
    }

    @Test
    protected void testBuildLostAndFoundNullReportedDate()
    {
        // Given
        Long id = 1L;
        String species = "Dog";
        String breed = "Labrador";
        String description = "Found near the park";
        LocalDateTime reportedDate = null;
        String status = "Found";
        String reporterContactName = "Jack Sparrow";
        String reporterContactNumber = "0721234567";

        // When
        LostAndFound lostAndFound = LostAndFoundFactory.buildLostAndFound(id, species, breed, description, reportedDate, status, reporterContactName, reporterContactNumber);

        // Then
        assertNull(lostAndFound);
    }

    @Test
    protected void testBuildLostAndFoundEmptyReporterContactName()
    {
        // Given
        Long id = 1L;
        String species = "Dog";
        String breed = "Labrador";
        String description = "Found near the park";
        LocalDateTime reportedDate = LocalDateTime.now();
        String status = "Found";
        String reporterContactName = "";
        String reporterContactNumber = "0721234567";

        // When
        LostAndFound lostAndFound = LostAndFoundFactory.buildLostAndFound(id, species, breed, description, reportedDate, status, reporterContactName, reporterContactNumber);

        // Then
        assertNull(lostAndFound);
    }

    @Test
    public void testBuildLostAndFoundEmptyReporterContactNumber()
    {
        // Given
        Long id = 1L;
        String species = "Dog";
        String breed = "Labrador";
        String description = "Found near the park";
        LocalDateTime reportedDate = LocalDateTime.now();
        String status = "Found";
        String reporterContactName = "Jack Sparrow";
        String reporterContactNumber = "";

        // When
        LostAndFound lostAndFound = LostAndFoundFactory.buildLostAndFound(id, species, breed, description, reportedDate, status, reporterContactName, reporterContactNumber);

        // Then
        assertNull(lostAndFound);
    }
}
