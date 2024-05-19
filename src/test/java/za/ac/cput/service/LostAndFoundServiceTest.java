package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.domain.LostAndFound;
import za.ac.cput.repository.LostAndFoundRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LostAndFoundServiceTest {

    //@Autowired
    private LostAndFoundService service;

    //@Autowired
    private LostAndFoundRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void create() {
        LostAndFound lostAndFound = new LostAndFound.Builder()
                .setSpecies("Dog")
                .setBreed("Labrador")
                .setDescription("Found near the park")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("John Doe")
                .setReporterContactNumber("1234567890")
                .build();

        LostAndFound created = service.create(lostAndFound);
        assertNotNull(created.getId());
        assertEquals("Dog", created.getSpecies());
    }
}