package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.UserCredential;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

    UserCredential findByUsername(String username);
}
