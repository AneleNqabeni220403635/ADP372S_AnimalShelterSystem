package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.UserCredential;

import java.util.List;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

    UserCredential findByUsername(String username);
    @Query("SELECT u.username FROM UserCredential u")
    List<String> listUsernames();
}
