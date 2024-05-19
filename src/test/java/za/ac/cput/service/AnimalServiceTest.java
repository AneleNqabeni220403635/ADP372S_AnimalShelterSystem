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
@TestMethodOrder(MethodOrderer.MethodName.class)
class AnimalServiceTest {

   @Autowired
    private AnimalService service;

    private static Animal animal = new Animal.Builder()
            .setAnimalCode(12345L)
            .setName("Roxy")
            .setAge(5)
            .setType("Cat")
            .build();

    @Test
    @Order(1)
    void create() {
        Animal created = service.create(animal);
        assertNotNull(created);
        assertEquals(animal.getAnimalCode(), created.getAnimalCode());
        System.out.println("Created: " + created);
    }

    @Test
    @Order(2)
    void read() {
        Animal read = service.read(animal.getAnimalCode());
        assertNotNull(read);
        assertEquals(animal.getAnimalCode(), read.getAnimalCode());
        System.out.println("Read: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Animal updatedAnimal = new Animal.Builder().copy(animal)
                .setName("Max")
                .build();
        Animal updated = service.update(updatedAnimal);
        assertNotNull(updated);
        assertEquals(updatedAnimal.getName(), updated.getName());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void delete() {
        boolean success = service.delete(animal.getAnimalCode());
        assertTrue(success);
        Animal deleted = service.read(animal.getAnimalCode());
        assertNull(deleted);
        System.out.println("Deleted: " + deleted);
    }

    @Test
    @Order(5)
    void findAll() {
        Set<Animal> animals = service.findAll();
        assertNotNull(animals);
        assertFalse(animals.isEmpty());
        System.out.println("All Animals: " + animals);
    }
}


