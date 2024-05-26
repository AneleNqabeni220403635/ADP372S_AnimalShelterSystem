package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalFactory;
import za.ac.cput.factory.IncidentReportFactory;
import za.ac.cput.factory.MedicalRecordFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncidentReportControllerTest
{
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @LocalManagementPort
    private int managementPort;
    private IncidentReport incidentReport;

    private final String BASE_URL = "";

    @BeforeEach
    public void setUp()
    {
        // Animal and medicalRecords needs work,
        // Come back to this once that is sorted.
        // in Animal controller, medical records is set to null, but if its null in the builder, null is returned for animal
        MedicalRecord medicalRecord = MedicalRecordFactory.buildDefaultMedicalRecord(99999999999L);
        Animal experiment626 = AnimalFactory.buildAnimal(99999999999L, "Stitch", 3, "Alien", medicalRecord);

        incidentReport = IncidentReportFactory.createIncidentReport(
                experiment626,
                "Attack",
                LocalDateTime.now(),
                "Alien dog attacked Hawaiians",
                "Captured",
                "Lilo"
        );
    }

    @Test
    @Order(1)
    public void portsInUse()
    {
        // Adding this extra test to see if we indeed get the port numbers back.
        // In some examples they use a random portnumber but then hardcode it anyway
        System.out.println(port);
        System.out.println(managementPort);
        assertNotEquals(0, port);
        assertNotEquals(0, managementPort);
    }

    @Test
    @Order(2)
    public void create()
    {
        // Given
        String url = BASE_URL + "/create";

        // When
        ResponseEntity<IncidentReport> response = restTemplate.postForEntity(url, incidentReport, IncidentReport.class);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        IncidentReport incidentReport1 = response.getBody();
        assertTrue(incidentReport1.getId() > 0L);
        System.out.println("Created: " + incidentReport1);
    }

    @Test
    @Order(3)
    public void read()
    {
        // Given
        String url = BASE_URL + "/read/" + incidentReport.getId();
        System.out.println("URL: " + url);

        // When
        ResponseEntity<IncidentReport> response = restTemplate.getForEntity(url, IncidentReport.class);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        IncidentReport report = response.getBody();
        assertEquals(incidentReport.getId(), report.getId());
        assertEquals(incidentReport.getIncidentType(), report.getIncidentType());
        assertEquals(incidentReport.getReportedBy(), report.getReportedBy());
        System.out.println("Read: " + incidentReport);
    }

    @Test
    @Order(4)
    public void update ()
    {
        // Given
        String url = BASE_URL + "/update";
        IncidentReport updatedIncidentReport = new IncidentReport.Builder().copy(incidentReport).setDescription("Alien dog terrorising Hawaii").build();

        // When
        ResponseEntity<IncidentReport> response = restTemplate.postForEntity(url, updatedIncidentReport, IncidentReport.class);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        IncidentReport incidentReport1 = response.getBody();

        assertEquals(incidentReport.getDescription(), updatedIncidentReport.getDescription());
        System.out.println("Updated" + incidentReport1);
    }

    @Test
    @Order(5)
    public void getAll()
    {
        // Given
        String url = BASE_URL + "/getAll"; // either getall or getAll should do the trick, just a bit easier on the eyes to read

        // When
        ResponseEntity<IncidentReport[]> response = restTemplate.getForEntity(url, IncidentReport[].class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length> 0);
        for (IncidentReport report: response.getBody())
        {
            System.out.println(report);
        }
    }

    @Test
    @Order(6)
    public void delete ()
    {
        // Given
        String url = BASE_URL +"/delete/"+ incidentReport.getId();

        // When
        restTemplate.delete(url);

        // Then
        ResponseEntity<IncidentReport> response = restTemplate.getForEntity(BASE_URL + "/read/" + incidentReport.getId(), IncidentReport.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }
}
