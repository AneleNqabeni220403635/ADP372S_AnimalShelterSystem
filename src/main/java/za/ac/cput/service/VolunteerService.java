package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Volunteer;
import za.ac.cput.repository.VolunteerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class VolunteerService implements IVolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer create(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer read(Long id) {
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(id);
        return volunteerOptional.orElse(null);
    }

    @Override
    public Volunteer update(Volunteer volunteer) {
        if (volunteerRepository.existsById(volunteer.getId())) {
            return volunteerRepository.save(volunteer);
        }
        return null;
    }

    @Override
    public Set<Volunteer> findAll() {
        return new HashSet<>(volunteerRepository.findAll());
    }

    @Override
    public boolean delete(Long id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Set<Volunteer> getall() {
        return findAll();
    }
}

