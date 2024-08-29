package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.*;
import za.ac.cput.factory.SaleFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/animalshelter/sale";

    private Sale sale;

    @BeforeEach
    public void setUp() {

        PetOwner petOwner = new PetOwner.Builder()
                .setId(1L)
                .setFirstName("Unathi")
                .setLastName("Mboniswa")
                .setContactNo("0734567890")
                .setEmailAddress("mboniswa@gmail.com")
                .setStreetAddress("125 Sir Lowry")
                .build();

        Dog dog = new Dog.Builder()
                .setDogId(2L)
                .setName("Buddy")
                .setSize("Large")
                .setAge(5)
                .setGender("Male")
                .setBreed("Golden Retriever")
                .setCageNumber(0)
                .build();

        Cat cat = new Cat.Builder()
                .setCatId(3L)
                .setName("Whiskers")
                .setSize("Large")
                .setAge(3)
                .setGender("Female")
                .setBreed("Siamese")
                .setCageNumber(5)
                .build();

        Applicant applicant= new Applicant.Builder()
                .setId(1L)
                .setCatId(cat)
                .setDogId(dog)
                .setPetOwner(petOwner)
                .setStatus("processing")
                .setApplicationDate(LocalDate.now())
                .build();

        Employee employee=new Employee.Builder()
                .setFirstName("Asanda")
                .setLastName("Mbangata")
                .setContactNo("0781456214")
                .setEmailAddress("mbangataa@gmail.com")
                .setId(1L)
                .build();

        sale= SaleFactory.buildSale(1L,applicant,employee,LocalDate.now(),785.2);

    }

    @Test
    @Order(1)
    void testCreateSale() {
        String url = BASE_URL + "/create";
        // Log the dog object being sent
        System.out.println("Sending Sale object: " + sale);
        ResponseEntity<Sale> response = restTemplate.postForEntity(url, sale, Sale.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Change to HttpStatus.OK if needed
        assertNotNull(response.getBody());
        Sale createdSale = response.getBody();
        assertEquals(sale.getSaleDate(), createdSale.getSaleDate());
        System.out.println("Created Sale: " + createdSale);
    }

    @Test
    @Order(2)
    void testReadSale() {
        // Ensure the dog is created before reading
        assertNotNull(sale.getId(), "Sale ID should not be null");
        // Correct URL format
        String url = BASE_URL + "/read/" + sale.getId();
        // Log the URL being accessed
        System.out.println("Request URL: " + url);
        ResponseEntity<Sale> response = restTemplate.getForEntity(url, Sale.class);
        // Log the response status and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200 OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        Sale readSale = response.getBody();
        // Log the details of the read dog
        System.out.println("Read Sale: " + readSale);
    }

    @Test
    @Order(3)
    void testUpdateSale() {
        String url = BASE_URL + "/update";
        Sale updatedSale = new Sale.Builder()
                .copy(sale)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Sale> request = new HttpEntity<>(updatedSale, headers);
        // Using exchange method for PUT request
        ResponseEntity<Sale> response = restTemplate.exchange(url, HttpMethod.PUT, request, Sale.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Sale updated = response.getBody();
        assertEquals(updatedSale.getSaleDate(), updated.getSaleDate());
        System.out.println("Updated Sale: " + updated);
    }


    @Test
    @Order(4)
    void testGetAllSales() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Set> response = restTemplate.exchange(url, HttpMethod.GET, entity, Set.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Show All Sales: " + response.getBody());
    }

    @Test
    @Order(5)
    void testDeleteSale() {
        // DELETE the dog
        String deleteUrl = BASE_URL + "/delete/" + sale.getId();
        restTemplate.delete(deleteUrl);
        // Verify the deletion by attempting to read the dog
        String readUrl = BASE_URL + "/read/" + sale.getId();
        ResponseEntity<Sale> response = restTemplate.getForEntity(readUrl, Sale.class);
        // Log the result
        System.out.println("Response Status Code after deletion attempt: " + response.getStatusCode());
        System.out.println("Response Body after deletion attempt: " + response.getBody());

    }
}
