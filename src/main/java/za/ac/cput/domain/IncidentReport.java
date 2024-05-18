package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class IncidentReport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "animalCode")
    private Animal animal;
    private String incidentType; // Attack, Injury, escaped, or something similar that summarizes it in one word
    private String description;
    private LocalDateTime incidentDate;
    private String actionsTaken;

    // Potentially make this a Volunteer, Staff member or Person once everyones code is merged
    // Or suggest to the group to make an abstract Person class from which the others ingherit
    private String reportedBy;

    protected  IncidentReport()
    {
    }

    private IncidentReport(Builder builder)
    {
        this.id = builder.id;
        this.animal = builder.animal;
        this.incidentType = builder.incidentType;
        this.incidentDate = builder.incidentDate;
        this.description = builder.description;
        this.actionsTaken = builder.actionsTaken;
        this.reportedBy = builder.reportedBy;
    }

    public Long getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getIncidentDate() {
        return incidentDate;
    }

    public String getActionsTaken() {
        return actionsTaken;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(
                getId(),
                getAnimal(),
                getIncidentType(),
                getDescription(),
                getIncidentDate(),
                getActionsTaken(),
                getReportedBy()
        );
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof IncidentReport that))
            return false;

        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAnimal(), that.getAnimal()) &&
                Objects.equals(getIncidentType(), that.getIncidentType()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getIncidentDate(), that.getIncidentDate()) &&
                Objects.equals(getActionsTaken(), that.getActionsTaken()) &&
                Objects.equals(getReportedBy(), that.getReportedBy());
    }

    @Override
    public String toString ()
    {
        return "IncidentReport{" +
                "id=" + id +
                "animal=" + animal.toString() +
                "incidentType='" + incidentType + '\'' +
                "incidentDate=" + incidentDate +
                "description='" + description + '\'' +
                "actionsTaken='" + actionsTaken + '\'' +
                "reportedBy='" + reportedBy + '\'' +
                "}";
    }

    public static class Builder
    {
        private Long id;
        private Animal animal;
        private String incidentType;
        private LocalDateTime incidentDate;
        private String description;
        private String actionsTaken;
        private String reportedBy;

        public Builder setId (Long id)
        {
            this.id = id;
            return this;
        }

        public Builder setAnimal (Animal animal)
        {
            this.animal = animal;
            return this;
        }

        public Builder setIncidentType (String incidentType)
        {
            this.incidentType = incidentType;
            return this;
        }

        public Builder setIncidentDate (LocalDateTime incidentDate)
        {
            this.incidentDate = incidentDate;
            return this;
        }

        public Builder setDescription (String description)
        {
            this.description = description;
            return this;
        }

        public Builder setActionsTaken (String actionsTaken)
        {
            this.actionsTaken = actionsTaken;
            return this;
        }

        public Builder setReportedBy (String reportedBy)
        {
            this.reportedBy = reportedBy;
            return this;
        }

        public Builder copy (IncidentReport incidentReport)
        {
            this.id = incidentReport.id;
            this.animal = incidentReport.animal;
            this.incidentType = incidentReport.incidentType;
            this.incidentDate = incidentReport.incidentDate;
            this.description = incidentReport.description;
            this.actionsTaken = incidentReport.actionsTaken;
            this.reportedBy = incidentReport.reportedBy;
            return this;
        }

        public IncidentReport build()
        {
            return new IncidentReport(this);
        }
    }
}
