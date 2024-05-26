package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    protected Animal animal;
    protected LocalDate vaccinationDate;
    protected String medication;
    protected String behaviour;
    protected LocalDate nextCheckup;

    protected MedicalRecord() {
    }

    private MedicalRecord(Builder builder) {
        this.id = builder.id;
        this.animal = builder.animal;
        this.vaccinationDate = builder.vaccinationDate;
        this.medication = builder.medication;
        this.behaviour = builder.behaviour;
        this.nextCheckup = builder.nextCheckup;
    }

    public Long getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public String getMedication() {
        return medication;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public LocalDate getNextCheckup() {
        return nextCheckup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAnimal(), that.getAnimal()) && Objects.equals(getVaccinationDate(), that.getVaccinationDate()) && Objects.equals(getMedication(), that.getMedication()) && Objects.equals(getBehaviour(), that.getBehaviour()) && Objects.equals(getNextCheckup(), that.getNextCheckup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnimal(), getVaccinationDate(), getMedication(), getBehaviour(), getNextCheckup());
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", animal=" + animal +
                ", vaccinationDate=" + vaccinationDate +
                ", medication='" + medication + '\'' +
                ", behaviour='" + behaviour + '\'' +
                ", nextCheckup=" + nextCheckup +
                '}';
    }

    public static class Builder {
        private Long id;
        private Animal animal;
        private LocalDate vaccinationDate;
        private String medication;
        private String behaviour;
        private LocalDate nextCheckup;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAnimal(Animal animal) {
            this.animal = animal;
            return this;
        }

        public Builder setVaccinationDate(LocalDate vaccinationDate) {
            this.vaccinationDate = vaccinationDate;
            return this;
        }

        public Builder setMedication(String medication) {
            this.medication = medication;
            return this;
        }

        public Builder setBehaviour(String behaviour) {
            this.behaviour = behaviour;
            return this;
        }

        public Builder setNextCheckup(LocalDate nextCheckup) {
            this.nextCheckup = nextCheckup;
            return this;
        }

        public Builder copy(MedicalRecord m) {
            this.id = m.getId();
            this.animal = m.getAnimal();
            this.vaccinationDate = m.getVaccinationDate();
            this.medication = m.getMedication();
            this.behaviour = m.getBehaviour();
            this.nextCheckup = m.getNextCheckup();
            return this;
        }

        public MedicalRecord build() {
            return new MedicalRecord(this);
        }
    }
}
