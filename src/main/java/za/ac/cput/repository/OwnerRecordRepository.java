package za.ac.cput.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OwnerRecord;


@Repository
public interface OwnerRecordRepository extends JpaRepository<OwnerRecord, Long> {
}