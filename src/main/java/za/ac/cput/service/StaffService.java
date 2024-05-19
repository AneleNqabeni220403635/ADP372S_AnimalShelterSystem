package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Staff;
import za.ac.cput.repository.StaffRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff create(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff read(Long id) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        return staffOptional.orElse(null);
    }

    @Override
    public Staff update(Staff staff) {
        if (staffRepository.existsById(staff.getId())) {
            return staffRepository.save(staff);
        }
        return null;
    }

    @Override
    public Set<Staff> findAll() {
        return new HashSet<>(staffRepository.findAll());
    }

    @Override
    public boolean delete(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Set<Staff> getall() {
        return findAll();
    }
}
