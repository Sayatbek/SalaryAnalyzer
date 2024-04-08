package com.swissre.component;

import com.swissre.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    private CsvParser csvParser;
    private BufferedReader reader;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        csvParser = new CsvParser();
        System.setErr(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setErr(standardOut);
    }

    @Test
    void testParseCsv() {
        String csvData = "id,firstName,lastName,salary,managerId\n"
                + "1,John,Doe,50000,\n"
                + "2,Jane,Doe,60000,1";
        reader = new BufferedReader(new StringReader(csvData));

        List<Employee> employees = csvParser.parseCsv(reader);

        assertEquals(2, employees.size());

        Employee employee1 = employees.get(0);
        assertEquals(1, employee1.getId());
        assertEquals("John", employee1.getFirstName());
        assertEquals("Doe", employee1.getLastName());
        assertEquals(50000, employee1.getSalary());
        assertNull(employee1.getManagerId());

        Employee employee2 = employees.get(1);
        assertEquals(2, employee2.getId());
        assertEquals("Jane", employee2.getFirstName());
        assertEquals("Doe", employee2.getLastName());
        assertEquals(60000, employee2.getSalary());
        assertEquals(1, employee2.getManagerId());
    }

    @Test
    void testParseCsv_EmptyData() {
        reader = new BufferedReader(new StringReader(""));

        List<Employee> employees = csvParser.parseCsv(reader);
        assertTrue(employees.isEmpty());
    }

    @Test
    void testParseCsv_HeaderOnly() {
        reader = new BufferedReader(new StringReader("id,firstName,lastName,salary,managerId"));
        List<Employee> employees = csvParser.parseCsv(reader);
        assertTrue(employees.isEmpty());
    }

    @Test
    void testParseCsv_MissingFields() {
        reader = new BufferedReader(new StringReader("id,firstName,lastName,salary,managerId\n" +
                "1,John,Doe,"));

        List<Employee> employees = csvParser.parseCsv(reader);
        assertTrue(employees.isEmpty());

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Invalid CSV data"));
    }

    @Test
    void testParseCsv_NonNumericFields() {
        reader = new BufferedReader(new StringReader("id,firstName,lastName,salary,managerId\n" +
                "1,John,Doe,abc,def"));

        List<Employee> employees = csvParser.parseCsv(reader);
        assertTrue(employees.isEmpty());

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Failed to parse number from line"));
    }
}