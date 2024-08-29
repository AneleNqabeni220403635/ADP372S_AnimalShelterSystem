package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OwnerRecord;
import za.ac.cput.repository.OwnerRecordRepository;
import za.ac.cput.service.Impl.IOwnerRecordService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerRecordService implements IOwnerRecordService {
    private final OwnerRecordRepository repository;

    @Autowired
    public OwnerRecordService(OwnerRecordRepository repository) {
        this.repository = repository;
    }


    public OwnerRecord create(OwnerRecord ownerRecord) {
        return repository.save(ownerRecord);
    }


    public OwnerRecord read(Long id) {
        return repository.findById(id).orElse(null);
    }


    public OwnerRecord update(OwnerRecord ownerRecord) {
        return repository.save(ownerRecord);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<OwnerRecord> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }
}
