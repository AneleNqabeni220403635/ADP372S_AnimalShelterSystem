package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}