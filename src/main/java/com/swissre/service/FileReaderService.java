package com.swissre.service;

import com.swissre.model.Employee;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {
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
