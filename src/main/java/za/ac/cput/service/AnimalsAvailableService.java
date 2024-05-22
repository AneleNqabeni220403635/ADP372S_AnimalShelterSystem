package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.repository.AnimalsAvailableRepository;

import java.util.Set;

@Service
public class AnimalsAvailableService implements IAnimalsAvailableService {

    private final AnimalsAvailableRepository animalsAvailableRepository;

    @Autowired
    public AnimalsAvailableService(AnimalsAvailableRepository animalsAvailableRepository) {
        this.animalsAvailableRepository = animalsAvailableRepository;
    }

    @Override
    public AnimalsAvailable create(AnimalsAvailable animalsAvailable) {
        return animalsAvailableRepository.save(animalsAvailable);
    }

    @Override
    public AnimalsAvailable read(Long animalCode) {
        return animalsAvailableRepository.findById(animalCode).orElse(null);
    }

    @Override
    public AnimalsAvailable update(AnimalsAvailable animalsAvailable) {
        return animalsAvailableRepository.save(animalsAvailable);
    }


    @Override
    public Set<AnimalsAvailable> getAll() {
        return Set.of();
    }
}