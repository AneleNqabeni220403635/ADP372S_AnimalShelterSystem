package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Animal;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    private static Animal snow = new Animal.Builder()
            .setAnimalCode(10L)
            .setName("snow")
            .setAge(7)
            .setType("Wolf")
            .build();

    @Test
    @Order(1)
    void testCreate() {
        Animal createdAnimal = animalService.create(snow);
        assertNotNull(createdAnimal);

        assertEquals(snow.getAnimalCode(), createdAnimal.getAnimalCode());
        System.out.println("Created: " + createdAnimal);
    }

    @Test
    @Order(2)
    void testRead() {
        Animal readAnimal = animalService.read(snow.getAnimalCode());
        assertNotNull(readAnimal);
        assertEquals(snow.getAnimalCode(), readAnimal.getAnimalCode());
        System.out.println("Read: " + readAnimal);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Animal updatedAnimal = new Animal.Builder()
                .copy(snow)
                .setAge(9)
                .build();
        Animal updated = animalService.update(updatedAnimal);
        assertNotNull(updated);
        assertEquals(9, updated.getAge());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void testDelete() {
        animalService.delete(snow.getAnimalCode());
        Animal deleted = animalService.read(snow.getAnimalCode());
        assertNull(deleted);
        System.out.println("Deleted");
    }

    @Test
    @Order(5)
    void testGetAll() {
        Set<Animal> animals = animalService.getall();
        assertNotNull(animals);
        assertFalse(animals.isEmpty());
        System.out.println("All Animals: " + animals);
    }
}
