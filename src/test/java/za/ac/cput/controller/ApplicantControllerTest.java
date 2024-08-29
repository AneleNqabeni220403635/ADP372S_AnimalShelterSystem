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
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.ApplicantFactory;
import za.ac.cput.factory.PetOwnerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicantControllerTest
{
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @LocalManagementPort
    private int managementPort;
    private Applicant applicant;
    private PetOwner petOwner;

    private final String BASE_URL = "";

    @BeforeEach
    public void setUp()
    {
        petOwner = PetOwnerFactory.buildPetOwner("Jack","Parrow", "0721234567", "parrowj@cput.ac.za", "80 Constitution Street, Cape Town, 7925");
        applicant = ApplicantFactory.createApplicant(
                LocalDate.now(),
                "Application Submitted",petOwner, null, null
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
        ResponseEntity<Applicant> response = restTemplate.postForEntity(url, applicant, Applicant.class);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        Applicant applicant1 = response.getBody();
        assertTrue(applicant1.getId() > 0L);
        System.out.println("Created: " + applicant1);
    }

    @Test
    @Order(3)
    public void read()
    {
        // Given
        String url = BASE_URL + "/read/" + applicant.getId();
        System.out.println("URL: " + url);

        // When
        ResponseEntity<Applicant> response = restTemplate.getForEntity(url, Applicant.class);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        Applicant report = response.getBody();
        assertEquals(applicant.getId(), report.getId());
        assertEquals(applicant.getApplicationDate(), report.getApplicationDate());
        assertEquals(applicant.getApplicationStatus(), report.getApplicationStatus());
        System.out.println("Read: " + applicant);
    }

    @Test
    @Order(4)
    public void update ()
    {
        // Given
        String url = BASE_URL + "/update";
        Applicant updatedApplicant = new Applicant.Builder().copy(applicant).setApplicationStatus("Background Check").build();

        // When
        ResponseEntity<Applicant> response = restTemplate.postForEntity(url, updatedApplicant, Applicant.class);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        Applicant applicant1 = response.getBody();

        assertEquals(applicant.getApplicationStatus(), updatedApplicant.getApplicationStatus());
        System.out.println("Updated" + applicant1);
    }

    @Test
    @Order(5)
    public void getAll()
    {
        // Given
        String url = BASE_URL + "/getAll"; // either getall or getAll should do the trick, just a bit easier on the eyes to read

        // When
        ResponseEntity<Applicant[]> response = restTemplate.getForEntity(url, Applicant[].class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length> 0);
        for (Applicant appl: response.getBody())
        {
            System.out.println(appl);
        }
    }

    @Test
    @Order(6)
    public void delete ()
    {
        // Given
        String url = BASE_URL +"/delete/"+ applicant.getId();

        // When
        restTemplate.delete(url);

        // Then
        ResponseEntity<Applicant> response = restTemplate.getForEntity(BASE_URL + "/read/" + applicant.getId(), Applicant.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }
}
