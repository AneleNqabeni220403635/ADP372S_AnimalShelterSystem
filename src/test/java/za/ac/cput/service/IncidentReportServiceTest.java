package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.repository.IncidentReportRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class IncidentReportServiceTest {

    @Autowired
    private IncidentReportService service;

    @Autowired
    private IncidentReportRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void create()
    {
        Animal animal = new Animal();
        IncidentReport incidentReport = new IncidentReport.Builder()
                .setAnimal(animal)
                .setIncidentType("Attack")
                .setDescription("Dog attacked a person")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Medical attention provided")
                .setReportedBy("Jack Sparrow")
                .build();

        IncidentReport created = service.create(incidentReport);
        assertNotNull(created.getId());
        assertEquals("Attack", created.getIncidentType());
    }

    @Test
    void read()
    {
        Animal animal = new Animal();
        IncidentReport incidentReport = new IncidentReport.Builder()
                .setAnimal(animal)
                .setIncidentType("Injury")
                .setDescription("Cat injured by another animal")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Veterinary care provided")
                .setReportedBy("Jack Sparrow")
                .build();

        IncidentReport created = service.create(incidentReport);
        IncidentReport read = service.read(created.getId());
        assertEquals(created, read);
    }

    @Test
    void update()
    {
        Animal animal = new Animal();
        IncidentReport incidentReport = new IncidentReport.Builder()
                .setAnimal(animal)
                .setIncidentType("Stung by Bee")
                .setDescription("Dog stung by bee")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Antihistamines administered")
                .setReportedBy("Jack Sparrow")
                .build();

        IncidentReport created = service.create(incidentReport);
        created = new IncidentReport.Builder()
                .copy(created)
                .setDescription("Dog stung by multiple bees")
                .build();

        IncidentReport updated = service.update(created);
        assertEquals("Dog stung by multiple bees", updated.getDescription());
    }

    @Test
    void findAll()
    {
        Animal animal1 = new Animal();
        IncidentReport incidentReport1 = new IncidentReport.Builder()
                .setAnimal(animal1)
                .setIncidentType("Attack")
                .setDescription("Dog attacked a person")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Medical attention provided")
                .setReportedBy("Jack Sparrow")
                .build();

        Animal animal2 = new Animal();
        IncidentReport incidentReport2 = new IncidentReport.Builder()
                .setAnimal(animal2)
                .setIncidentType("Injury")
                .setDescription("Cat injured by another animal")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Veterinary care provided")
                .setReportedBy("Jack Sparrow")
                .build();

        service.create(incidentReport1);
        service.create(incidentReport2);

        Set<IncidentReport> incidentReports = new HashSet<>(service.findAll());
        assertEquals(2, incidentReports.size());
    }

    @Test
    void delete()
    {
        Animal animal = new Animal();
        IncidentReport incidentReport = new IncidentReport.Builder()
                .setAnimal(animal)
                .setIncidentType("Stung by Bee")
                .setDescription("Dog stung by bee")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Antihistamines administered")
                .setReportedBy("Alice")
                .build();

        IncidentReport created = service.create(incidentReport);
        boolean deleted = service.delete(created.getId());
        assertTrue(deleted);

        IncidentReport deletedReport = service.read(created.getId());
        assertNull(deletedReport);
    }
}