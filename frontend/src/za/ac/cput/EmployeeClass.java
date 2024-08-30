package za.ac.cput;

public class EmployeeClass {

    protected String id;
    protected String firstName;
    protected String lastName;
    protected String contactNo;
    protected String emailAddress;


    public EmployeeClass(String id, String firstName, String lastName, String contactNo, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNo=" + contactNo
                + ", emailAddress=" + emailAddress + "]";
    }
}
