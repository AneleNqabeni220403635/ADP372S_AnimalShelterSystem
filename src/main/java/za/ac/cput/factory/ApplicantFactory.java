package za.ac.cput.factory;

import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Arrays;

public class ApplicantFactory {
    public static Applicant buildApplicant(Long id, PetOwner petOwner, LocalDate applicationDate, Dog dogId, Cat catId, String status) {
        if (Helper.isNullorZero(id) || petOwner == null || applicationDate == null || Helper.isNullorEmpty(status)) {
            return null;
        }

        return new Applicant.Builder()
                .setId(id)
                .setPetOwner(petOwner)
                .setApplicationDate(applicationDate)
                .setDogId(dogId)
                .setCatId(catId)
                .setStatus(status)
                .build();
    }

    public static Applicant buildApplicant(PetOwner petOwner, LocalDate applicationDate, Dog dogId, Cat catId, String status) {
        if (petOwner == null || applicationDate == null || Helper.isNullorEmpty(status)) {
            return null;
        }

        Long id = Helper.generateApplicantId();

        return new Applicant.Builder()
                .setId(id)
                .setPetOwner(petOwner)
                .setApplicationDate(applicationDate)
                .setDogId(dogId)
                .setCatId(catId)
                .setStatus(status)
                .build();
    }
}
