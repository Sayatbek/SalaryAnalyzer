package com.swissre.model;

public class Employee {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final double salary;
    private final Integer managerId; // Nullable for the CEO, who has no manager

    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    // toString method for easy logging/debugging
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %.2f, %s", id, firstName, lastName, salary, managerId == null ? "" : managerId.toString());
    }
}