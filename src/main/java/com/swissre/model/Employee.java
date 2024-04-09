package com.swissre.model;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents an employee in the organization.
 * This class stores basic employee information including their ID, name, lastname, salary.
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
     * Id of the manager.
     */
    private final Optional<Integer> managerId;

    /**
     * Constructs a new Employee instance.
     *
     * @param id        Unique identifier for the employee.
     * @param firstName First name of the employee.
     * @param lastName  Last name of the employee.
     * @param salary    Salary of the employee.
     */
    public Employee(int id, String firstName, String lastName, double salary, Optional<Integer> managerId) {
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
     * Gets the employee's managerId.
     *
     * @return An Integer representing the managerId for the employee.
     */
    public Optional<Integer> getManagerId() {
        return managerId;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %.2f", id, firstName, lastName, salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id &&
                Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }
}