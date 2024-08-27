package za.ac.cput.domain;

import jakarta.persistence.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

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
                getApplicationStatus()
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
                Objects.equals(getApplicationStatus(), applicant.getApplicationStatus());
    }

    @Override
    public String toString ()
    {
        return "IncidentReport{" +
                "id=" + id + "'," +
                "applicationDate=" + applicationDate + "'," +
                "applicationStatus='" + applicationStatus + "'," +
                "}";
    }

    public static class Builder
    {
        private Long id;
        private LocalDate applicationDate;
        private String applicationStatus;

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

        public Builder copy (Applicant applicant)
        {
            this.id = applicant.id;
            this.applicationDate = applicant.applicationDate;
            this.applicationStatus = applicant.applicationStatus;
            return this;
        }

        public Applicant build()
        {
            return new Applicant(this);
        }
    }
}
