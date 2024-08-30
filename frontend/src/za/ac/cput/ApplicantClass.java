package za.ac.cput;

import java.time.LocalDate;
import za.ac.cput.PetOwnerClass;
import za.ac.cput.CatClass;
import za.ac.cput.DogClass;

public class ApplicantClass {
    private PetOwnerClass petOwner;
    private CatClass cat;
    private DogClass dog;
    private String applicationDate;
    private String status;

    public ApplicantClass(PetOwnerClass pet_owner, String applicationDate, DogClass dog, CatClass cat, String status1) {
    	this.dog=dog;
    	this.cat=cat;
    	this.petOwner=pet_owner;
    	this.applicationDate=applicationDate;
    	this.status=status1;
    }

    public PetOwnerClass getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwnerClass petOwnerClass) {
        this.petOwner = petOwnerClass;
    }

    public CatClass getCat() {
        return cat;
    }

    public void setCat(CatClass catClass) {
        this.cat = catClass;
    }

    public DogClass getDog() {
        return dog;
    }

    public void setDog(DogClass dog) {
        this.dog = dog;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getReturnDate() {
        return status;
    }

    public void setReturnDate(String status) {
        this.status = status;
    }

	public byte[] getBytes(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
