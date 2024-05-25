package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Animal;
import za.ac.cput.service.AnimalService;
import java.util.Set;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/create")
    public Animal create(@RequestBody Animal animal) {

        return animalService.create(animal);
    }

    @PostMapping("/read/{animalCode}")
    public Animal read(@PathVariable Long animalCode){

        return animalService.read(animalCode);
    }

    @PostMapping("/update")
    public Animal update(@RequestBody Animal animal){

        return animalService.update(animal);
    }

    @PostMapping("/delete/{animalCode}")
    public void delete(@PathVariable Long animalCode){
        animalService.delete(animalCode);
    }

    @GetMapping("/getall")
    public Set<Animal> getall(){

        return animalService.getall();
    }
    @GetMapping("/{animalName}/{age}")
    public Set<Animal> findByAllAnimalsByNameAndAge(@PathVariable String animalName,@PathVariable int age){
        return animalService.findAllAnimalsByNameAndAge(animalName, age);
    }

}
