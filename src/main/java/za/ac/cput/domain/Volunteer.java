package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long volunteerId;
    private String name;
    private int age;
    private String contactNumber;
    private String email;
    private String availability;

    protected Volunteer() {
        // Default constructor for JPA
    }

    private Volunteer(Builder builder) {
        this.volunteerId = builder.volunteerId;
        this.name = builder.name;
        this.age = builder.age;
        this.contactNumber = builder.contactNumber;
        this.email = builder.email;
        this.availability = builder.availability;
    }

    public long getVolunteerId() {
        return volunteerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Volunteer volunteer)) return false;
        return getAge() == volunteer.getAge() && getVolunteerId() == volunteer.getVolunteerId() && Objects.equals(getName(), volunteer.getName()) && Objects.equals(getContactNumber(), volunteer.getContactNumber()) && Objects.equals(getEmail(), volunteer.getEmail()) && Objects.equals(getAvailability(), volunteer.getAvailability());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVolunteerId(), getName(), getAge(), getContactNumber(), getEmail(), getAvailability());
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "volunteerId=" + volunteerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }

    public static class Builder {
        private long volunteerId;
        private String name;
        private int age;
        private String contactNumber;
        private String email;
        private String availability;

        public Builder() {
        }

        public Builder setVolunteerId(long volunteerId) {
            this.volunteerId = volunteerId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public Builder copy(Volunteer volunteer) {
            this.volunteerId = volunteer.getVolunteerId();
            this.name = volunteer.getName();
            this.age = volunteer.getAge();
            this.contactNumber = volunteer.getContactNumber();
            this.email = volunteer.getEmail();
            this.availability = volunteer.getAvailability();
            return this;
        }

        public Volunteer build() {
            return new Volunteer(this);
        }
    }
}
