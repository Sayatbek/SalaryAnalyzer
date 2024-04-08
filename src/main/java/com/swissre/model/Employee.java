package com.swissre.model;

import java.util.Objects;

/**
 * Represents an employee in the organization.
 * This class stores basic employee information including their ID, name, salary, and manager.
 */
public class Employee {
    /**
     * Unique identifier for the employee.
     */
    private final int id;

    /**
     * First name of the employee.
     */
    private final String firstName;

    /**
     * Last name of the employee.
     */
    private final String lastName;

    /**
     * Salary of the employee.
     */
    private final double salary;

    /**
     * ID of the employee's manager. This field is nullable indicating that if null, the employee does not have a manager (e.g., CEO).
     */
    private final Integer managerId; // Nullable for the CEO, who has no manager

    /**
     * Constructs a new Employee instance.
     *
     * @param id         Unique identifier for the employee.
     * @param firstName  First name of the employee.
     * @param lastName   Last name of the employee.
     * @param salary     Salary of the employee.
     * @param managerId  ID of the employee's manager. Can be null if the employee has no manager.
     */
    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    /**
     * Gets the employee's ID.
     *
     * @return An integer representing the unique identifier for the employee.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the employee's firstName.
     *
     * @return An String representing the firstName for the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the employee's lastName.
     *
     * @return An String representing the lastName for the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the employee's salary.
     *
     * @return A double value representing the employee's salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets the manager ID for the employee.
     *
     * @return An integer representing the ID of the employee's manager, or null if the employee is at the top of the hierarchy.
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * Returns a string representation of the employee, including id, first name, last name, salary, and managerId.
     *
     * @return A string representation of the employee.
     */
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %.2f, %s", id, firstName, lastName, salary, managerId == null ? "" : managerId.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, managerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id &&
                Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(managerId, employee.managerId);
    }
}