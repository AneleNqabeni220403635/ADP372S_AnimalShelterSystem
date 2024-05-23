package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Animal;
import za.ac.cput.domain.MedicalRecord;

import java.util.Set;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{
    Set<MedicalRecord> findAllMedicalRecordByAnimal(Long animal);
}
