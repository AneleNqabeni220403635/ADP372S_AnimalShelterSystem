package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AnimalsAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String species;
    private String breed;
    private String gender;
    private Double weight;
    private Boolean available;
    @ManyToOne(cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

    protected AnimalsAvailable() {

    }

    private AnimalsAvailable(Builder builder) {
        this.id = builder.id;
        this.species = builder.species;
        this.breed = builder.breed;
        this.gender = builder.gender;
        this.weight = builder.weight;
        this.available = builder.available;
        this.medicalRecord = builder.medicalRecord;
    }

    public Long getId() {
        return id;
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
        return Double.compare(animal.getWeight(), getWeight()) == 0 && getAvailable().equals(animal.getAvailable()) && Objects.equals(getId(), animal.getId()) && Objects.equals(getSpecies(), animal.getSpecies()) && Objects.equals(getBreed(), animal.getBreed()) && Objects.equals(getGender(), animal.getGender()) && Objects.equals(getMedicalRecord(), animal.getMedicalRecord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpecies(), getBreed(), getGender(), getWeight(), getAvailable(), getMedicalRecord());
    }

    @Override
    public String toString() {
        return "AnimalsAvailable{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", available=" + available +
                ", medicalRecord=" + medicalRecord +
                '}';
    }

    public static class Builder {

        protected Long id;
        protected String species;
        protected String breed;
        protected String gender;
        protected Double weight;
        protected Boolean available;
        protected MedicalRecord medicalRecord;

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder copy(AnimalsAvailable a) {
            this.id = a.getId();
            this.species = a.getSpecies();
            this.breed = a.getBreed();
            this.gender = a.getGender();
            this.weight = a.getWeight();
            this.available = a.getAvailable();
            this.medicalRecord = a.getMedicalRecord();
            return this;
        }

        public AnimalsAvailable build() {
            return new AnimalsAvailable(this);
        }
    }
}