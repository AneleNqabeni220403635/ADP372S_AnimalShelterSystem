package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Adoption;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
