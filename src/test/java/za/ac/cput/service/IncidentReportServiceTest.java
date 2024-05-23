package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.repository.AnimalRepository;
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
    private IncidentReportRepository incidentReportRepository;

    @Autowired
    private AnimalRepository animalRepository;

    private Animal animal;

    @BeforeEach
    void setUp()
    {
        incidentReportRepository.deleteAll();

        animal = new Animal.Builder()
                .setAnimalCode(999999999999L)
                .setName("Salem")
                .setAge(2)
                .setType("Cat")
                .build();
        animal = animalRepository.save(animal);
    }

    @AfterEach
    public void tearDown()
    {
        animalRepository.deleteById(999999999997L);
        animalRepository.deleteById(999999999998L);
        animalRepository.deleteById(999999999999L);
    }

    @Test
    void create()
    {
        IncidentReport incidentReport = new IncidentReport.Builder()
                .setAnimal(animal)
                .setIncidentType("Attack")
                .setDescription("Cat attacked a person")
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
        assertEquals(created.getId(), read.getId());
        assertEquals(created.getAnimal(), read.getAnimal());
        assertEquals(created.getIncidentType(), read.getIncidentType());
        assertEquals(created.getDescription(), read.getDescription());
        assertEquals(created.getActionsTaken(), read.getActionsTaken());
        assertEquals(created.getReportedBy(), read.getReportedBy());
    }

    @Test
    public void update()
    {
        IncidentReport incidentReport = new IncidentReport.Builder()
                .setAnimal(animal)
                .setIncidentType("Stung by Bee")
                .setDescription("Dog stung by bee")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Medicine administered")
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
    public void findAll()
    {

        Animal animal1 = new Animal.Builder()
                .setAnimalCode(999999999997L)
                .setName("Shrek")
                .setAge(12)
                .setType("Ogre")
                .build();
        animalRepository.save(animal1);

        IncidentReport incidentReport1 = new IncidentReport.Builder()
                .setAnimal(animal1)
                .setIncidentType("Attack")
                .setDescription("Ogre attacked a person")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Medical attention provided")
                .setReportedBy("Jack Sparrow")
                .build();

        service.create(incidentReport1);

        Animal animal2 = new Animal.Builder()
                .setAnimalCode(999999999998L)
                .setName("Puss in boots")
                .setAge(2)
                .setType("Cat")
                .build();
        animalRepository.save(animal2);

        IncidentReport incidentReport2 = new IncidentReport.Builder()
                .setAnimal(animal2)
                .setIncidentType("Injury")
                .setDescription("Cat injured by another animal")
                .setIncidentDate(LocalDateTime.now())
                .setActionsTaken("Veterinary care provided")
                .setReportedBy("Jack Sparrow")
                .build();

        service.create(incidentReport2);

        Set<IncidentReport> incidentReports = new HashSet<>(service.findAll());
        assertEquals(2, incidentReports.size());
    }

    @Test
    void delete()
    {
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