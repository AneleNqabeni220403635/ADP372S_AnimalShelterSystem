package za.ac.cput.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.PetOwner;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
}