package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.repository.IncidentReportRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class IncidentReportService implements IIncidentReportService {

    @Autowired
    private IncidentReportRepository repository;

    @Override
    public IncidentReport create(IncidentReport incidentReport) {
        return repository.save(incidentReport);
    }

    @Override
    public IncidentReport read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public IncidentReport update(IncidentReport incidentReport) {
        if (repository.existsById(incidentReport.getId())) {
            return repository.save(incidentReport);
        }
        return null;
    }

    @Override
    public Set<IncidentReport> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}