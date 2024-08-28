package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firstName;
    protected String lastName;
    protected String contactNo;
    protected String emailAddress;

    public Employee() {
    }

    private Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contactNo = builder.contactNo;
        this.emailAddress = builder.emailAddress;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getFirstName(), employee.getFirstName()) &&
                Objects.equals(getLastName(), employee.getLastName()) &&
                Objects.equals(getContactNo(), employee.getContactNo()) &&
                Objects.equals(getEmailAddress(), employee.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getContactNo(), getEmailAddress());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String contactNo;
        private String emailAddress;

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

        public Builder copy(Employee e) {
            this.id = e.getId();
            this.firstName = e.getFirstName();
            this.lastName = e.getLastName();
            this.contactNo = e.getContactNo();
            this.emailAddress = e.getEmailAddress();
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
