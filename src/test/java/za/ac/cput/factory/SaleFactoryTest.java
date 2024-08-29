package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Sale;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SaleFactoryTest {

    private Applicant applicant;
    private Employee employee;

    @BeforeEach
    void setUp() {

        applicant = new Applicant.Builder()
                .setId(1L)
                .setPetOwner(null) // Assuming null for simplicity
                .setApplicationDate(LocalDate.now())
                .setDogId(null)
                .setCatId(null)
                .setStatus("Pending")
                .build();

        employee = new Employee.Builder()
                .setId(1L)
                .setFirstName("John")
                .setLastName("Doe")
                .setContactNo("555-9876")
                .setEmailAddress("john.doe@example.com")
                .build();
    }

    @Test
    void testBuildSaleWithId() {
        Sale sale = SaleFactory.buildSale(1L, applicant, employee, LocalDate.now(), 150.0);

        assertNotNull(sale);
        assertEquals(1L, sale.getId());
        assertEquals(applicant, sale.getApplicant());
        assertEquals(employee, sale.getEmployee());
        assertEquals(LocalDate.now(), sale.getSaleDate());
        assertEquals(150.0, sale.getPrice());
    }

    @Test
    void testBuildSaleWithoutId() {
        Sale sale = SaleFactory.buildSale(applicant, employee, LocalDate.now(), 200.0);

        assertNotNull(sale);
        assertNotNull(sale.getId());
        assertEquals(applicant, sale.getApplicant());
        assertEquals(employee, sale.getEmployee());
        assertEquals(LocalDate.now(), sale.getSaleDate());
        assertEquals(200.0, sale.getPrice());
    }

    @Test
    void testBuildSaleWithInvalidData() {
        Sale sale = SaleFactory.buildSale(0L, null, null, null, -100.0);

        assertNull(sale);
    }

    @Test
    void testBuildSaleWithNullApplicant() {
        Sale sale = SaleFactory.buildSale(1L, null, employee, LocalDate.now(), 150.0);

        assertNull(sale);
    }

    @Test
    void testBuildSaleWithNullEmployee() {
        Sale sale = SaleFactory.buildSale(1L, applicant, null, LocalDate.now(), 150.0);

        assertNull(sale);
    }

    @Test
    void testBuildSaleWithNullSaleDate() {
        Sale sale = SaleFactory.buildSale(1L, applicant, employee, null, 150.0);

        assertNull(sale);
    }

    @Test
    void testBuildSaleWithNegativePrice() {
        Sale sale = SaleFactory.buildSale(1L, applicant, employee, LocalDate.now(), -50.0);

        assertNull(sale);
    }
}
