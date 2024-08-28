package za.ac.cput.domain;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    protected Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    protected Employee employee;


    protected LocalDate saleDate;
    protected double price;

    protected Sale() {
    }

    private Sale(Builder builder) {
        this.id = builder.id;
        this.applicant=builder.applicant;
        this.employee=builder.employee;
        this.saleDate = builder.saleDate;
        this.price = builder.price;
    }

    public Long getId() {
        return id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale sale)) return false;
        return Double.compare(sale.getPrice(), getPrice()) == 0 &&
                Objects.equals(getId(), sale.getId()) &&
                Objects.equals(getApplicant(), sale.getApplicant()) &&
                Objects.equals(getEmployee(), sale.getEmployee()) &&
                Objects.equals(getSaleDate(), sale.getSaleDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getApplicant(), getEmployee(), getSaleDate(), getPrice());
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", applicant=" + applicant +
                ", employee=" + employee +
                ", saleDate=" + saleDate +
                ", price=" + price +
                '}';
    }

    public static class Builder {
        private Long id;
        private Applicant applicant;
        private Employee employee;
        private LocalDate saleDate;
        private double price;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        public Builder setApplicant(Applicant applicant) {
            this.applicant = applicant;
            return this;
        }
        public Builder setEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }
        public Builder setSaleDate(LocalDate saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder copy(Sale s) {
            this.id = s.getId();
            this.applicant = s.getApplicant();
            this.employee = s.getEmployee();
            this.saleDate = s.getSaleDate();
            this.price = s.getPrice();
            return this;
        }

        public Sale build() {
            return new Sale(this);
        }
    }
}

