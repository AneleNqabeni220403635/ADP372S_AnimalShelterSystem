package za.ac.cput.factory;

import za.ac.cput.domain.Applicant;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Sale;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class SaleFactory {

    public static Sale buildSale(Long id, Applicant applicant, Employee employee, LocalDate saleDate, double price) {
        if (Helper.isNullorZero(id) || applicant == null || employee == null || saleDate == null || price <= 0) {
            return null;
        }

        return new Sale.Builder()
                .setId(id)
                .setApplicant(applicant)
                .setEmployee(employee)
                .setSaleDate(saleDate)
                .setPrice(price)
                .build();
    }

    public static Sale buildSale(Applicant applicant, Employee employee, LocalDate saleDate, double price) {
        if (applicant == null || employee == null || saleDate == null || price <= 0) {
            return null;
        }

        Long id = Helper.generateSaleId();

        return new Sale.Builder()
                .setId(id)
                .setApplicant(applicant)
                .setEmployee(employee)
                .setSaleDate(saleDate)
                .setPrice(price)
                .build();
    }
}

