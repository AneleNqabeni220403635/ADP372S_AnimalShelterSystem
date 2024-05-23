package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Volunteer;
import za.ac.cput.repository.VolunteerRepository;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VolunteerService implements IVolunteerService{

    private VolunteerRepository volunteerRepository;

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
        return volunteerRepository.findById(id).orElse(null);
    }

    @Override
    public Volunteer update(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void delete(Long id) {
        volunteerRepository.deleteById(id);
    }

    @Override
    public Set<Volunteer> getall() {
        return volunteerRepository.findAll().stream().collect(Collectors.toSet());
    }
}
