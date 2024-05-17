package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responsibility;
    private Date trainingDate;
    private String performanceEvaluation;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    protected Staff() {}

    private Staff(Builder builder) {
        this.responsibility = builder.responsibility;
        this.trainingDate = builder.trainingDate;
        this.performanceEvaluation = builder.performanceEvaluation;
        this.volunteer = builder.volunteer;
    }

    public static class Builder {
        private String responsibility;
        private Date trainingDate;
        private String performanceEvaluation;
        private Volunteer volunteer;

        public Builder setResponsibility(String responsibility) {
            this.responsibility = responsibility;
            return this;
        }

        public Builder setTrainingDate(Date trainingDate) {
            this.trainingDate = trainingDate;
            return this;
        }

        public Builder setPerformanceEvaluation(String performanceEvaluation) {
            this.performanceEvaluation = performanceEvaluation;
            return this;
        }

        public Builder setVolunteer(Volunteer volunteer) {
            this.volunteer = volunteer;
            return this;
        }

        public Staff build() {
            return new Staff(this);
        }
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", responsibility='" + responsibility + '\'' +
                ", trainingDate=" + trainingDate +
                ", performanceEvaluation='" + performanceEvaluation + '\'' +
                ", volunteer=" + volunteer +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public String getPerformanceEvaluation() {
        return performanceEvaluation;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }
}
