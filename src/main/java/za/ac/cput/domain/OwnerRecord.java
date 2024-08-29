package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class OwnerRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    protected Dog dog;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    protected Cat cat;

    @ManyToOne
    @JoinColumn(name = "petOwner_id", nullable = false)
    protected PetOwner petOwner;

    protected LocalDate takenDate;
    protected LocalDate returnDate;

    protected OwnerRecord() {
    }

    private OwnerRecord(Builder builder) {
        this.id = builder.id;
        this.dog = builder.dog;
        this.cat = builder.cat;
        this.petOwner = builder.petOwner;
        this.takenDate = builder.takenDate;
        this.returnDate = builder.returnDate;
    }

    public Long getId() {
        return id;
    }

    public Dog getDog() {
        return dog;
    }

    public Cat getCat() {
        return cat;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public LocalDate getTakenDate() {
        return takenDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerRecord that)) return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDog(), that.getDog()) &&
                Objects.equals(getCat(), that.getCat()) &&
                Objects.equals(getPetOwner(), that.getPetOwner()) &&
                Objects.equals(getTakenDate(), that.getTakenDate()) &&
                Objects.equals(getReturnDate(), that.getReturnDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDog(), getCat(), getPetOwner(), getTakenDate(), getReturnDate());
    }

    @Override
    public String toString() {
        return "OwnerRecord{" +
                "id=" + id +
                ", dog=" + dog +
                ", cat=" + cat +
                ", petOwner=" + petOwner +
                ", takenDate=" + takenDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public static class Builder {
        private Long id;
        private Dog dog;
        private Cat cat;
        private PetOwner petOwner;
        private LocalDate takenDate;
        private LocalDate returnDate;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDog(Dog dog) {
            this.dog = dog;
            return this;
        }

        public Builder setCat(Cat cat) {
            this.cat = cat;
            return this;
        }

        public Builder setPetOwner(PetOwner petOwner) {
            this.petOwner = petOwner;
            return this;
        }

        public Builder setTakenDate(LocalDate takenDate) {
            this.takenDate = takenDate;
            return this;
        }

        public Builder setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder copy(OwnerRecord or) {
            this.id = or.getId();
            this.dog = or.getDog();
            this.cat = or.getCat();
            this.petOwner = or.getPetOwner();
            this.takenDate = or.getTakenDate();
            this.returnDate = or.getReturnDate();
            return this;
        }

        public OwnerRecord build() {
            return new OwnerRecord(this);
        }
    }
}
