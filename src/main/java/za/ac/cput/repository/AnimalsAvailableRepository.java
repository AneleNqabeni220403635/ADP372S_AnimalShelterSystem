package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsAvailableRepository extends JpaRepository<AnimalsAvailableRepository, Long> {
}
