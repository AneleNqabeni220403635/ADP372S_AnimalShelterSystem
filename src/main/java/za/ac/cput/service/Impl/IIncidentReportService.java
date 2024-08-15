package za.ac.cput.service.Impl;

import za.ac.cput.domain.IncidentReport;

import java.util.Set;

public interface IIncidentReportService extends IService<IncidentReport, Long>
{
    Set<IncidentReport> getAll();
}
