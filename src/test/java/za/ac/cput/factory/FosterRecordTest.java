package za.ac.cput.factory;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.FosterRecord;

    interface FosterRecordRepository extends JpaRepository<FosterRecord, String> {

    }


