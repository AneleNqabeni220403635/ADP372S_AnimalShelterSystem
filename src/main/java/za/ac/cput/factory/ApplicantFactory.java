package za.ac.cput.factory;

import za.ac.cput.domain.Applicant;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Arrays;

public class ApplicantFactory {
    public static Applicant createApplicant (LocalDate applicationDate,
                                                  String applicationStatus)
    {
        if (Helper.isNullorEmpty(applicationStatus))
            return null;
        if(!Arrays.stream(Applicant.Statuses).anyMatch(status -> status.equals(applicationStatus)))
            return null;

        return new Applicant.Builder()
                .setApplicationDate(applicationDate)
                .setApplicationStatus(applicationStatus)
                .build();
    }
}
