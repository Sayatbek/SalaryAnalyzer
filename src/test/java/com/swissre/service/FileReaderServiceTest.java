package com.swissre.service;

import com.swissre.component.CsvParser;
import com.swissre.component.FileReader;
import com.swissre.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class FileReaderServiceTest {

    @Test
    void testReadEmployeesFromCsv() {
        // Instantiate the service
        FileReaderService fileReaderService = new FileReaderService(new FileReader(), new CsvParser());

        // Use a test-specific CSV file path
        String testFilePath = "employees.csv";

        // Execute the method under test
        List<Employee> employees = fileReaderService.readEmployeesFromCsv(testFilePath);

        // Assert conditions based on your test csv
        assertFalse("List of employees should not be empty", employees.isEmpty());
    }
}
