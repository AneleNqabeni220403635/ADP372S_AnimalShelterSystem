package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.IncidentReport;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long>
{
}
