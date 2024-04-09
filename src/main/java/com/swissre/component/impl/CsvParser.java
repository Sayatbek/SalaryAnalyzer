package com.swissre.component.impl;

import com.swissre.component.Parser;
import com.swissre.exception.ServiceException;
import com.swissre.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for parsing CSV data into Employee objects.
 */
public class CsvParser implements Parser {
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
    @Override
    public List<Employee> parse(Reader reader) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Skip the header row
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                populateEmployees(values, employees);
            }
        } catch (IOException ioException) {
            throw new ServiceException("Failed to read CSV data", ioException);
        }
        return employees;
    }

    private void populateEmployees(String[] values, List<Employee> employees) {
        Employee employee = mapValuesToEmployee(values);
        employees.add(employee);
    }

    private Employee mapValuesToEmployee(String[] values) {
        int id = Integer.parseInt(values[ID_POSITION].trim());
        String firstName = values[FIRST_NAME_POSITION].trim();
        String lastName = values[LAST_NAME_POSITION].trim();
        double salary = Double.parseDouble(values[SALARY_POSITION].trim());
        Integer managerId = null;
        if (values.length > MANAGER_ID_POSITION) {
            managerId = Integer.parseInt(values[MANAGER_ID_POSITION].trim());
        }
        return new Employee(id, firstName, lastName, salary, Optional.ofNullable(managerId));
    }
}