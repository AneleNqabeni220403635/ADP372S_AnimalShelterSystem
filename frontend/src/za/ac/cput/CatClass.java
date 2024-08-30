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

    public String getId() {
        return catId;
    }

    public void setId(String id) {
        this.catId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        System.out.print("breed is " + breed);
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
