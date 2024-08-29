package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cat;
import za.ac.cput.repository.CatRepository;
import za.ac.cput.service.Impl.ICatService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CatService implements ICatService {

    private final CatRepository repository;

    @Autowired
    public CatService(CatRepository repository) {
        this.repository = repository;
    }


    public Cat create(Cat cat) {
        return repository.save(cat);
    }


    public Cat read(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Cat update(Cat cat) {
        return repository.save(cat);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<Cat> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

}

