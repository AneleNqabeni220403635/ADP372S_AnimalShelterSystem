package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.MedicalRecord;

import java.util.Set;

@Service
public interface IMedicalRecordService extends IService<MedicalRecord, Long>{
    Set<MedicalRecord> getall();
}