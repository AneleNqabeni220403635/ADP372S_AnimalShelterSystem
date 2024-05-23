package za.ac.cput.factory;

import za.ac.cput.domain.Volunteer;

public class VolunteerFactory {

    public static Volunteer createVolunteer(long volunteerId, String name, int age, String contactNumber, String email, String availability) {
        return new Volunteer.Builder()
                .setVolunteerId(volunteerId)
                .setName(name)
                .setAge(age)
                .setContactNumber(contactNumber)
                .setEmail(email)
                .setAvailability(availability)
                .build();
    }
}
