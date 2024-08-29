package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Applicant;
import za.ac.cput.service.ApplicantService;

import java.util.Set;

@RestController
@RequestMapping("/applicant")
public class ApplicantController
{
    @Autowired
    private ApplicantService applicantService;

    @PostMapping("/create")
    public Applicant create(@RequestBody Applicant applicant)
    {
        return applicantService.create(applicant);
    }

    @GetMapping("/read/{applicantId}")
    public Applicant read(@PathVariable Long applicantId)
    {
        return applicantService.read(applicantId);
    }

    @PostMapping("/update")
    public Applicant update (@RequestBody Applicant applicant)
    {
        return applicantService.update(applicant);
    }

    @PostMapping("/delete/{applicantId}")
    public void delete(@PathVariable Long applicantId)
    {
        applicantService.delete(applicantId);
    }

    @GetMapping("/getAll")
    public Set<Applicant> getAll()
    {
        return applicantService.getall();
    }
}
