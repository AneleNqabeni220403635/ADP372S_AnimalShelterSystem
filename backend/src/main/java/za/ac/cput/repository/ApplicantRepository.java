package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long>
{
    Optional<Applicant> findByCatId(Cat catId);
    Optional<Applicant> findByDogId(Dog dogId);
}
