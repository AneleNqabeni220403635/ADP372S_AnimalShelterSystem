package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.UserPrincipal;
import za.ac.cput.repository.EmployeeRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repository.findByUsername(username);

        if (employee == null)
        {
            System.out.println("Username not found: " + username);
            // Do not change this to username not found, we dont want to give potential attackers clues to get into the system.
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return new UserPrincipal(employee);
    }
}
