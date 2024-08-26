package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.MedicalRecord;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CatFactoryTest {

    private static Set<MedicalRecord> medicalRecords;
    private static Cat cat;

    @BeforeEach
    public void setUp() {
        cat = CatFactory.buildCat("Whiskers", "Small", 3, "Male", "Siamese", 12);
    }

    @Test
    void testBuildCatWithId() {
        long catId = 123L;
        Cat catWithId = CatFactory.buildCat(catId, "Fluffy", "Large", 4, "Female", "Persian", 9);
        assertNotNull(catWithId);
        assertEquals(catId, catWithId.getCatId());
        assertEquals("Fluffy", catWithId.getName());
        assertEquals("Large", catWithId.getSize());
        assertEquals(4, catWithId.getAge());
        assertEquals("Female", catWithId.getGender());
        assertEquals("Persian", catWithId.getBreed());
        assertEquals(9, catWithId.getCageNumber());
    }

    @Test
    void testBuildCatWithoutId() {
        Cat catWithoutId = CatFactory.buildCat("Bella", "Medium", 2, "Female", "Maine Coon", 8);
        assertNotNull(catWithoutId);
        assertTrue(catWithoutId.getCatId() > 0); // Check if ID is generated
        assertEquals("Bella", catWithoutId.getName());
        assertEquals("Medium", catWithoutId.getSize());
        assertEquals(2, catWithoutId.getAge());
        assertEquals("Female", catWithoutId.getGender());
        assertEquals("Maine Coon", catWithoutId.getBreed());
        assertEquals(8, catWithoutId.getCageNumber());
    }

    @Test
    void testBuildCatWithInvalidData() {
        Cat invalidCat = CatFactory.buildCat("", "Small", -1, "", "", 0);
        assertNull(invalidCat);
    }
}
