package za.ac.cput.controller;

import za.ac.cput.domain.Employee;
import za.ac.cput.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping("/read/{id}")
    public Employee readEmployee(@PathVariable Long id) {
        return employeeService.read(id);
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/getall")
    public Set<Employee> getAllEmployees() {
        return employeeService.getall();
    }

    @PostMapping("/login")
    public String login(@RequestBody Employee employee){
        return employeeService.login(employee);
    }

    @PostMapping("/findByUsername/{username}")
    public Employee findByUsername(@PathVariable String username) { return employeeService.findByUsername(username);}
}
