package za.ac.cput.factory;

import za.ac.cput.domain.Staff;

import java.time.LocalDate;

public class StaffFactory {

    public static Staff createStaff(long staffId, String name, String position, double salary, LocalDate hireDate,
                                    String responsibility, String performanceEvaluation) {
        return new Staff.Builder()
                .setStaffId(staffId)
                .setName(name)
                .setPosition(position)
                .setSalary(salary)
                .setHireDate(hireDate)
                .setResponsibility(responsibility)
                .setPerformanceEvaluation(performanceEvaluation)
                .build();
    }
}
