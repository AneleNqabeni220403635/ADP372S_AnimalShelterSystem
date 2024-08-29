package za.ac.cput.factory;

import za.ac.cput.domain.Volunteer;
import za.ac.cput.util.Helper;


public class VolunteerFactory {

    public static Volunteer buildVolunteer(Long id, String firstName, String lastName, String contactNo, String emailAddress, String streetAddress, String availability) {
        if (Helper.isNullorZero(id) || Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(contactNo) || Helper.isNullorEmpty(emailAddress) || Helper.isNullorEmpty(streetAddress) || Helper.isNullorEmpty(availability)) {
            return null;
        }

        return new Volunteer.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContactNo(contactNo)
                .setEmailAddress(emailAddress)
                .setStreetAddress(streetAddress)
                .setAvailability(availability)
                .build();
    }

    public static Volunteer buildVolunteer(String firstName, String lastName, String contactNo, String emailAddress, String streetAddress, String availability) {
        if (Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(contactNo) || Helper.isNullorEmpty(emailAddress) || Helper.isNullorEmpty(streetAddress) || Helper.isNullorEmpty(availability)) {
            return null;
        }

        Long id = Helper.generateVolunteerId();

        return new Volunteer.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContactNo(contactNo)
                .setEmailAddress(emailAddress)
                .setStreetAddress(streetAddress)
                .setAvailability(availability)
                .build();
    }
}