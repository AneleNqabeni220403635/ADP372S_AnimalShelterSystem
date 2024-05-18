package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Adoption;

@Service
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
