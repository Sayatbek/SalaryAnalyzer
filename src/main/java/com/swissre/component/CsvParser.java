package com.swissre.component;

import com.swissre.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for parsing CSV data into Employee objects.
 */
public class CsvParser {

    /**
     * Parses a CSV from a BufferedReader and returns a list of Employee objects.
     * It catches specific exceptions like IOException and NumberFormatException.
     * It also ensures that the BufferedReader is closed in a finally block.
     *
     * @param reader the BufferedReader to parse.
     * @return a list of Employee objects parsed from the CSV.
     */
    public List<Employee> parseCsv(BufferedReader reader) {
        List<Employee> employees = new ArrayList<>();
        String line;
        try {
            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    Employee employee = new Employee(
                            Integer.parseInt(values[0].trim()),
                            values[1].trim(),
                            values[2].trim(),
                            Double.parseDouble(values[3].trim()),
                            values.length < 5 ? null : Integer.parseInt(values[4].trim()));
                    employees.add(employee);
                } catch (NumberFormatException numberFormatException) {
                    System.err.println("Failed to parse number from line " + line + ": " + numberFormatException.getMessage());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid CSV data: " + e.getMessage());
        } catch (IOException ioException) {
            System.err.println("Failed to read CSV data: " + ioException.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ioException) {
                System.err.println("Failed to close reader: " + ioException.getMessage());
            }
        }
        return employees;
    }
}