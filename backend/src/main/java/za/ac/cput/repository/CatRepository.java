package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
