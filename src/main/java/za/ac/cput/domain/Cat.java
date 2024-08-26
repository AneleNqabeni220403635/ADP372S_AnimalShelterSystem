package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")

    protected long catId;
    protected String name;
    protected String breed;
    protected int age;
    protected String size;
    protected String gender;
    protected int cageNumber;

    public Cat() {
    }

    private Cat(Builder builder) {
        this.catId = builder.catId;
        this.name = builder.name;
        this.size = builder.size;
        this.age = builder.age;
        this.gender = builder.gender;
        this.breed = builder.breed;
        this.cageNumber = builder.cageNumber;

    }


    public long getCatId() {
        return catId;
    }

    public String getSize() {
        return size;
    }

    public String getGender() {
        return gender;
    }

    public int getCageNumber() {
        return cageNumber;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat cat)) return false;
        return getAge() == cat.getAge() &&
                getCageNumber() == cat.getCageNumber() &&
                Objects.equals(getCatId(), cat.getCatId()) &&
                Objects.equals(getName(), cat.getName()) &&
                Objects.equals(getSize(), cat.getSize()) &&
                Objects.equals(getGender(), cat.getGender()) &&
                Objects.equals(getBreed(), cat.getBreed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCatId(), getName(), getSize(), getAge(), getGender(), getBreed(), getCageNumber());
    }


    @Override
    public String toString() {
        return "Cat{" +
                "catId=" + catId +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", breed='" + breed + '\'' +
                ", cageNumber=" + cageNumber +
                '}';
    }

    public static class Builder {
        protected Long catId;
        protected String name;
        protected String size;
        protected int age;
        protected String gender;
        protected String breed;
        protected int cageNumber;

        public Builder() {
        }

        public Cat.Builder setCatId(Long catId) {
            this.catId = catId;
            return this;
        }

        public Cat.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Cat.Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Cat.Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Cat.Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Cat.Builder setBreed(String breed) {
            this.breed = breed;
            return this;
        }

        public Cat.Builder setCageNumber(int cageNumber) {
            this.cageNumber = cageNumber;
            return this;
        }

        public Cat.Builder copy(Cat d) {
            this.catId = d.getCatId();
            this.name = d.getName();
            this.size = d.getSize();
            this.age = d.getAge();
            this.gender = d.getGender();
            this.breed = d.getBreed();
            this.cageNumber = d.getCageNumber();
            return this;
        }

        public Cat build() {
            return new Cat(this);
        }
    }
}
