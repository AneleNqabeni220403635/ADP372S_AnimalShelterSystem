package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long animalCode;
    protected String name;
    protected int age;
    protected String type;
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<MedicalRecord> medicalRecords = new HashSet<>();

    protected Animal() {

    }

    private Animal(Builder builder) {
        this.animalCode = builder.animalCode;
        this.name = builder.name;
        this.age = builder.age;
        this.type = builder.type;
        this.medicalRecords = builder.medicalRecords;
    }

    public Long getAnimalCode() {
        return animalCode;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return getAge() == animal.getAge() && Objects.equals(getAnimalCode(), animal.getAnimalCode()) && Objects.equals(getName(), animal.getName()) && Objects.equals(getType(), animal.getType()) && Objects.equals(getMedicalRecords(), animal.getMedicalRecords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimalCode(), getName(), getAge(), getType(), getMedicalRecords());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalCode=" + animalCode +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", medicalRecords=" + medicalRecords +
                '}';
    }

    public static class Builder {

        protected Long animalCode;
        protected String name;
        protected int age;
        protected String type;
        protected Set<MedicalRecord> medicalRecords = new HashSet<>();

        public Builder() {
        }

        public Builder setAnimalCode(Long animalCode) {
            this.animalCode = animalCode;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setMedicalRecords(Set<MedicalRecord> medicalRecords) {
            this.medicalRecords = medicalRecords;
            return this;
        }

        public Builder addMedicalRecord(MedicalRecord medicalRecord) {
            this.medicalRecords.add(medicalRecord);
            return this;
        }

        public Builder copy(Animal a) {
            this.animalCode = a.getAnimalCode();
            this.name = a.getName();
            this.age = a.getAge();
            this.type = a.getType();
            this.medicalRecords = a.getMedicalRecords();
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}
