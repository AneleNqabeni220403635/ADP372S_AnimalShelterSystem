package za.ac.cput.service.Impl;

import za.ac.cput.domain.Dog;

import java.util.Set;

public interface IDogService extends IService<Dog, Long>{
    Set<Dog> getall();
}