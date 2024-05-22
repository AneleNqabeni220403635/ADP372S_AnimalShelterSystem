package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.Animal;
import za.ac.cput.factory.AdoptionFactory;
import za.ac.cput.factory.AnimalFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdoptionServiceTest {
    @Autowired
    private AdoptionService adoptionService;


    private static Adoption adoption1; 
    private static Adoption adoption2;
    private static Animal animal1;
    private static Animal animal2;

    @BeforeEach
    void setUp() {
        adoption1 = AdoptionFactory.createAdoption(1L, "Kitty Kat", LocalDate.now().minusMonths(2), "Approved", animal1);
        adoption2 = AdoptionFactory.createAdoption(2L, "Luhle Booi", LocalDate.now().minusMonths(1), "Pending", animal2);
    }

    @Test
    void a_create() {
        Adoption created = adoptionService.create(adoption1);
        assertNotNull(created);
        assertEquals(adoption1.getApplicantName(), created.getApplicantName());
    }

    @Test
    void b_read() {
        Adoption created = adoptionService.create(adoption2);
        Adoption read = adoptionService.read(created.getAdoptionId());
        assertNotNull(read);
        assertEquals(created.getApplicantName(), read.getApplicantName());
    }

    @Test
    void c_update() {
        Adoption created = adoptionService.create(adoption1);
        created = new Adoption.Builder()
                .copy(created)
                .setStatus("Denied")
                .build();
        Adoption updated = adoptionService.update(created);
        assertEquals("Denied", updated.getStatus());
    }

    @Test
    void d_getall() {
        adoptionService.create(adoption1);
        adoptionService.create(adoption2);
        Set<Adoption> adoptions = adoptionService.getAll();
        assertTrue(adoptions.size() >= 2);
    }
}