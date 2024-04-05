package com.swissre.service;

import com.swissre.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides services for analyzing the salaries and reporting lines of employees within an organization.
 * This service aims to identify discrepancies in manager salaries and report on excessively long reporting lines.
 */
public class SalaryAnalysisService {

    /**
     * List of all employees within the organization.
     */
    private final List<Employee> employees;

    /**
     * Constructs a new SalaryAnalysisService with a provided list of employees.
     *
     * @param employees The complete list of employees to be analyzed.
     */
    public SalaryAnalysisService(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Analyzes each manager's salary to determine if they earn less or more than the expected range based on
     * their direct subordinates' average salaries. It prints out managers who do not meet the salary criteria.
     * <p>
     * A manager's salary is considered below expectation if it's less than 20% more than the average salary of
     * their direct subordinates, and above expectation if it's more than 50% more than that average.
     */
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

    /**
     * Identifies employees with reporting lines longer than four managers and prints out these employees along
     * with the extent to which their reporting line exceeds this threshold.
     * <p>
     * A reporting line is considered too long if there are more than four managers between the employee and the CEO.
     */
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