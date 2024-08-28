package za.ac.cput.service.Impl;

import za.ac.cput.domain.AnimalsAvailable;

import java.util.Set;
//@Service
public interface IVolunteerService /*extends IService<AnimalsAvailable, Long >*/ {
    void delete(Long animalCode);

    AnimalsAvailable create(AnimalsAvailable animalsAvailable);

    AnimalsAvailable read(Long animalCode);

    AnimalsAvailable update(AnimalsAvailable animalsAvailable);

    Set<AnimalsAvailable> getAll();

    //Set<AnimalsAvailable> getAll();
}
