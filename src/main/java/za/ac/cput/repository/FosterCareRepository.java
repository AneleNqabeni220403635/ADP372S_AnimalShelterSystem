package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.FosterCare;

@Repository
public interface FosterCareRepository extends JpaRepository <FosterCare, String> {
}
