package za.ac.cput.service;

import za.ac.cput.domain.MedicalRecord;

import java.util.Set;

public interface IMedicalRecordService extends IService<MedicalRecord, Long>{
    Set<MedicalRecord> getall();
}