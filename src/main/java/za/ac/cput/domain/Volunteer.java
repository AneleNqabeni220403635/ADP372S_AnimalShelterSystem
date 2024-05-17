package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int hoursPerWeek;
    private String role;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Staff> staffEntries = new ArrayList<>();

    protected Volunteer() {}

    private Volunteer(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.hoursPerWeek = builder.hoursPerWeek;
        this.role = builder.role;
        this.staffEntries = builder.staffEntries != null ? builder.staffEntries : new ArrayList<>();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private int hoursPerWeek;
        private String role;
        private List<Staff> staffEntries = new ArrayList<>();

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setHoursPerWeek(int hoursPerWeek) {
            this.hoursPerWeek = hoursPerWeek;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder addStaffEntry(Staff staff) {
            this.staffEntries.add(staff);
            return this;
        }

        public Volunteer build() {
            return new Volunteer(this);
        }
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hoursPerWeek=" + hoursPerWeek +
                ", role='" + role + '\'' +
                ", staffEntries=" + staffEntries +
                '}';
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

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public String getRole() {
        return role;
    }

    public List<Staff> getStaffEntries() {
        return staffEntries;
    }
}
