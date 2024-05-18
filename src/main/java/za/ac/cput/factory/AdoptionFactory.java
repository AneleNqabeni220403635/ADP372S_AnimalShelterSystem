package za.ac.cput.factory;


import za.ac.cput.domain.Adoption;
import za.ac.cput.domain.Animal;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class AdoptionFactory {

    public static Adoption createAdoption(Long adoptionId, String applicantName, LocalDate applicationDate, String status, Animal animal) {
        if (Helper.isNullorZero(adoptionId) || Helper.isNullorEmpty(applicantName) || applicationDate == null || Helper.isNullorEmpty(status) || animal == null) {
            return null;
        }

        return new Adoption.Builder()
                .setAdoptionId(adoptionId)
                .setApplicantName(applicantName)
                .setApplicationDate(applicationDate)
                .setStatus(status)
                .setAnimal(animal)
                .build();
    }
}