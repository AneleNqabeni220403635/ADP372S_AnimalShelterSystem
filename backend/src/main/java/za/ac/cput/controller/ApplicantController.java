package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.service.ApplicantService;

import java.util.List;
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

    @PutMapping("/update")
    public Applicant update (@RequestBody Applicant applicant)
    {
        return applicantService.update(applicant);
    }

    @DeleteMapping("/delete/{applicantId}")
    public void delete(@PathVariable Long applicantId)
    {
        applicantService.delete(applicantId);
    }

    @GetMapping("/getAll")
    public Set<Applicant> getAll()
    {
        return applicantService.getall();
    }

    @PostMapping("/readCatId/{catId}")
    public Applicant readCatId(@PathVariable Long catId)
    {
        Cat cat = new Cat.Builder().setCatId(catId).build();
        return applicantService.readCatId(cat);
    }

    @PostMapping("/readDogId/{dogId}")
    public Applicant readDogId(@PathVariable Long dogId)
    {
        Dog dog = new Dog.Builder().setDogId(dogId).build();
        return applicantService.readDogId(dog);
    }

    @GetMapping("/readStatus/{status}")
    public List<Applicant> readDogId(@PathVariable String status)
    {
        return applicantService.readStatus(status);
    }
}
