package za.ac.cput.factory;

import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Arrays;

public class ApplicantFactory {
    public static Applicant createApplicant (LocalDate applicationDate,
                                             String applicationStatus,
                                             PetOwner petOwner,
                                             Dog dog,
                                             Cat cat)
    {
        if (Helper.isNullorEmpty(applicationStatus))
            return null;
        if(!Arrays.stream(Applicant.Statuses).anyMatch(status -> status.equals(applicationStatus)))
            return null;
        if(petOwner == null)
            return null;

        return new Applicant.Builder()
                .setApplicationDate(applicationDate)
                .setApplicationStatus(applicationStatus)
                .setPetOwner(petOwner)
                .setDog(dog)
                .setCat(cat)
                .build();
    }
}
