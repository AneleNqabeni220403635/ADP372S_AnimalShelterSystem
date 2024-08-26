package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.PetOwner;

import static org.junit.jupiter.api.Assertions.*;

public class PetOwnerFactoryTest {

    @BeforeEach
    void setUp() {
        // No setup required for PetOwner tests as no shared state or complex initialization is needed.
    }

    @Test
    void testBuildPetOwnerWithId() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(1L, "Alice", "Johnson", "555-1234", "alice.johnson@example.com", "123 Maple Street");

        assertNotNull(petOwner);
        assertEquals(1L, petOwner.getId());
        assertEquals("Alice", petOwner.getFirstName());
        assertEquals("Johnson", petOwner.getLastName());
        assertEquals("555-1234", petOwner.getContactNo());
        assertEquals("alice.johnson@example.com", petOwner.getEmailAddress());
        assertEquals("123 Maple Street", petOwner.getStreetAddress());
    }

    @Test
    void testBuildPetOwnerWithoutId() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner("Bob", "Smith", "555-5678", "bob.smith@example.com", "456 Oak Avenue");

        assertNotNull(petOwner);
        assertNotNull(petOwner.getId());
        assertEquals("Bob", petOwner.getFirstName());
        assertEquals("Smith", petOwner.getLastName());
        assertEquals("555-5678", petOwner.getContactNo());
        assertEquals("bob.smith@example.com", petOwner.getEmailAddress());
        assertEquals("456 Oak Avenue", petOwner.getStreetAddress());
    }

    @Test
    void testBuildPetOwnerWithInvalidData() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(0L, "", "", "", "", "");

        assertNull(petOwner);
    }

    @Test
    void testBuildPetOwnerWithNullFirstName() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(1L, null, "Brown", "555-7890", "null.first@example.com", "789 Pine Road");

        assertNull(petOwner);
    }

    @Test
    void testBuildPetOwnerWithNullLastName() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(1L, "Charlie", null, "555-7890", "charlie.brown@example.com", "789 Pine Road");

        assertNull(petOwner);
    }

    @Test
    void testBuildPetOwnerWithNullContactNo() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(1L, "Charlie", "Brown", null, "charlie.brown@example.com", "789 Pine Road");

        assertNull(petOwner);
    }

    @Test
    void testBuildPetOwnerWithNullEmailAddress() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(1L, "Charlie", "Brown", "555-7890", null, "789 Pine Road");

        assertNull(petOwner);
    }

    @Test
    void testBuildPetOwnerWithNullStreetAddress() {
        PetOwner petOwner = PetOwnerFactory.buildPetOwner(1L, "Charlie", "Brown", "555-7890", "charlie.brown@example.com", null);

        assertNull(petOwner);
    }
}
