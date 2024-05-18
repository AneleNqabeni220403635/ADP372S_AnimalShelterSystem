package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class AnimalsAvailable extends Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalCode;
    private String species;
    private String breed;
    private String gender;
    private Double weight;
    private Boolean available;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    protected AnimalsAvailable() {
    }

    private AnimalsAvailable(Builder builder) {
        this.animalCode = builder.animalCode;
        this.species = builder.species;
        this.breed = builder.breed;
        this.gender = builder.gender;
        this.weight = builder.weight;
        this.available = builder.available;
        this.medicalRecord = builder.medicalRecord;
    }

    public Long getAnimalCode() {
        return animalCode;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public Double getWeight() {
        return weight;
    }

    public Boolean getAvailable() {
        return available;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimalsAvailable animal)) return false;
        return Double.compare(animal.getWeight(), getWeight()) == 0 && getAvailable().equals(animal.getAvailable()) && Objects.equals(getAnimalCode(), animal.getAnimalCode()) && Objects.equals(getSpecies(), animal.getSpecies()) && Objects.equals(getBreed(), animal.getBreed()) && Objects.equals(getGender(), animal.getGender()) && Objects.equals(getMedicalRecord(), animal.getMedicalRecord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimalCode(), getSpecies(), getBreed(), getGender(), getWeight(), getAvailable(), getMedicalRecord());
    }

    @Override
    public String toString() {
        return "AnimalsAvailable{" +
                "animalCode=" + animalCode +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", available=" + available +
                ", medicalRecord=" + medicalRecord +
                '}';
    }

    public static class Builder {
        private Long animalCode;
        private String species;
        private String breed;
        private String gender;
        private Double weight;
        private Boolean available;
        private MedicalRecord medicalRecord;

        public Builder setAnimalCode(Long animalCode) {
            this.animalCode = animalCode;
            return this;
        }

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setBreed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setWeight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setAvailable(Boolean available) {
            this.available = available;
            return this;
        }

        public Builder setMedicalRecord(MedicalRecord medicalRecord) {
            this.medicalRecord = medicalRecord;
            return this;
        }

        public Builder copy(AnimalsAvailable animal) {
            this.animalCode = animal.getAnimalCode();
            this.species = animal.getSpecies();
            this.breed = animal.getBreed();
            this.gender = animal.getGender();
            this.weight = animal.getWeight();
            this.available = animal.getAvailable();
            this.medicalRecord = animal.getMedicalRecord();
            return this;
        }

        public AnimalsAvailable build() {
            return new AnimalsAvailable(this);
        }
    }
}