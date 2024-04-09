package com.swissre.service;

import com.swissre.component.impl.CsvParser;
import com.swissre.component.impl.FileReader;
import com.swissre.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyzerServiceTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private AnalyzerService analyzerService;
    private final ReaderService readerService = new ReaderService(new FileReader(), new CsvParser());


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testManagerEarningLessThanTheyShould() {
        List<Employee> employees = readerService.readEmployees("employeesEarningLessThanTheyShould.csv");
        analyzerService = new AnalyzerService(employees);

        analyzerService.analyzeManagersSalaries();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("earns less than they should"));
        assertFalse(output.contains("earns more than they should"));
    }

    @Test
    void testManagerEarningMoreThanTheyShould() {
        List<Employee> employees = readerService.readEmployees("employeesEarningMoreThanTheyShould.csv");
        analyzerService = new AnalyzerService(employees);

        analyzerService.analyzeManagersSalaries();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("earns more than they should"));
        assertFalse(output.contains("earns less than they should"));
    }

    @Test
    void testEmployeeReportingLineWithinLimit() {
        List<Employee> employees = readerService.readEmployees("employeesReportingLineWithinLimit.csv");
        analyzerService = new AnalyzerService(employees);

        analyzerService.analyzeManagersSalaries();
        String output = outputStreamCaptor.toString().trim();
        assertFalse(output.contains("reporting line that is too long"));
    }

    @Test
    void testEmployeeReportingLineTooLong() {
        List<Employee> employees = readerService.readEmployees("employeesReportingLineTooLong.csv");
        analyzerService = new AnalyzerService(employees);

        analyzerService.analyzeDepthOfReportingLines();
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("reporting line that is too long"));
    }
}