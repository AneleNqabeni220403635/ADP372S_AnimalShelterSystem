package za.ac.cput;


public class CatClass {
    private String catId;
    private String name;
    private String breed;
    private String cageNumber;
    private String gender;
    private String size;
    private String age;

    public CatClass(String id, String name, String breed, String cageNumber, String gender, String size, String age) {
        this.catId = id;
        this.name = name;
        this.breed = breed;
        this.cageNumber = cageNumber;
        this.gender = gender;
        this.size = size;
        this.age = age;
    }

    // Getter and Setter for id
    public String getId() {
        return catId;
    }

    public void setId(String id) {
        this.catId = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for breed
    public String getBreed() {
    	System.out.print("breead is "+breed);
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Getter and Setter for cageNumber
    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    // Getter and Setter for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter for size
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Getter and Setter for age
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
