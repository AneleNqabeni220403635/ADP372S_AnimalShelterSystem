package za.ac.cput.factory;

import za.ac.cput.domain.Cat;
import za.ac.cput.domain.Dog;
import za.ac.cput.domain.OwnerRecord;
import za.ac.cput.domain.PetOwner;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class OwnerRecordFactory {

    public static OwnerRecord buildOwnerRecord(Long id, Dog dog, Cat cat, PetOwner petOwner, LocalDate takenDate, LocalDate returnDate) {
        if (Helper.isNullorZero(id) || dog == null && cat == null || petOwner == null || takenDate == null) {
            return null;
        }

        return new OwnerRecord.Builder()
                .setId(id)
                .setDog(dog)
                .setCat(cat)
                .setPetOwner(petOwner)
                .setTakenDate(takenDate)
                .setReturnDate(returnDate)
                .build();
    }

    public static OwnerRecord buildOwnerRecord(Dog dog, Cat cat, PetOwner petOwner, LocalDate takenDate, LocalDate returnDate) {
        if (dog == null && cat == null || petOwner == null || takenDate == null) {
            return null;
        }

        Long id = Helper.generateOwnerRecordId(); // Assuming Helper has a method to generate an ID

        return new OwnerRecord.Builder()
                .setId(id)
                .setDog(dog)
                .setCat(cat)
                .setPetOwner(petOwner)
                .setTakenDate(takenDate)
                .setReturnDate(returnDate)
                .build();
    }
}
