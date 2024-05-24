package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Volunteer;
import za.ac.cput.service.VolunteerService;

import java.util.Set;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/create")
    public Volunteer create(@RequestBody Volunteer volunteer) {
        return volunteerService.create(volunteer);
    }

    @PostMapping("/read/{volunteerId}")
    public Volunteer read(@PathVariable Long volunteerId) {
        return volunteerService.read(volunteerId);
    }

    @PostMapping("/update")
    public Volunteer update(@RequestBody Volunteer volunteer) {
        return volunteerService.update(volunteer);
    }

    @PostMapping("/delete/{volunteerId}")
    public void delete(@PathVariable Long volunteerId) {
        volunteerService.delete(volunteerId);
    }

    @GetMapping("/getall")
    public Set<Volunteer> getAll() {
        return volunteerService.getall();
    }
}

