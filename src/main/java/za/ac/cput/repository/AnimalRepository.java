package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
Animal findAllAnimalsByName(String name);
}
