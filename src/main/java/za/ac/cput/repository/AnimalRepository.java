package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Animal;

import java.util.Set;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Set<Animal> findAllAnimalsByNameAndAge(String name, int age);
}
