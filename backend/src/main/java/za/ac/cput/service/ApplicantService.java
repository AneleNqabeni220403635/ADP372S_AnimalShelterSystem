package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.repository.ApplicantRepository;
import za.ac.cput.service.Impl.IApplicantService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicantService implements IApplicantService
{
    private final ApplicantRepository repository;

    @Autowired
    public ApplicantService(ApplicantRepository repository)
    {
        this.repository = repository;
    }

    public Applicant create(Applicant applicant)
    {
        return repository.save(applicant);
    }

    public Applicant read(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public Applicant update(Applicant applicant) {
        return repository.save(applicant);
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public Set<Applicant> getall()
    {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

    public Applicant readCatId(Cat cat)
    {
        return repository.findByCatId(cat).orElse(null);
    }

    public Applicant readDogId(Dog dog)
    {
        return repository.findByDogId(dog).orElse(null);
    }
    public List<Applicant> readStatus(String status)
    {
        System.out.println("Attempting to find applications with status of: " + status);
        return repository.findByStatus(status);
    }
}