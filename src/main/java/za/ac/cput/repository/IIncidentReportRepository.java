package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.IncidentReport;

public interface IIncidentReportRepository extends JpaRepository<IncidentReport, Long>
{
}
