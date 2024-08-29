package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.OwnerRecord;
import za.ac.cput.domain.PetOwner;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerRecordFactoryTest {

    private Dog dog;
    private Cat cat;
    private PetOwner petOwner;

    @BeforeEach
    void setUp() {
        dog = new Dog.Builder().setDogId(1L).build();
        cat = new Cat.Builder().setCatId(2L).build();
        petOwner = new PetOwner.Builder().setId(3L).build();
    }

    @Test
    void testBuildOwnerRecordWithId() {
        OwnerRecord record = OwnerRecordFactory.buildOwnerRecord(1L, dog, cat, petOwner, LocalDate.now(), LocalDate.now().plusMonths(1));

        assertNotNull(record);
        assertEquals(1L, record.getId());
        assertEquals(dog, record.getDog());
        assertEquals(cat, record.getCat());
        assertEquals(petOwner, record.getPetOwner());
    }

    @Test
    void testBuildOwnerRecordWithoutId() {
        OwnerRecord record = OwnerRecordFactory.buildOwnerRecord(dog, cat, petOwner, LocalDate.now(), LocalDate.now().plusMonths(1));

        assertNotNull(record);
        assertNotNull(record.getId());
    }

    @Test
    void testBuildOwnerRecordWithInvalidData() {
        OwnerRecord record = OwnerRecordFactory.buildOwnerRecord(null, null, null, null, null);

        assertNull(record);
    }
}
