package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Dog;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DogServiceTest {

    @Autowired
    private DogService dogService;

    private static Dog roxy = new Dog.Builder()
            .setDogId(1L)
            .setAge(8)
            .setBreed("Pitbull")
            .setCageNumber(107)
            .setGender("Female")
            .setName("Roxy")
            .setSize("Medium")
            .build();

    @Test
    @Order(1)
    void testCreate() {
        Dog createdDog = dogService.create(roxy);
        assertNotNull(createdDog);
        assertEquals(roxy.getDogId(), createdDog.getDogId());
        System.out.println("Created: " + createdDog);
    }

    @Test
    @Order(2)
    void testRead() {
        Dog readDog = dogService.read(roxy.getDogId());
        assertNotNull(readDog);
        assertEquals(roxy.getDogId(), readDog.getDogId());
        System.out.println("Read: " + readDog);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Dog updatedDog = new Dog.Builder()
                .copy(roxy)
                .setSize("Medium")
                .build();
        Dog updated = dogService.update(updatedDog);
        assertNotNull(updated);
        assertEquals("Medium", updated.getSize());
        System.out.println("Updated: " + updated);
    }


    @Test
    @Order(4)
    void testDelete() {
        dogService.delete(roxy.getDogId());
        Dog deleted = dogService.read(roxy.getDogId());
        assertNull(deleted);
        System.out.println("Deleted");
    }

    @Test
    @Order(5)
    void testGetAll() {
        Set<Dog> dogs = dogService.getall();
        System.out.println("All Dogs: " + dogs);
    }
}
