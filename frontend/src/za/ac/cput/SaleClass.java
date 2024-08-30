package za.ac.cput;

public class SaleClass {

    private String id;
    private ApplicantClass applicant;
    private String saleDate;
    private EmployeeClass employee;
    private String amount;

    // Default constructor
    public SaleClass() {
    }

    // Parameterized constructor
    public SaleClass(String id, ApplicantClass applicant, String saleDate, EmployeeClass employee, String amount) {
        this.id = id;
        this.applicant = applicant;
        this.saleDate = saleDate;
        this.employee = employee;
        this.amount = amount;
    }

    // Getter and Setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for applicant
    public ApplicantClass getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantClass applicant) {
        this.applicant = applicant;
    }

    // Getter and Setter for saleDate
    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    // Getter and Setter for employee
    public EmployeeClass getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeClass employee) {
        this.employee = employee;
    }

    // Getter and Setter for amount
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SaleClass [id=" + id + ", applicant=" + applicant + ", saleDate=" + saleDate + ", employee=" + employee + ", amount=" + amount + "]";
    }
}
