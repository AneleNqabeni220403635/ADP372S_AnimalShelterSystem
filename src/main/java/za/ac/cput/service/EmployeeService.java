package za.ac.cput.service;

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
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee create(Employee employee) {
        return repository.save(employee);
    }


    public Employee read(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Employee update(Employee employee) {
        return repository.save(employee);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<Employee> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }
}

