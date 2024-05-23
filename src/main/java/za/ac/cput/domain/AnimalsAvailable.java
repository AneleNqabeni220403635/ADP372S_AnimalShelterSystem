package za.ac.cput.domain;

import java.util.Objects;

public class AnimalsAvailable extends Animal {
    private Long animalCode;
    private String species;
    private String breed;
    private String gender;
    private double weight;
    private boolean available;

    private MedicalRecord medicalRecord;


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

    public double getWeight() {
        return weight;
    }

    public boolean getAvailable() {
        return available;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalsAvailable that = (AnimalsAvailable) o;
        return Double.compare(that.weight, weight) == 0 &&
                available == that.available &&
                Objects.equals(animalCode, that.animalCode) &&
                Objects.equals(species, that.species) &&
                Objects.equals(breed, that.breed) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(medicalRecord, that.medicalRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash( animalCode, species, breed, gender, weight, available, medicalRecord);
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
        public Long animalCode;
        private Long id;
        private String species;
        private String breed;
        private String gender;
        private double weight;
        private boolean available;
        private MedicalRecord medicalRecord;

        public Builder() {
        }

        public Builder copy(AnimalsAvailable animalsAvailable) {
            this.id = animalsAvailable.animalCode;
            this.species = animalsAvailable.species;
            this.breed = animalsAvailable.breed;
            this.gender = animalsAvailable.gender;
            this.weight = animalsAvailable.weight;
            this.available = animalsAvailable.available;
            this.medicalRecord = animalsAvailable.medicalRecord;
            return this;
        }

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

        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setAvailable(boolean available) {
            this.available = available;
            return this;
        }

        public Builder setMedicalRecord(MedicalRecord medicalRecord) {
            this.medicalRecord = medicalRecord;
            return this;
        }


        public AnimalsAvailable build() {
            return new AnimalsAvailable(this);
        }
    }
}