package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalFactory;
import za.ac.cput.factory.IncidentReportFactory;
import za.ac.cput.factory.MedicalRecordFactory;
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

        // Get back to this after exams, animal and medical records needs some work on the factory
        MedicalRecord medicalRecord = MedicalRecordFactory.buildDefaultMedicalRecord(999999999999L);
        animal = AnimalFactory.buildAnimal(999999999999L, "Salem", 2, "Cat", medicalRecord);
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
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(
                animal,
                "Attack",
                LocalDateTime.now(),
                "Cat attacked a person",
                "Medical attention provided",
                "Jack Sparrow"
        );

        IncidentReport created = service.create(incidentReport);
        assertNotNull(created.getId());
        assertEquals("Attack", created.getIncidentType());
    }

    @Test
    void read()
    {
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(
                animal,
                "Injury",
                LocalDateTime.now(),
                "Cat injured by another animal",
                "Veterinary care provided",
                "Jack Sparrow"
        );

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
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(
                animal,
                "Stung by Bee",
                LocalDateTime.now(),
                "Dog stung by bee",
                "Medicine administered",
                "Jack Sparrow"
        );

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
        // Given
        MedicalRecord medicalRecord1 = MedicalRecordFactory.buildDefaultMedicalRecord(999999999997L);
        Animal animal1 = AnimalFactory.buildAnimal(999999999997L, "Shrek", 12, "Ogre", medicalRecord1);
        animalRepository.save(animal1);

        IncidentReport incidentReport1 = IncidentReportFactory.createIncidentReport(
                animal1,
                "Attack",
                LocalDateTime.now(),
                "Ogre attacked a person",
                "Medical attention provided",
                "Jack Sparrow"
        );
        service.create(incidentReport1);

        MedicalRecord medicalRecord2 = MedicalRecordFactory.buildDefaultMedicalRecord(999999999997L);
        Animal animal2 = AnimalFactory.buildAnimal(999999999998L, "Puss in boots", 2, "Cat", medicalRecord2);
        animalRepository.save(animal2);

        IncidentReport incidentReport2 = IncidentReportFactory.createIncidentReport(
                animal2,
                "Injury",
                LocalDateTime.now(),
                "Cat injured by another animal",
                "Veterinary care provided",
                "Jack Sparrow"
        );

        // When
        service.create(incidentReport2);

        // Then
        Set<IncidentReport> incidentReports = new HashSet<>(service.getAll());
        assertEquals(2, incidentReports.size());
    }

    @Test
    void delete()
    {
        // Given
        IncidentReport incidentReport = IncidentReportFactory.createIncidentReport(
                animal,
                "Stung by Bee",
                LocalDateTime.now(),
                "Dog stung by bee",
                "Medicine administered",
                "Alice"
        );
        IncidentReport created = service.create(incidentReport);

        // When
        service.delete(created.getId());

        // Then
        IncidentReport deletedReport = service.read(created.getId());
        assertNull(deletedReport);
    }
}