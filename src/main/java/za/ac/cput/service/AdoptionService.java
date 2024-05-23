package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Adoption;
import za.ac.cput.repository.AdoptionRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdoptionService implements IAdoptionService {

    private final AdoptionRepository adoptionRepository;

    @Autowired
    public AdoptionService(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public Adoption create(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    @Override
    public Adoption read(Long id) {
        return adoptionRepository.findById(id).orElse(null);
    }

    @Override
    public Adoption update(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    @Override
    public Set<Adoption> getAll() {
        return adoptionRepository.findAll().stream().collect(Collectors.toSet());
    }
}