package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Dog;
import za.ac.cput.service.DogService;

import java.util.Set;

@RestController
@RequestMapping("/dog")
public class DogController {

    @Autowired
    private DogService dogService;

    @PostMapping("/create")
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.create(dog);
    }

    @GetMapping("/read/{id}")
    public Dog readDog(@PathVariable Long id) {
        return dogService.read(id);
    }

    @PutMapping("/update")
    public Dog updateDog(@RequestBody Dog dog) {
        return dogService.update(dog);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogService.delete(id);
    }

    @GetMapping("/getall")
    public Set<Dog> getAllDogs() {
        return dogService.getall();
    }

}
