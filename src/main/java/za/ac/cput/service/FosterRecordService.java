package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.FosterRecord;
import za.ac.cput.repository.FosterRecordRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FosterRecordService implements IFosterRecord{
    private FosterRecordRepository repository;

    @Autowired
    public FosterRecordService(FosterRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public FosterRecord create(FosterRecord fosterRecord) {
        return repository.save(fosterRecord);
    }

    @Override
    public FosterRecord read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public FosterRecord update(FosterRecord fosterRecord) {
        if (repository.existsById(fosterRecord.getAnimalId())) {
            return repository.save(fosterRecord);
        }
        return null;
    }
    @Override
    public FosterRecord delete(String id) {
        return repository.findById(id).orElse(null);

    }

    @Override
    public Set<FosterRecord> getAll() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

}
