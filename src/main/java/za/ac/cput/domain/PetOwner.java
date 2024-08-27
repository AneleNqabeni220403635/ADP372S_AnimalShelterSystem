package za.ac.cput.domain;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


@Entity
public class PetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firstName;
    protected String lastName;
    protected String contactNo;
    protected String emailAddress;
    protected String streetAddress;

    public PetOwner() {
    }

    private PetOwner(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contactNo = builder.contactNo;
        this.emailAddress = builder.emailAddress;
        this.streetAddress = builder.streetAddress;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetOwner petOwner)) return false;
        return Objects.equals(getId(), petOwner.getId()) &&
                Objects.equals(getFirstName(), petOwner.getFirstName()) &&
                Objects.equals(getLastName(), petOwner.getLastName()) &&
                Objects.equals(getContactNo(), petOwner.getContactNo()) &&
                Objects.equals(getEmailAddress(), petOwner.getEmailAddress()) &&
                Objects.equals(getStreetAddress(), petOwner.getStreetAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getContactNo(), getEmailAddress(), getStreetAddress());
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String contactNo;
        private String emailAddress;
        private String streetAddress;

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

        public Builder copy(PetOwner po) {
            this.id = po.getId();
            this.firstName = po.getFirstName();
            this.lastName = po.getLastName();
            this.contactNo = po.getContactNo();
            this.emailAddress = po.getEmailAddress();
            this.streetAddress = po.getStreetAddress();
            return this;
        }

        public PetOwner build() {
            return new PetOwner(this);
        }
    }
}
