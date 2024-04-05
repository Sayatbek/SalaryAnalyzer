package com.swissre.service;

import com.swissre.component.CsvParser;
import com.swissre.component.FileReader;
import com.swissre.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SalaryAnalysisServiceTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private SalaryAnalysisService salaryAnalysisService;
    private final FileReaderService fileReaderService = new FileReaderService(new FileReader(), new CsvParser());


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor)); // Capture System.out

        // Initialized before each test with the specific scenario
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut); // Reset System.out to its original
    }

    @Test
    void testManagerEarningLessThanTheyShould() {
        // Set up a scenario where the manager earns less than they should
        List<Employee> employees = fileReaderService.readEmployeesFromCsv("employeesEarningLessThanTheyShould.csv");
        salaryAnalysisService = new SalaryAnalysisService(employees);

        salaryAnalysisService.analyzeSalariesOfManagers();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("earns less than they should")); // Assert condition met
        assertFalse(output.contains("earns more than they should")); // Assert condition not met
    }

    @Test
    void testManagerEarningMoreThanTheyShould() {
        // Setup specific employees scenario
        List<Employee> employees = fileReaderService.readEmployeesFromCsv("employeesEarningMoreThanTheyShould.csv");
        salaryAnalysisService = new SalaryAnalysisService(employees);

        salaryAnalysisService.analyzeSalariesOfManagers();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("earns more than they should")); // Assert condition met
        assertFalse(output.contains("earns less than they should")); // Assert condition not met
    }

    @Test
    void testEmployeeReportingLineWithinLimit() {
        // Set up a scenario where the reporting line is within the limit
        List<Employee> employees = fileReaderService.readEmployeesFromCsv("employeesReportingLineWithinLimit.csv");
        salaryAnalysisService = new SalaryAnalysisService(employees);

        salaryAnalysisService.analyzeSalariesOfManagers();
        String output = outputStreamCaptor.toString().trim();
        assertFalse(output.contains("reporting line that is too long")); // Assert condition not met
    }

    @Test
    void testEmployeeReportingLineTooLong() {
        // Set up a long chain of reporting
        List<Employee> employees = fileReaderService.readEmployeesFromCsv("employeesReportingLineTooLong.csv");
        salaryAnalysisService = new SalaryAnalysisService(employees);

        salaryAnalysisService.analyzeDepthOfReportingLines();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("reporting line that is too long")); // Assert condition met
    }
}