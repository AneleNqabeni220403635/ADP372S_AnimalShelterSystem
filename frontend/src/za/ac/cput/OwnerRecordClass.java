package za.ac.cput;

import java.time.LocalDate;

public class OwnerRecordClass {
    private PetOwnerClass petOwner;
    private CatClass cat;
    private DogClass dog;
    private String takenDate;
    private String returnDate;

    public OwnerRecordClass(DogClass dog,CatClass cat,PetOwnerClass pet,String takenDate,String returnDate) {
        this.dog=dog;
        this.cat=cat;
        this.petOwner=pet;
        this.takenDate=takenDate;
        this.returnDate=returnDate;
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

    public String getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(String takenDate) {
        this.takenDate = takenDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public byte[] getBytes(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}

