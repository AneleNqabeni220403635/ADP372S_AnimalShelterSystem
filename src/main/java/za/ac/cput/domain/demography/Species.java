package za.ac.cput.domain.demography;

public class Species {
    private String speciesId;
    private String name;
    private Species(){
    }

    public String getSpeciesId() {

        return speciesId;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Species{" +
                "speciesId='" + speciesId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
