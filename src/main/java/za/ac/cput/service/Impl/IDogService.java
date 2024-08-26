package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Dog;

import java.util.Set;

@Service
public interface IDogService extends IService<Dog, Long>{
    Set<Dog> getall();
}