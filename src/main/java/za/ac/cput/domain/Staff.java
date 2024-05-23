package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffId;
    private String name;
    private String position;
    private double salary;
    private LocalDate hireDate;
    private String responsibility;
    private String performanceEvaluation;

    protected Staff() {

    }

    private Staff(Builder builder) {
        this.staffId = builder.staffId;
        this.name = builder.name;
        this.position = builder.position;
        this.salary = builder.salary;
        this.hireDate = builder.hireDate;
        this.responsibility = builder.responsibility;
        this.performanceEvaluation = builder.performanceEvaluation;
    }

    public long getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getPerformanceEvaluation() {
        return performanceEvaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff staff)) return false;
        return Double.compare(staff.getSalary(), getSalary()) == 0 && getStaffId() == staff.getStaffId() && Objects.equals(getName(), staff.getName()) && Objects.equals(getPosition(), staff.getPosition()) && Objects.equals(getHireDate(), staff.getHireDate()) && Objects.equals(getResponsibility(), staff.getResponsibility()) && Objects.equals(getPerformanceEvaluation(), staff.getPerformanceEvaluation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaffId(), getName(), getPosition(), getSalary(), getHireDate(), getResponsibility(), getPerformanceEvaluation());
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", responsibility='" + responsibility + '\'' +
                ", performanceEvaluation='" + performanceEvaluation + '\'' +
                '}';
    }

    public static class Builder {
        private long staffId;
        private String name;
        private String position;
        private double salary;
        private LocalDate hireDate;
        private String responsibility;
        private String performanceEvaluation;

        public Builder() {
            // Default constructor
        }

        public Builder setStaffId(long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder setHireDate(LocalDate hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder setResponsibility(String responsibility) {
            this.responsibility = responsibility;
            return this;
        }

        public Builder setPerformanceEvaluation(String performanceEvaluation) {
            this.performanceEvaluation = performanceEvaluation;
            return this;
        }

        public Builder copy(Staff staff) {
            this.staffId = staff.getStaffId();
            this.name = staff.getName();
            this.position = staff.getPosition();
            this.salary = staff.getSalary();
            this.hireDate = staff.getHireDate();
            this.responsibility = staff.getResponsibility();
            this.performanceEvaluation = staff.getPerformanceEvaluation();
            return this;
        }

        public Staff build() {
            return new Staff(this);
        }
    }
}
