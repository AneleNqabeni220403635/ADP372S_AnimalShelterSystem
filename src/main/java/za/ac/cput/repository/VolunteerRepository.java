package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
