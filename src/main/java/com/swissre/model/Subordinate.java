package com.swissre.model;

import java.util.Objects;

/**
 * Represents an employee with manager to report in the organization.
 * This class stores basic employee information including their ID, name, lastname, salary.
 */
public class Subordinate extends Employee {
    /**
     * ID of the employee's manager.
     */
    private final int managerId;

    /**
     * Constructs a new Subordinate instance.
     *
     * @param id        Unique identifier for the employee.
     * @param firstName First name of the employee.
     * @param lastName  Last name of the employee.
     * @param salary    Salary of the employee.
     * @param managerId ID of the employee's manager.
     */
    public Subordinate(int id, String firstName, String lastName, double salary, int managerId) {
        super(id, firstName, lastName, salary);
        this.managerId = managerId;
    }

    /**
     * Gets the manager ID for the subordinate.
     *
     * @return An integer representing the ID of the subordinate's manager.
     */
    public int getManagerId() {
        return managerId;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %.2f, %d", id, firstName, lastName, salary, managerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, managerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subordinate subordinate = (Subordinate) obj;
        return id == subordinate.id &&
                Double.compare(subordinate.salary, salary) == 0 &&
                Objects.equals(firstName, subordinate.firstName) &&
                Objects.equals(lastName, subordinate.lastName) &&
                Objects.equals(managerId, subordinate.managerId);
    }
}
