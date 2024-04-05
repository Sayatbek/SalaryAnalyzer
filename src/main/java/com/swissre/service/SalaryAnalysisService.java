package com.swissre.service;

import com.swissre.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class provides services for analyzing the salaries and reporting lines of employees within an organization.
 * It aims to identify discrepancies in manager salaries and report on excessively long reporting lines.
 */
public class SalaryAnalysisService {

    private static final double MINIMUM_SALARY_MULTIPLIER = 1.20;
    private static final double MAXIMUM_SALARY_MULTIPLIER = 1.50;
    private static final int MAX_REPORTING_LINE_DEPTH = 4;

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
    public void analyzeSalariesOfManagers() {
        Map<Integer, List<Employee>> managersToSubordinates = employees.stream()
                .filter(e -> e.getManagerId() != null)
                .collect(Collectors.groupingBy(Employee::getManagerId));

        managersToSubordinates.forEach((managerId, subordinates) -> {
            Employee manager = findEmployeeById(managerId);

            if (manager == null) {
                System.out.println("Manager with ID " + managerId + " not found.");
                return;
            }

            double averageSubordinateSalary = calculateAverageSalary(subordinates);

            double minimumExpectedSalary = averageSubordinateSalary * MINIMUM_SALARY_MULTIPLIER;
            double maximumExpectedSalary = averageSubordinateSalary * MAXIMUM_SALARY_MULTIPLIER;

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
    public void analyzeDepthOfReportingLines() {
        employees.forEach(employee -> {
            int depth = calculateReportingLineDepth(employee);

            if (depth > MAX_REPORTING_LINE_DEPTH) {
                System.out.println(employee + " has a reporting line that is too long by " + (depth - MAX_REPORTING_LINE_DEPTH));
            }
        });
    }

    private Employee findEmployeeById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    private int calculateReportingLineDepth(Employee employee) {
        int depth = 0;
        Integer managerId = employee.getManagerId();
        while (managerId != null) {
            Employee manager = findEmployeeById(managerId);

            if (manager == null) {
                System.out.println("Manager with ID " + managerId + " not found.");
                break;
            }

            managerId = manager.getManagerId();
            depth++;
        }
        return depth;
    }
}