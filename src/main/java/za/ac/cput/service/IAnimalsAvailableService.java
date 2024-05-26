package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.AnimalsAvailable;

import java.util.Set;
//@Service
public interface IAnimalsAvailableService /*extends IService<AnimalsAvailable, Long >*/ {
    void delete(Long animalCode);

    AnimalsAvailable create(AnimalsAvailable animalsAvailable);

    AnimalsAvailable read(Long animalCode);

    AnimalsAvailable update(AnimalsAvailable animalsAvailable);

    Set<AnimalsAvailable> getAll();

    //Set<AnimalsAvailable> getAll();
}
