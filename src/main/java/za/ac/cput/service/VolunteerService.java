package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.repository.VolunteerRepository;

import java.util.Set;

@Service
public abstract class VolunteerService implements IAnimalsAvailableService {

    private final VolunteerRepository animalsAvailableRepository;

    @Autowired
    public VolunteerService(VolunteerRepository animalsAvailableRepository) {
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
    public void delete(Long animalCode) {
        animalsAvailableRepository.deleteById(animalCode);
    }

    @Override
    public Set<AnimalsAvailable> getAll() {
        return Set.of();
    }
}