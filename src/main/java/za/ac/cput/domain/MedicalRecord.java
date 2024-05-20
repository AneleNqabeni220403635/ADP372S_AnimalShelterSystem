package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MedicalRecord extends Animal {
    @Id
    private Long animal;
private LocalDate VaccinationDate;
private String medication;
    private String behaviour;
    private LocalDate nextCheckup;

protected MedicalRecord(){

}
private MedicalRecord(Builder builder){
    this.animal = builder.animal;
    this.VaccinationDate = builder.VaccinationDate;
    this.medication = builder.medication;
    this.behaviour = builder.behaviour;
    this.nextCheckup = builder.nextCheckup;
}

    public Long getAnimal() {
        return animal;
    }

    public LocalDate getVaccinationDate() {
        return VaccinationDate;
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
        return Objects.equals(getAnimal(), that.getAnimal()) && Objects.equals(getVaccinationDate(), that.getVaccinationDate()) && Objects.equals(getMedication(), that.getMedication()) && Objects.equals(getBehaviour(), that.getBehaviour()) && Objects.equals(getNextCheckup(), that.getNextCheckup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimal(), getVaccinationDate(), getMedication(), getBehaviour(), getNextCheckup());
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "animal=" + animal +
                ", VaccinationDate=" + VaccinationDate +
                ", medication='" + medication + '\'' +
                ", behaviour='" + behaviour + '\'' +
                ", nextCheckup=" + nextCheckup +
                '}';
    }
    public static class Builder{
        private Long animal;
        private LocalDate VaccinationDate;
        private String medication;
        private String behaviour;
        private LocalDate nextCheckup;

        public Builder(){

        }
        public Builder setAnimal(Long animal) {
            this.animal = animal;
            return this;
        }

        public Builder setVaccinationDate(LocalDate vaccinationDate) {
            VaccinationDate = vaccinationDate;
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
        public Builder copy(MedicalRecord m){
            this.animal = m.animal;
             this.VaccinationDate = m.VaccinationDate;
             this.medication = m.medication;
              this.behaviour = m.behaviour;
             this.nextCheckup = m.nextCheckup;
             return this;
        }
        public MedicalRecord build() {
            return new MedicalRecord(this);
        }
    }
}
