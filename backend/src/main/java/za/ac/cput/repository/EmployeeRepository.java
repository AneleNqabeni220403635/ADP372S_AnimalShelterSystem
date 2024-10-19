package za.ac.cput.repository;

import org.springframework.data.jpa.repository.Query;
import za.ac.cput.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
    @Query("SELECT e.username FROM Employee e")
    List<String> listUsernames();
}
