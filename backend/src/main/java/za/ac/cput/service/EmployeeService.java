package za.ac.cput.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import za.ac.cput.domain.Employee;
import za.ac.cput.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.service.Impl.IEmployeeService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee create(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return repository.save(employee);
    }


    public Employee read(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Employee update(Employee employee) {
        Employee e = read(employee.getId());
        // don't set password to blank, ui passes blank if its not changed
        if (employee.getPassword() != null && !employee.getPassword().isEmpty())
        {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        else
        {
            employee.setPassword(e.getPassword());
        }
        if (employee.getUsername() == null || employee.getUsername().isEmpty())
            employee.setUsername(e.getUsername());
        if (employee.getRole() == null || employee.getRole().isEmpty())
            employee.setRole(e.getRole());
        return repository.save(employee);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<Employee> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }


    public String login(Employee employee) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(employee.getUsername());
        }
        return "failure";
    }


    public Employee findByUsername(String username)
    {
        return repository.findByUsername(username);
    }
}

