package za.ac.cput.domain;

import java.util.Objects;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    protected Dog dog;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    protected Cat cat;

    protected LocalDate vaccinationDate;
    protected String medication;
    protected String behaviour;
    protected LocalDate nextCheckup;
    protected String description;



    protected MedicalRecord() {
    }

    private MedicalRecord(Builder builder) {
        this.id = builder.id;
        this.dog = builder.dog;
        this.cat=builder.cat;
        this.vaccinationDate = builder.vaccinationDate;
        this.medication = builder.medication;
        this.behaviour = builder.behaviour;
        this.nextCheckup = builder.nextCheckup;
        this.description=builder.description;
    }


    public Long getId() {
        return id;
    }

    public Cat getCat() {
        return cat;
    }

    public String getDescription() {
        return description;
    }

    public Dog getDog() {
        return dog;
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
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCat(),that.getCat()) &&
                Objects.equals(getDog(), that.getDog()) &&
                Objects.equals(getVaccinationDate(), that.getVaccinationDate()) &&
                Objects.equals(getMedication(), that.getMedication()) &&
                Objects.equals(getBehaviour(), that.getBehaviour()) &&
                Objects.equals(getNextCheckup(), that.getNextCheckup()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDog(),getCat(), getVaccinationDate(), getMedication(), getBehaviour(), getNextCheckup(),getDescription());
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", dog=" + dog +
                ", cat=" + cat +
                ", vaccinationDate=" + vaccinationDate +
                ", medication='" + medication + '\'' +
                ", behaviour='" + behaviour + '\'' +
                ", nextCheckup=" + nextCheckup +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private Dog dog;
        private Cat cat;
        private LocalDate vaccinationDate;
        private String medication;
        private String behaviour;
        private LocalDate nextCheckup;
        private String description;

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

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder copy(MedicalRecord m) {
            this.id = m.getId();
            this.dog = m.getDog();
            this.cat = m.getCat();
            this.vaccinationDate = m.getVaccinationDate();
            this.medication = m.getMedication();
            this.behaviour = m.getBehaviour();
            this.nextCheckup = m.getNextCheckup();
            this.description = m.getDescription();
            return this;
        }

        public MedicalRecord build() {
            return new MedicalRecord(this);
        }
    }
}
