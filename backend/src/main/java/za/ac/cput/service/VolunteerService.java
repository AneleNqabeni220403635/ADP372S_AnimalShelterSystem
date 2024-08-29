package za.ac.cput.service;

import za.ac.cput.domain.Volunteer;
import za.ac.cput.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.service.Impl.IVolunteerService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VolunteerService implements IVolunteerService {
    private final VolunteerRepository repository;

    @Autowired
    public VolunteerService(VolunteerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Volunteer create(Volunteer volunteer) {
        return repository.save(volunteer);
    }

    @Override
    public Volunteer read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Volunteer update(Volunteer volunteer) {
        return repository.save(volunteer);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<Volunteer> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }
}
