package za.ac.cput.domain.demography;

public class Breed {
private String breedId;
private String name;
private Breed(){
}

    public String getBreedId() {
        return breedId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedId='" + breedId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
