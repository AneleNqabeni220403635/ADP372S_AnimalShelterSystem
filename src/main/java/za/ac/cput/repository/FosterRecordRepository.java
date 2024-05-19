package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.FosterRecord;

@Repository
public interface FosterRecordRepository extends JpaRepository <FosterRecord, String> {
}
