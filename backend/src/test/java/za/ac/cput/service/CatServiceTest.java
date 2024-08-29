package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cat;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CatServiceTest {

    @Autowired
    private CatService catService;

    private static Cat whiskers;

    @BeforeEach
    public void setUp() {
        whiskers = new Cat.Builder()
                .setCatId(1L)
                .setName("Whiskers")
                .setAge(3)
                .setBreed("Siamese")
                .setSize("Small")
                .setGender("Male")
                .setCageNumber(201)
                .build();

        // Create the cat entity before running the tests
        catService.create(whiskers);
    }

    @Test
    @Order(1)
    void testCreate() {
        Cat newCat = new Cat.Builder()
                .setCatId(1L)
                .setName("Luna New")
                .setAge(2)
                .setBreed("Persian")
                .setSize("Medium")
                .setGender("Female")
                .setCageNumber(202)
                .build();

        Cat createdCat = catService.create(newCat);
        assertNotNull(createdCat);
        assertEquals(newCat.getCatId(), createdCat.getCatId());
        System.out.println("Created: " + createdCat);
    }

    @Test
    @Order(2)
    void testRead() {
        Cat readCat = catService.read(whiskers.getCatId());
        assertNotNull(readCat);
        assertEquals(whiskers.getCatId(), readCat.getCatId());
        System.out.println("Read: " + readCat);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Cat updatedCat = new Cat.Builder()
                .copy(whiskers)
                .setSize("Large")
                .build();

        Cat updated = catService.update(updatedCat);
        assertNotNull(updated);
        assertEquals("Large", updated.getSize());
        System.out.println("Updated: " + updated);
    }


    @Test
    @Order(4)
    void testGetAll() {
        Set<Cat> cats = catService.getall();
        System.out.println("All Cats: " + cats);
    }

    @Test
    @Order(5)
    void testDelete() {
        catService.delete(whiskers.getCatId());
        Cat deleted = catService.read(whiskers.getCatId());
        assertNull(deleted);
        System.out.println("Deleted");
    }


}
