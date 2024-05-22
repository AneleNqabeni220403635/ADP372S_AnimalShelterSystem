package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class  Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long animalCode;
    private String name;
    private int age;
    private String type;
    @ManyToOne
    protected MedicalRecord medicalRecord;

    protected Animal() {

    }

    private Animal(Builder builder) {
        this.animalCode = builder.animalCode;
        this.name = builder.name;
        this.age = builder.age;
        this.type = builder.type;
        this.medicalRecord = builder.medicalRecord;
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

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return getAge() == animal.getAge() && Objects.equals(getAnimalCode(), animal.getAnimalCode()) && Objects.equals(getName(), animal.getName()) && Objects.equals(getType(), animal.getType()) && Objects.equals(getMedicalRecord(), animal.getMedicalRecord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimalCode(), getName(), getAge(), getType(), getMedicalRecord());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalCode=" + animalCode +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", medicalRecord=" + medicalRecord +
                '}';
    }

    public static class Builder {

        protected Long animalCode;
        protected String name;
        protected int age;
        protected String type;
        protected MedicalRecord medicalRecord;
public Builder(){

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

        public Builder setMedicalRecord(MedicalRecord medicalRecord) {
            this.medicalRecord = medicalRecord;
            return this;
        }
        public Builder copy(Animal a) {
            this.animalCode = a.getAnimalCode();
            this.name = a.getName();
            this.age = a.getAge();
            this.type = a.getType();
            this.medicalRecord = a.getMedicalRecord();
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}
