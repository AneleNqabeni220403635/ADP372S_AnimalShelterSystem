package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Applicant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "petOwner_id", nullable = false)
    protected PetOwner petOwner;

    protected LocalDate applicationDate;

    @OneToOne
    @JoinColumn(name = "dog_id")
    protected Dog dogId;

    @OneToOne
    @JoinColumn(name = "cat_id")
    protected Cat catId;

    protected String status;

    public Applicant()
    {
    }

    private Applicant(Builder builder)
    {
        this.id = builder.id;
        this.petOwner = builder.petOwner;
        this.applicationDate = builder.applicationDate;
        this.dogId = builder.dogId;
        this.catId = builder.catId;
        this.status = builder.status;
    }

    public Long getId()
    {
        return id;
    }

    public PetOwner getPetOwner()
    {
        return petOwner;
    }

    public LocalDate getApplicationDate()
    {
        return applicationDate;
    }

    public Dog getDogId()
    {
        return dogId;
    }

    public Cat getCatId()
    {
        return catId;
    }

    public String getStatus()
    {
        return status;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof Applicant applicant))
            return false;

        return Objects.equals(getId(), applicant.getId()) &&
                Objects.equals(getPetOwner(), applicant.getPetOwner()) &&
                Objects.equals(getApplicationDate(), applicant.getApplicationDate()) &&
                Objects.equals(getDogId(), applicant.getDogId()) &&
                Objects.equals(getCatId(), applicant.getCatId()) &&
                Objects.equals(getStatus(), applicant.getStatus());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getPetOwner(), getApplicationDate(), getDogId(), getCatId(), getStatus());
    }

    @Override
    public String toString()
    {
        return "Applicant{" +
                "id=" + id +
                ", petOwner=" + petOwner +
                ", applicationDate=" + applicationDate +
                ", dogId=" + dogId +
                ", catId=" + catId +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private PetOwner petOwner;
        private LocalDate applicationDate;
        private Dog dogId;
        private Cat catId;
        private String status;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPetOwner(PetOwner petOwner) {
            this.petOwner = petOwner;
            return this;
        }

        public Builder setApplicationDate(LocalDate applicationDate) {
            this.applicationDate = applicationDate;
            return this;
        }

        public Builder setDogId(Dog dogId) {
            this.dogId = dogId;
            return this;
        }

        public Builder setCatId(Cat catId) {
            this.catId = catId;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Applicant a) {
            this.id = a.getId();
            this.petOwner = a.getPetOwner();
            this.applicationDate = a.getApplicationDate();
            this.dogId = a.getDogId();
            this.catId = a.getCatId();
            this.status = a.getStatus();
            return this;
        }

        public Applicant build() {
            return new Applicant(this);
        }
    }
}
