package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Dog;
import za.ac.cput.repository.DogRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DogService implements IDogService {
    private DogRepository repository;

    @Autowired
    public DogService(DogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dog create(Dog dog) {
        return repository.save(dog);
    }

    @Override
    public Dog read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Dog update(Dog dog) {
        return repository.save(dog);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<Dog> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }
}