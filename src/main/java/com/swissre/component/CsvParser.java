package com.swissre.component;

import com.swissre.model.Employee;
import com.swissre.model.Subordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for parsing CSV data into Employee objects.
 */
public class CsvParser {
    private static final int ID_POSITION = 0;
    private static final int FIRST_NAME_POSITION = 1;
    private static final int LAST_NAME_POSITION = 2;
    private static final int SALARY_POSITION = 3;
    private static final int MANAGER_ID_POSITION = 4;

    /**
     * Parses a CSV from a Reader and returns a list of Employee objects.
     * It catches specific exceptions like IOException.
     * It uses Apache Commons CSV for parsing CSV data.
     *
     * @param reader the Reader to parse.
     * @return a list of Employee objects parsed from the CSV.
     */
    public List<Employee> parse(Reader reader) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Skip the header row
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                populateEmployeesList(values, employees);
            }
        } catch (IOException ioException) {
            System.err.printf("Failed to read CSV data: %s%n", ioException);
        }
        return employees;
    }

    private void populateEmployeesList(String[] values, List<Employee> employees) {
        try {
            Employee employee = mapValuesToEmployee(values);
            employees.add(employee);
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.err.printf("Invalid CSV data: %s%n", arrayIndexOutOfBoundsException);
        } catch (NumberFormatException numberFormatException) {
            System.err.printf("Failed to parse number from line: %s%n", numberFormatException);
        }
    }

    private Employee mapValuesToEmployee(String[] values) {
        int id = Integer.parseInt(values[ID_POSITION].trim());
        String firstName = values[FIRST_NAME_POSITION].trim();
        String lastName = values[LAST_NAME_POSITION].trim();
        double salary = Double.parseDouble(values[SALARY_POSITION].trim());

        Employee employee;
        if (values.length > MANAGER_ID_POSITION) {
            int managerId = Integer.parseInt(values[MANAGER_ID_POSITION].trim());
            employee = new Subordinate(id, firstName, lastName, salary, managerId);
        } else {
            employee = new Employee(id, firstName, lastName, salary);
        }
        return employee;
    }
}