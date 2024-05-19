package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import za.ac.cput.domain.LostAndFound;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LostAndFoundServiceTest {

    @Autowired
    private LostAndFoundService service;
    private Long createdId = 0L;

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
        LostAndFound savedLostAndFound = service.create(new LostAndFound.Builder()
                .setSpecies("Dog")
                .setBreed("Labrador")
                .setDescription("Found near the park")
                .setReportedDate(LocalDateTime.now())
                .setStatus("Found")
                .setReporterContactName("John Doe")
                .setReporterContactNumber("1234567890")
                .build());

        LostAndFound found = service.read(savedLostAndFound.getId());
        assertNotNull(found);
        assertEquals(savedLostAndFound.getId(), found.getId());
    }

    @Test
    @Order(3)
    void update() {
    }

    @Test
    @Order(4)
    void findAll() {
    }

    @Test
    @Order(5)
    void delete() {
    }
}