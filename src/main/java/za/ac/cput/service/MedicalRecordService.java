package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.repository.MedicalRecordRepository;
import java.util.HashSet;
import java.util.Set;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    private final MedicalRecordRepository repository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public MedicalRecord create(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }

    @Override
    public MedicalRecord read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        if (repository.existsById(medicalRecord.getAnimal())) {
            return repository.save(medicalRecord);
        }
        return null;
    }

    @Override
    public Set<MedicalRecord> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public Set<MedicalRecord> getall() {
        return new HashSet<>(repository.findAll());
    }
}
