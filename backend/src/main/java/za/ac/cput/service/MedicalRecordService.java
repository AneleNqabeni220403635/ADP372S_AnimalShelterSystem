package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.repository.MedicalRecordRepository;
import za.ac.cput.service.Impl.IMedicalRecordService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    private final MedicalRecordRepository repository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository repository) {
        this.repository = repository;
    }


    public MedicalRecord create(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }


    public MedicalRecord read(Long id) {
        return repository.findById(id).orElse(null);
    }


    public MedicalRecord update(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public Set<MedicalRecord> getall() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }
}
