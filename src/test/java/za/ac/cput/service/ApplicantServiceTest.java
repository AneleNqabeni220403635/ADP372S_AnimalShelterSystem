package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.factory.ApplicantFactory;
import za.ac.cput.factory.CatFactory;
import za.ac.cput.factory.DogFactory;
import za.ac.cput.factory.PetOwnerFactory;
import za.ac.cput.repository.ApplicantRepository;
import za.ac.cput.repository.CatRepository;
import za.ac.cput.repository.DogRepository;
import za.ac.cput.repository.PetOwnerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ApplicantServiceTest {

    @Autowired
    private ApplicantService service;

    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private CatRepository catRepository;

    private PetOwner petOwner;
    private Dog dog;
    private Cat cat;

    @BeforeEach
    void setUp()
    {
        applicantRepository.deleteAll();
        petOwnerRepository.deleteAll();
        dogRepository.deleteAll();
        catRepository.deleteAll();

        dog = DogFactory.buildDog(79L,"Wishbone", "Small", 7, "Male", "Rottweiler", 104);
        dogRepository.save(dog);

        cat = CatFactory.buildCat(99L,"Garfield", "Large",5, "Male", "Maincoon", 55 );
        catRepository.save(cat);

        petOwner = PetOwnerFactory.buildPetOwner("Jack","Parrow", "0721234567", "parrowj@cput.ac.za", "80 Constitution Street, Cape Town, 7925");
        petOwnerRepository.save(petOwner);
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    void create()
    {
        Applicant applicant = ApplicantFactory.createApplicant(LocalDate.now(), "Application_Submitted", petOwner, dog, cat);

        Applicant created = service.create(applicant);
        assertNotNull(created.getId());
        assertEquals(applicant.getApplicationStatus(), created.getApplicationStatus());
    }

    @Test
    void read()
    {
        Applicant applicant = ApplicantFactory.createApplicant(LocalDate.now(), "Application_Submitted", petOwner, null, null);
        Applicant created = service.create(applicant);

        Applicant read = service.read(created.getId());

        assertEquals(created.getId(), read.getId());
        assertEquals(created.getApplicationDate(), read.getApplicationDate());
        assertEquals(created.getApplicationStatus(), read.getApplicationStatus());
    }

    @Test
    public void update()
    {
        Applicant applicant = ApplicantFactory.createApplicant(LocalDate.now(), "Application_Submitted", petOwner, null, null);
        Applicant created = service.create(applicant);

        created = new Applicant.Builder()
                .copy(created)
                .setApplicationStatus("Application Review")
                .build();

        Applicant updated = service.update(created);
        assertEquals("Application Review", updated.getApplicationStatus());
    }

    @Test
    public void findAll()
    {
        Applicant applicant1 = ApplicantFactory.createApplicant(LocalDate.now(), "Application_Submitted", petOwner, null, null);
        service.create(applicant1);

        Applicant applicant2 = ApplicantFactory.createApplicant(LocalDate.now(), "Adoption_Pending", petOwner, null, null);
        service.create(applicant2);

        Set<Applicant> applicants = new HashSet<>(service.getAll());
        assertEquals(2, applicants.size());
    }

    @Test
    void delete()
    {
        Applicant applicant = ApplicantFactory.createApplicant(LocalDate.now(), "Application_Submitted", petOwner, null, null);
        service.create(applicant);

        // When
        service.delete(applicant.getId());

        // Then
        Applicant deletedApplicant = service.read(applicant.getId());
        assertNull(deletedApplicant);
    }
}