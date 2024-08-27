package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.service.PetOwnerService;

import java.util.Set;

@RestController
@RequestMapping("/petOwner")
public class PetOwnerController {

    @Autowired
    private PetOwnerService petOwnerService;

    @PostMapping("/create")
    public PetOwner createPetOwner(@RequestBody PetOwner petOwner) {
        return petOwnerService.create(petOwner);
    }

    @GetMapping("/read/{id}")
    public PetOwner readPetOwner(@PathVariable Long id) {
        return petOwnerService.read(id);
    }

    @PutMapping("/update")
    public PetOwner updatePetOwner(@RequestBody PetOwner petOwner) {
        return petOwnerService.update(petOwner);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePetOwner(@PathVariable Long id) {
        petOwnerService.delete(id);
    }

    @GetMapping("/getall")
    public Set<PetOwner> getAllPetOwners() {
        return petOwnerService.getall();
    }
}
