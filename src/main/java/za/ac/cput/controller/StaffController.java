package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Staff;
import za.ac.cput.service.StaffService;

import java.util.Set;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    public Staff create(@RequestBody Staff staff) {
        return staffService.create(staff);
    }

    @PostMapping("/read/{staffId}")
    public Staff read(@PathVariable Long staffId) {
        return staffService.read(staffId);
    }

    @PostMapping("/update")
    public Staff update(@RequestBody Staff staff) {
        return staffService.update(staff);
    }

    @PostMapping("/delete/{staffId}")
    public void delete(@PathVariable Long staffId) {
        staffService.delete(staffId);
    }

    @GetMapping("/getall")
    public Set<Staff> getall() {
        return staffService.getall();
    }
}
