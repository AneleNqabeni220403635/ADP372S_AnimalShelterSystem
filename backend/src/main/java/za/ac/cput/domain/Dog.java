package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    protected long dogId;
    protected String name;
    protected String size;
    protected int age;
    protected String gender;
    protected String breed;
    protected int cageNumber;


    public Dog() {
    }

    private Dog(Builder builder) {
        this.dogId = builder.dogId;
        this.name = builder.name;
        this.size = builder.size;
        this.age = builder.age;
        this.gender = builder.gender;
        this.breed = builder.breed;
        this.cageNumber = builder.cageNumber;
    }


    public long getDogId() {
        return dogId;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public int getCageNumber() {
        return cageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog dog)) return false;
        return getAge() == dog.getAge() &&
                getCageNumber() == dog.getCageNumber() &&
                Objects.equals(getDogId(), dog.getDogId()) &&
                Objects.equals(getName(), dog.getName()) &&
                Objects.equals(getSize(), dog.getSize()) &&
                Objects.equals(getGender(), dog.getGender()) &&
                Objects.equals(getBreed(), dog.getBreed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDogId(), getName(), getSize(), getAge(), getGender(), getBreed(), getCageNumber());
    }


    @Override
    public String toString() {
        return "Dog{" +
                "dogId=" + dogId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", breed='" + breed + '\'' +
                ", cageNumber=" + cageNumber +
                '}';
    }

    public static class Builder {
        protected Long dogId;
        protected String name;
        protected String size;
        protected int age;
        protected String gender;
        protected String breed;
        protected int cageNumber;

        public Builder() {
        }

        public Builder setDogId(Long dogId) {
            this.dogId = dogId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setBreed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder setCageNumber(int cageNumber) {
            this.cageNumber = cageNumber;
            return this;
        }

        public Builder copy(Dog d) {
            this.dogId = d.getDogId();
            this.name = d.getName();
            this.size = d.getSize();
            this.age = d.getAge();
            this.gender = d.getGender();
            this.breed = d.getBreed();
            this.cageNumber = d.getCageNumber();
            return this;
        }

        public Dog build() {
            return new Dog(this);
        }
    }

}
