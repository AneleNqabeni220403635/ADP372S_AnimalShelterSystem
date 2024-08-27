package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Applicant;
import za.ac.cput.repository.ApplicantRepository;
import za.ac.cput.service.Impl.IApplicantService;

import java.util.HashSet;
import java.util.Set;

@Service
public class ApplicantService implements IApplicantService {

    private final ApplicantRepository repository;

    @Autowired
    public ApplicantService(ApplicantRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Applicant create(Applicant applicant) {
        return repository.save(applicant);
    }

    @Override
    public Applicant read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Applicant update(Applicant applicant) {
        if (repository.existsById(applicant.getId())) {
            return repository.save(applicant);
        }
        return null;
    }

    public Set<Applicant> getAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id))
        {
            repository.deleteById(id);
        }
    }
}