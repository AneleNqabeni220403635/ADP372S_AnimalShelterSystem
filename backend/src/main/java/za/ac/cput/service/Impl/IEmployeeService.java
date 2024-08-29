package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Employee;

import java.util.Set;
@Service
public interface IEmployeeService extends IService<Employee,Long> {
    Set<Employee> getall();
}
