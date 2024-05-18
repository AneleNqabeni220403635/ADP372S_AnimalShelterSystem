package za.ac.cput.factory;

import za.ac.cput.domain.Animal;
import za.ac.cput.domain.IncidentReport;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class IncidentReportFactory {
    public static IncidentReport createIncidentReport (Long id,
                                                               Animal animal,
                                                               String incidentType,
                                                               LocalDateTime incidentDate,
                                                               String description,
                                                               String actionsTaken,
                                                               String reportedBy)
    {
        if (Helper.isNullorZero(id) ||
                !Helper.IsValidAnimal(animal.getAnimalCode()) ||
                Helper.isNullorEmpty(incidentType) ||
                Helper.isNullorEmpty(description)||
                Helper.isNullorEmpty(actionsTaken)||
                Helper.isNullorEmpty(reportedBy))
        {
            return null;
        }

        return new IncidentReport.Builder()
                .setId(id)
                .setAnimal(animal)
                .setIncidentType(incidentType)
                .setIncidentDate(incidentDate)
                .build();
    }
}
