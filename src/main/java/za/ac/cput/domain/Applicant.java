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
    @JoinColumn(name = "dog_id", nullable = true)
    protected Dog dog;

    @OneToOne
    @JoinColumn(name = "cat_id", nullable = true)
    protected Cat cat;

    protected  Applicant()
    {
    }

    private Applicant(Builder builder)
    {
        this.id = builder.id;
        this.applicationDate = builder.applicationDate;
        this.applicationStatus = builder.applicationStatus;
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

    public Dog getDog() { return dog; }

    public Cat getCat() { return cat; }

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
                getDog(),
                getCat()
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
                Objects.equals(getDog(), applicant.getDog()) &&
                Objects.equals(getCat(), applicant.getCat());
    }

    @Override
    public String toString ()
    {
        return "IncidentReport{" +
                "id=" + id + "," +
                "applicationDate=" + applicationDate + "," +
                "applicationStatus='" + applicationStatus + "'," +
                "petOwner=" + petOwner + "," +
                "dog=" + dog + "," +
                "cat=" + cat +
                "}";
    }

    public static class Builder
    {
        private Long id;
        private LocalDate applicationDate;
        private String applicationStatus;
        private PetOwner petOwner;
        private Dog dog;
        private Cat cat;

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

        public Builder setDog(Dog dog) {
            this.dog = dog;
            return this;
        }

        public Builder setCat(Cat cat) {
            this.cat = cat;
            return this;
        }

        public Builder copy (Applicant applicant)
        {
            this.id = applicant.id;
            this.applicationDate = applicant.applicationDate;
            this.applicationStatus = applicant.applicationStatus;
            this.petOwner = applicant.petOwner;
            this.dog = applicant.dog;
            this.cat = applicant.cat;
            return this;
        }

        public Applicant build()
        {
            return new Applicant(this);
        }
    }
}
