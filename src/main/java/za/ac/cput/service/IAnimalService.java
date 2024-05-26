package za.ac.cput.service;

import za.ac.cput.domain.Animal;

import java.util.Set;

public interface IAnimalService extends IService<Animal, Long>{
    Set <Animal> getall();
}
