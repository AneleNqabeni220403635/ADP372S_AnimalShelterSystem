package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.FosterCare;
import za.ac.cput.repository.FosterCareRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class FosterCareService implements IFosterCareService{
    private FosterCareRepository repository;

    @Autowired
    public FosterCareService(FosterCareRepository repository) {
        this.repository = repository;
    }

    @Override
    public FosterCare create(FosterCare fosterCare) {
        return repository.save(fosterCare);
    }

    @Override
    public FosterCare read(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public FosterCare update(FosterCare fosterCare) {
        if (repository.existsById(fosterCare.getCaregiverName())) {
            return repository.save(fosterCare);
        }
        return null;
    }

    @Override
    public Set<FosterCare> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public boolean delete(String id) {
        if (this.repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public Set<FosterCare> getall(){
        return new HashSet<>(repository.findAll());
    }

}
