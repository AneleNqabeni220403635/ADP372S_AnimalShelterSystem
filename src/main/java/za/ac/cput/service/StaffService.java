package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Staff;
import za.ac.cput.repository.StaffRepository;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StaffService implements IStaffService {

    private StaffRepository staffRepository;

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
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public Staff update(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Set<Staff> getall() {
        return staffRepository.findAll().stream().collect(Collectors.toSet());
    }
}
