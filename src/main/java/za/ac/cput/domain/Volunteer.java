package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firstName;
    protected String lastName;
    protected String contactNo;
    protected String emailAddress;
    protected String streetAddress;
    protected String availability;

    protected Volunteer() {
    }

    private Volunteer(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contactNo = builder.contactNo;
        this.emailAddress = builder.emailAddress;
        this.streetAddress = builder.streetAddress;
        this.availability = builder.availability;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Volunteer volunteer)) return false;
        return Objects.equals(getId(), volunteer.getId()) &&
                Objects.equals(getFirstName(), volunteer.getFirstName()) &&
                Objects.equals(getLastName(), volunteer.getLastName()) &&
                Objects.equals(getContactNo(), volunteer.getContactNo()) &&
                Objects.equals(getEmailAddress(), volunteer.getEmailAddress()) &&
                Objects.equals(getStreetAddress(), volunteer.getStreetAddress()) &&
                Objects.equals(getAvailability(), volunteer.getAvailability());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getContactNo(), getEmailAddress(), getStreetAddress(), getAvailability());
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String contactNo;
        private String emailAddress;
        private String streetAddress;
        private String availability;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setContactNo(String contactNo) {
            this.contactNo = contactNo;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder setAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public Builder copy(Volunteer v) {
            this.id = v.getId();
            this.firstName = v.getFirstName();
            this.lastName = v.getLastName();
            this.contactNo = v.getContactNo();
            this.emailAddress = v.getEmailAddress();
            this.streetAddress = v.getStreetAddress();
            this.availability = v.getAvailability();
            return this;
        }

        public Volunteer build() {
            return new Volunteer(this);
        }
    }
}
