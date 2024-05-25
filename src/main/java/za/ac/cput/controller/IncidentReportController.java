package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.service.IncidentReportService;

import java.util.Set;

@RestController
@RequestMapping("/incidentReport")
public class IncidentReportController
{
    @Autowired
    private IncidentReportService incidentReportService;

    // Some places wrap the returning datatype over here in a ResponseEntity instead of in the test
    @PostMapping("/create")
    public IncidentReport create(@RequestBody IncidentReport incidentReport)
    {
        return incidentReportService.create(incidentReport);
    }

    @GetMapping("/read/{incidentReportId}")
    public IncidentReport read(@PathVariable Long incidentReportId)
    {
        return incidentReportService.read(incidentReportId);
    }

    @PostMapping("/update")
    public IncidentReport update (@RequestBody IncidentReport incidentReport)
    {
        return incidentReportService.update(incidentReport);
    }

    @PostMapping("/delete/{incidentReportId}")
    public void delete(@PathVariable Long incidentReportId)
    {
        incidentReportService.delete(incidentReportId);
    }

    @GetMapping("/getAll")
    public Set<IncidentReport> getAll()
    {
        return incidentReportService.getAll();
    }
}
