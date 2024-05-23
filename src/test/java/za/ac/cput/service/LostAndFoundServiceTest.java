package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import za.ac.cput.domain.LostAndFound;
import za.ac.cput.repository.LostAndFoundRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LostAndFoundServiceTest {

   @Autowired
    private LostAndFoundService service;

    @Autowired
    private LostAndFoundRepository repository;
    private Long createdId = 0L;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @Order(1)
    public void create()
    {
        LostAndFound lostAndFound = new LostAndFound.Builder()
                .setSpecies("Cat")
                .setBreed("Calico")
                .setDescription("Found near the park")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("Jack Sparrow")
                .setReporterContactNumber("0721234567")
                .build();

        LostAndFound created = service.create(lostAndFound);
        assertNotNull(created.getId());
        createdId = created.getId();
        assertEquals("Cat", created.getSpecies());
    }

    @Test
    @Order(2)
    void read()
    {
        LostAndFound lostAndFound = new LostAndFound.Builder()
                .setSpecies("Cat")
                .setBreed("Calico")
                .setDescription("Found near the park")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("Jack Sparrow")
                .setReporterContactNumber("0721234567")
                .build();

        LostAndFound found = service.read(lostAndFound.getId());
        assertNotNull(found);
        assertEquals(lostAndFound.getId(), found.getId());
    }

    @Test
    @Order(3)
    void update() {
        LostAndFound lostAndFound = new LostAndFound.Builder()
                .setSpecies("Cat")
                .setBreed("Calico")
                .setDescription("Found near the park")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("Jack Sparrow")
                .setReporterContactNumber("0721234567")
                .build();

        LostAndFound created = service.create(lostAndFound);
        created = new LostAndFound.Builder()
                .copy(created)
                .setDescription("Updated description")
                .build();

        LostAndFound updated = service.update(created);
        assertEquals("Updated description", updated.getDescription());
    }

    @Test
    @Order(4)
    void findAll() {
        LostAndFound lostAndFound1 = new LostAndFound.Builder()
                .setSpecies("Cat")
                .setBreed("Siamese")
                .setDescription("Lost near the library")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Lost")
                .setReporterContactName("Alice")
                .setReporterContactNumber("0721234566")
                .build();

        LostAndFound lostAndFound2 = new LostAndFound.Builder()
                .setSpecies("Dog")
                .setBreed("Rottweiler")
                .setDescription("Found near the supermarket")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("Bob")
                .setReporterContactNumber("0721234568")
                .build();

        service.create(lostAndFound1);
        service.create(lostAndFound2);

        Set<LostAndFound> lostAndFoundList = new HashSet<>(service.findAll());
        assertEquals(2, lostAndFoundList.size());
    }

    @Test
    @Order(5)
    void delete() {
        LostAndFound lostAndFound = new LostAndFound.Builder()
                .setSpecies("Cat")
                .setBreed("Calico")
                .setDescription("Found near the park")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("Jack Sparrow")
                .setReporterContactNumber("0721234567")
                .build();

        LostAndFound created = service.create(lostAndFound);
        boolean deleted = service.delete(created.getId());
        assertTrue(deleted);

        LostAndFound read = service.read(created.getId());
        assertNull(read);
    }
}