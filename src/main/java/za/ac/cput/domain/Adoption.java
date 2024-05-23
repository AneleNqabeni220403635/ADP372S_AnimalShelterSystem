package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adoptionId;
    private String applicantName;
    private LocalDate applicationDate;
    private String  status;
    @ManyToOne(cascade = CascadeType.ALL)
    private Animal animal;

    protected Adoption() {
    }

    private Adoption(Builder builder) {
        this.adoptionId = builder.adoptionId;
        this.applicantName = builder.applicantName;
        this.applicationDate = builder.applicationDate;
        this.status = builder.status;
        this.animal = builder.animal;
    }

    public Long getAdoptionId() {
        return adoptionId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adoption adoption)) return false;
        return Objects.equals(getAdoptionId(), adoption.getAdoptionId()) && Objects.equals(getApplicantName(), adoption.getApplicantName()) && Objects.equals(getApplicationDate(), adoption.getApplicationDate()) && getStatus() == adoption.getStatus() && Objects.equals(getAnimal(), adoption.getAnimal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdoptionId(), getApplicantName(), getApplicationDate(), getStatus(), getAnimal().getAnimalCode());
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "adoptionId=" + adoptionId +
                ", applicantName='" + applicantName + '\'' +
                ", applicationDate=" + applicationDate +
                ", status=" + status +
                ", animal=" + animal +
                '}';
    }

    public static class Builder {
        private Long adoptionId;
        private String applicantName;
        private LocalDate applicationDate;
        private String  status;
        private Animal animal;

        public Builder() {
        }

        public Builder copy(Adoption a) {
            this.adoptionId = a.getAdoptionId();
            this.applicantName = a.getApplicantName();
            this.applicationDate = a.getApplicationDate();
            this.status = a.getStatus();
            this.animal = a.getAnimal();
            return this;
        }

        public Builder setAdoptionId(Long adoptionId) {
            this.adoptionId = adoptionId;
            return this;
        }

        public Builder setApplicantName(String applicantName) {
            this.applicantName = applicantName;
            return this;
        }

        public Builder setApplicationDate(LocalDate applicationDate) {
            this.applicationDate = applicationDate;
            return this;
        }

        public Builder setStatus(String  status) {
            this.status = status;
            return this;
        }

        public Builder setAnimal(Animal animal) {
            this.animal = animal;
            return this;
        }

        public Adoption build() {
            return new Adoption(this);
        }
    }
}