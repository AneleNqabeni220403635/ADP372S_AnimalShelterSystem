package za.ac.cput.factory;

import za.ac.cput.domain.Employee;
import za.ac.cput.util.Helper;

public class EmployeeFactory {
    public static Employee buildEmployee(long employeeId, String firstName, String lastName, String contactNo, String emailAddress) {
        if (Helper.isNullorZero(employeeId) || Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(contactNo) || Helper.isNullorEmpty(emailAddress)) {
            return null;
        }

        return new Employee.Builder()
                .setId(employeeId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContactNo(contactNo)
                .setEmailAddress(emailAddress)
                .build();
    }

    public static Employee buildEmployee(String firstName, String lastName, String contactNo, String emailAddress) {
        if (Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(contactNo) || Helper.isNullorEmpty(emailAddress)) {
            return null;
        }

        long employeeId = Helper.generateEmployeeId();

        return new Employee.Builder()
                .setId(employeeId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContactNo(contactNo)
                .setEmailAddress(emailAddress)
                .build();
    }
}

