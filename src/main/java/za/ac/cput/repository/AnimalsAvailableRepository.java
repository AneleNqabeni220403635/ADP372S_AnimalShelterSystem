package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.AnimalsAvailable;
@Service
public interface AnimalsAvailableRepository extends JpaRepository<AnimalsAvailable,Long> {
}
