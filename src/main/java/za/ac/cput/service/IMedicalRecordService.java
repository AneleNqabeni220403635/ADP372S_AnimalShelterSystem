package za.ac.cput.service;

import za.ac.cput.domain.MedicalRecord;

import java.util.Set;

public interface IMedicalRecordService extends IService<MedicalRecord, Long>{
    MedicalRecord create(MedicalRecord medicalRecord);
    MedicalRecord read(Long id);
    MedicalRecord update(MedicalRecord medicalRecord);
    void delete(Long id);
    Set<MedicalRecord> getall();
}
