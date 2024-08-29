package za.ac.cput.factory;import za.ac.cput.domain.PetOwner;
import za.ac.cput.util.Helper;

public class PetOwnerFactory {
    public static PetOwner buildPetOwner(long petOwnerId, String firstName, String lastName, String contactNo, String emailAddress, String streetAddress) {
        if (Helper.isNullorZero(petOwnerId) || Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(contactNo) || Helper.isNullorEmpty(emailAddress) || Helper.isNullorEmpty(streetAddress)) {
            return null;
        }

        return new PetOwner.Builder()
                .setId(petOwnerId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContactNo(contactNo)
                .setEmailAddress(emailAddress)
                .setStreetAddress(streetAddress)
                .build();
    }

    public static PetOwner buildPetOwner(String firstName, String lastName, String contactNo, String emailAddress, String streetAddress) {
        if (Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(contactNo) || Helper.isNullorEmpty(emailAddress) || Helper.isNullorEmpty(streetAddress)) {
            return null;
        }

        long petOwnerId = Helper.generatePetOwnerId();

        return new PetOwner.Builder()
                .setId(petOwnerId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContactNo(contactNo)
                .setEmailAddress(emailAddress)
                .setStreetAddress(streetAddress)
                .build();
    }
}
