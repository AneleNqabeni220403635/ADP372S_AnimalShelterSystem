package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.service.AdoptionService;

import java.util.Set;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @PostMapping("/create")
    public Adoption create(@RequestBody Adoption adoption) {

        return adoptionService.create(adoption);
    }

    @PostMapping("/read/{id}")
    public Adoption read(@PathVariable Long adoptionId){

        return adoptionService.read(adoptionId);
    }

    @PostMapping("/update")
    public Adoption update(@RequestBody Adoption adoption){

        return adoptionService.update(adoption);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long adoptionId){
        adoptionService.delete(adoptionId);
    }

    @GetMapping("/getall")
    public Set<Adoption> getall(){

        return adoptionService.getAll();
    }

}
