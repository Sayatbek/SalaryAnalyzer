package com.swissre.service;

import com.swissre.model.Employee;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality to read employee data from a CSV file and create a list of Employee objects.
 */
public class FileReaderService {

    /**
     * Reads employee data from a CSV file located in the resources' folder.
     * The CSV file format should match the Employee object structure.
     * <p>
     * The method skips the header row of the CSV and then reads each line,
     * converts it into an Employee object, and adds it to the list of employees.
     *
     * @param fileName The name of the CSV file to read from the resources' folder.
     * @return A list of Employee objects parsed from the CSV file.
     * @throws IllegalArgumentException if the CSV file is not found in the resources' folder.
     */
    public List<Employee> readEmployeesFromCsv(String fileName) {
        List<Employee> employees = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            // Skip the header row
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Employee employee = new Employee(
                        Integer.parseInt(values[0].trim()),
                        values[1].trim(),
                        values[2].trim(),
                        Double.parseDouble(values[3].trim()),
                        values.length < 5 ? null : Integer.parseInt(values[4].trim()));
                employees.add(employee);
            }
        } catch (Exception e) {
            System.err.println("Failed to read employees from CSV file: " + e.getMessage());
        }
        return employees;
    }
}