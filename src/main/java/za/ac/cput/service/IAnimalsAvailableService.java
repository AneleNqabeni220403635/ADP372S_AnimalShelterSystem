package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.AnimalsAvailable;

import java.util.Set;
@Service
public interface IAnimalsAvailableService extends IService<AnimalsAvailable, Long > {

    Set<AnimalsAvailable> getAll();
}
