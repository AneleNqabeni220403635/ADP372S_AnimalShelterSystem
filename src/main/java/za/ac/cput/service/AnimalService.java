package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Animal;
import za.ac.cput.repository.AnimalRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AnimalService implements IAnimalService {
    private final AnimalRepository repository;

    @Autowired
    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }
    @Override
    public Animal create(Animal animal){
        return repository.save(animal);
    }
    @Override
    public Animal read(Long id){
        return repository.findById(id).orElse(null);
    }
@Override
    public Animal update(Animal animal){
    if (repository.existsById(animal.getAnimalCode())) {
        return repository.save(animal);
    }
        return null;
}
@Override
    public Set<Animal>findAll(){
    return new HashSet<>(repository.findAll());
}
@Override
    public boolean delete(Long id){
        if(this.repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
    return false;
}
    @Override
    public Set<Animal> getall() {
        return new HashSet<>(repository.findAll());
    }

}
