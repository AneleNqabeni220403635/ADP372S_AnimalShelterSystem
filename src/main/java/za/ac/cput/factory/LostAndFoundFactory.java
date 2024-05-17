package za.ac.cput.factory;

import za.ac.cput.domain.LostAndFound;

import java.time.LocalDateTime;

public class LostAndFoundFactory {
    public static LostAndFound buildLostAndFound (Long id,
                                                  String species,
                                                  String breed,
                                                  String description,
                                                  LocalDateTime reportedDate,
                                                  String status,
                                                  String reporterContactName,
                                                  String reporterContactNumber)
    {
        if (Helper.isNullofZero(id) ||
                Helper.isNullorEmpty(species) ||
                Helper.isNullorEmpty(breed) ||
                Helper.isNullorEmpty(description)||
                Helper.isNullorEmpty(status) ||
                Helper.isNullorEmpty(reporterContactName) ||
                Helper.isNullorEmpty(reporterContactNumber))
        {
            return null;
        }

        return new LostAndFound
                .Builder()
                .setId(id)
                .setSpecies(species)
                .setBreed(breed)
                .setDescription(description)
                .setReportedDate(reportedDate)
                .setReporterContactName(reporterContactName)
                .setReporterContactNumber(reporterContactNumber)
                .setStatus(status)
                .build();
    }
}
