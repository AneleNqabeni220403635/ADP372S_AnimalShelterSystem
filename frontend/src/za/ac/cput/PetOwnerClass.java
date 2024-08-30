package za.ac.cput;

public class PetOwnerClass {
	private String Id;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String emailAddress;
    private String streetAddress;

    public PetOwnerClass(String createdPetId,String firstName, String lastName, String contactNo, String emailAddress, String streetAddress) {
    	this.Id=createdPetId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.streetAddress = streetAddress;
    }

    
    public String getId() {
        return Id;
    }

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for contactNo
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    // Getter and Setter for emailAddress
    public String getEmailAddress() {
    	System.out.print("email"+emailAddress);
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // Getter and Setter for streetAddress
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
