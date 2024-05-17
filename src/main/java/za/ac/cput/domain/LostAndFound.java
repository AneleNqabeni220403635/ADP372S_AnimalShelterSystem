package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class LostAndFound
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String species;
    private String breed;
    private String description;
    private LocalDateTime reportedDate;
    private String status; // Lost, found or reunited
    private String reporterContactName;
    private String reporterContactNumber;

    public Long getId()
    {
        return id;
    }

    public String getSpecies()
    {
        return species;
    }

    public String getBreed()
    {
        return breed;
    }

    public String getDescription()
    {
        return description;
    }

    public LocalDateTime getReportedDate()
    {
        return reportedDate;
    }

    public String getStatus()
    {
        return status;
    }

    public String getReporterContactName()
    {
        return reporterContactName;
    }

    public String getReporterContactNumber()
    {
        return reporterContactNumber;
    }

    protected LostAndFound()
    {
    }

    private LostAndFound(Builder builder)
    {
        this.id = builder.id;
        this.species = builder.species;
        this.breed = builder.breed;
        this.description = builder.description;
        this.reportedDate = builder.reportedDate;
        this.status = builder.status;
        this.reporterContactName = builder.reporterContactName;
        this.reporterContactNumber = builder.reporterContactNumber;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(
                getId(),
                getSpecies(),
                getBreed(),
                getDescription(),
                getReportedDate(),
                getStatus(),
                getReporterContactName(),
                getReporterContactNumber()
        );
    }

    @Override
    public String toString()
    {
        return "LostAndFound{" +
                "id=" + id +
                "species='" + species + '\'' +
                "breed='" + breed + '\'' +
                "description='" + description + '\'' +
                "reportedDate=" + reportedDate +
                "reporterContactName='" + reporterContactName + '\'' +
                "reporterContactNumber='" + reporterContactNumber + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof LostAndFound that))
            return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSpecies(), that.getSpecies()) &&
                Objects.equals(getBreed(), that.getBreed()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getReportedDate(), that.getReportedDate()) &&
                Objects.equals(getReporterContactName(), that.getReporterContactName()) &&
                Objects.equals(getReporterContactNumber(), that.getReporterContactNumber());
    }

    public static class Builder
    {
        private Long id;
        private String species;
        private String breed;
        private String description;
        private LocalDateTime reportedDate;
        private String status;
        private String reporterContactName;
        private String reporterContactNumber;

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder setSpecies(String species)
        {
            this.species = species;
            return this;
        }

        public Builder setBreed(String breed)
        {
            this.breed = breed;
            return this;
        }

        public Builder setDescription(String description)
        {
            this.description = description;
            return this;
        }

        public Builder setReportedDate(LocalDateTime reportedDate)
        {
            this.reportedDate = reportedDate;
            return this;
        }

        public Builder setStatus(String status)
        {
            this.status = status;
            return this;
        }

        public Builder setReporterContactName(String reporterContactName)
        {
            this.reporterContactName = reporterContactName;
            return this;
        }

        public Builder setReporterContactNumber(String reporterContactNumber)
        {
            this.reporterContactNumber = reporterContactNumber;
            return this;
        }

        public Builder copy (LostAndFound lostAndFound)
        {
            this.id = lostAndFound.id;
            this.species = lostAndFound.species;
            this.breed = lostAndFound.breed;
            this.description = lostAndFound.description;
            this.reportedDate = lostAndFound.reportedDate;
            this.status = lostAndFound.status;
            this.reporterContactName = lostAndFound.reporterContactName;
            this.reporterContactNumber = lostAndFound.reporterContactNumber;
            return this;
        }

        public LostAndFound build ()
        {
            return new LostAndFound(this);
        }
    }
}
