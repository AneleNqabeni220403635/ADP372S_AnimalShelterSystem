package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Donation;
@Repository
public interface DonationRepository extends JpaRepository<Donation, String> {
}
