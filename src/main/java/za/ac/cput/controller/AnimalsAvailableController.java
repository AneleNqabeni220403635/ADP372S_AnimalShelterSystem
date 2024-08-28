
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.service.VolunteerService;

import java.util.Set;

@RestController
@RequestMapping("/adoption")
public class AnimalsAvailableController {

    @Autowired
    private VolunteerService animalsAvailableService;

    @PostMapping("/create")
    public AnimalsAvailable create(@RequestBody AnimalsAvailable animalsAvailable) {

        return animalsAvailable.create(animalsAvailable);
    }

    @PostMapping("/read/{animalCode}")
    public AnimalsAvailable read(@PathVariable Long animalCode){

        return animalsAvailableService.read(animalCode);
    }

    @PostMapping("/update")
    public AnimalsAvailable update(@RequestBody AnimalsAvailable animalsAvailable){

        return animalsAvailableService.update(animalsAvailable);
    }

    @PostMapping("/delete/{animalCode}")
    public void delete(@PathVariable Long animalCode){
        animalsAvailableService.delete(animalCode);
    }

    @GetMapping("/getall")
    public Set<AnimalsAvailable> getall(){

        return animalsAvailableService.getAll();
    }

}
