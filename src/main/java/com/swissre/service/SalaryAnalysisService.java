package com.swissre.service;

import com.swissre.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryAnalysisService {

    private final List<Employee> employees;

    public SalaryAnalysisService(List<Employee> employees) {
        this.employees = employees;
    }

    // Identifies managers who earn less or more than they should
    public void analyzeManagerSalaries() {
        Map<Integer, List<Employee>> managersToSubordinates = employees.stream()
                .filter(e -> e.getManagerId() != null)
                .collect(Collectors.groupingBy(Employee::getManagerId));

        managersToSubordinates.forEach((managerId, subordinates) -> {
            Employee manager = employees.stream()
                    .filter(e -> e.getId() == managerId)
                    .findFirst()
                    .orElse(null);

            if (manager == null) return;

            double averageSubordinateSalary = subordinates.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            double minimumExpectedSalary = averageSubordinateSalary * 1.20;
            double maximumExpectedSalary = averageSubordinateSalary * 1.50;

            if (manager.getSalary() < minimumExpectedSalary) {
                System.out.println(manager + " earns less than they should by $" + (minimumExpectedSalary - manager.getSalary()));
            } else if (manager.getSalary() > maximumExpectedSalary) {
                System.out.println(manager + " earns more than they should by $" + (manager.getSalary() - maximumExpectedSalary));
            }
        });
    }

    // Identifies employees with reporting lines longer than 4
    public void analyzeReportingLines() {
        employees.forEach(employee -> {
            int depth = 0;
            Integer managerId = employee.getManagerId();
            while (managerId != null) {
                Integer finalManagerId = managerId;
                Employee manager = employees.stream()
                        .filter(e -> e.getId() == finalManagerId)
                        .findFirst()
                        .orElse(null);

                if (manager == null) break;

                managerId = manager.getManagerId();
                depth++;
            }

            if (depth > 4) {
                System.out.println(employee + " has a reporting line that is too long by " + (depth - 4));
            }
        });
    }
}