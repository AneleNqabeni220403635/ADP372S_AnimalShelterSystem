package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Applicant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate applicationDate;

    private String applicationStatus;

    @ManyToOne
    @JoinColumn(name = "petOwner_id", nullable = false)
    protected PetOwner petOwner;

    @OneToOne
    @JoinColumn(name = "dog_id")
    protected Dog dogId;

    @OneToOne
    @JoinColumn(name = "cat_id")
    protected Cat catId;

    protected  Applicant()
    {
    }

    private Applicant(Builder builder)
    {
        this.id = builder.id;
        this.applicationDate = builder.applicationDate;
        this.applicationStatus = builder.applicationStatus;
        this.petOwner = builder.petOwner;
        this.dogId = builder.dogId;
        this.catId = builder.catId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public PetOwner getPetOwner() { return petOwner; }

    public Dog getDogId() { return dogId; }

    public Cat getCatId() { return catId; }

    // ever seen the show CatDog? I get the theme song stuck in my head everytime i see these classes
    // https://www.youtube.com/watch?v=QSFj4vEDZQw
    // enjoy

    @Transient
    public static String[] Statuses =  {
            "Application_Submitted",
            "Application_Review",
            "Background_Check",
            "PreAdoption_Interview",
            "Home_Visit_Scheduled",
            "Home_Visit_Completed",
            "Approved",
            "Meet_Greet_Scheduled",
            "Adoption_Pending",
            "Adoption_Finalized",
            "Adoption_Denied",
            "On_Hold",
            "Withdrawn",
            "Followup_Scheduled"
    };

    @Override
    public int hashCode ()
    {
        return Objects.hash(
                getId(),
                getApplicationDate(),
                getApplicationStatus(),
                getPetOwner(),
                getDogId(),
                getCatId()
        );
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Applicant applicant))
            return false;

        return Objects.equals(getId(), applicant.getId()) &&
                Objects.equals(getApplicationDate(), applicant.getApplicationDate()) &&
                Objects.equals(getApplicationStatus(), applicant.getApplicationStatus()) &&
                Objects.equals(getPetOwner(), applicant.getPetOwner()) &&
                Objects.equals(getDogId(), applicant.getDogId()) &&
                Objects.equals(getCatId(), applicant.getCatId());
    }

    @Override
    public String toString ()
    {
        return "Applicant{" +
                "id=" + id + "," +
                "applicationDate=" + applicationDate + "," +
                "applicationStatus='" + applicationStatus + "'," +
                "petOwner=" + petOwner + "," +
                "dog=" + dogId.getDogId() + "," +
                "cat=" + catId.getCatId() +
                "}";
    }

    public static class Builder
    {
        private Long id;
        private LocalDate applicationDate;
        private String applicationStatus;
        private PetOwner petOwner;
        private Dog dogId;
        private Cat catId;

        public Builder setId (Long id)
        {
            this.id = id;
            return this;
        }

        public Builder setApplicationDate (LocalDate applicationDate)
        {
            this.applicationDate = applicationDate;
            return this;
        }

        public Builder setApplicationStatus (String applicationStatus)
        {
            this.applicationStatus = applicationStatus;
            return this;
        }

        public Builder setPetOwner(PetOwner petOwner) {
            this.petOwner = petOwner;
            return this;
        }

        public Builder setDog(Dog dogId) {
            this.dogId = dogId;
            return this;
        }

        public Builder setCat(Cat catId) {
            this.catId = catId;
            return this;
        }

        public Builder copy (Applicant applicant)
        {
            this.id = applicant.id;
            this.applicationDate = applicant.applicationDate;
            this.applicationStatus = applicant.applicationStatus;
            this.petOwner = applicant.petOwner;
            this.dogId = applicant.getDogId();
            this.catId = applicant.getCatId();
            return this;
        }

        public Applicant build()
        {
            return new Applicant(this);
        }
    }
}
