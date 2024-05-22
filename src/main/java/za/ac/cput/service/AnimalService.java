package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Animal;
import za.ac.cput.repository.AnimalRepository;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnimalService implements IAnimalService {
    private AnimalRepository repository;

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

        return repository.save(animal);
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<Animal> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

}
