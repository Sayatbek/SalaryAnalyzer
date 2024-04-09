package com.swissre.component;

import com.swissre.component.impl.CsvParser;
import com.swissre.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    private Parser parser;
    private BufferedReader reader;

    @BeforeEach
    void setUp() {
        parser = new CsvParser();
    }

    @Test
    void testParseCsv() {
        String csvData = "id,firstName,lastName,salary,managerId\n"
                + "1,John,Doe,50000,\n"
                + "2,Jane,Doe,60000,1";
        reader = new BufferedReader(new StringReader(csvData));

        List<Employee> employees = parser.parse(reader);

        assertEquals(2, employees.size());

        Employee employee1 = employees.get(0);
        assertEquals(1, employee1.getId());
        assertEquals("John", employee1.getFirstName());
        assertEquals("Doe", employee1.getLastName());
        assertEquals(50000, employee1.getSalary());
        assertEquals(Optional.empty(), employee1.getManagerId());

        Employee employee2 = employees.get(1);
        assertEquals(2, employee2.getId());
        assertEquals("Jane", employee2.getFirstName());
        assertEquals("Doe", employee2.getLastName());
        assertEquals(60000, employee2.getSalary());
        assertTrue(employee2.getManagerId().isPresent());
        assertEquals(1, employee2.getManagerId().get());
    }

    @Test
    void testParseCsv_EmptyData() {
        reader = new BufferedReader(new StringReader(""));

        List<Employee> employees = parser.parse(reader);
        assertTrue(employees.isEmpty());
    }

    @Test
    void testParseCsv_HeaderOnly() {
        reader = new BufferedReader(new StringReader("id,firstName,lastName,salary,managerId"));
        List<Employee> employees = parser.parse(reader);
        assertTrue(employees.isEmpty());
    }

    @Test
    void testParseCsv_MissingFields() {
        reader = new BufferedReader(new StringReader("id,firstName,lastName,salary,managerId\n" +
                "1,John,Doe,"));

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parser.parse(reader),
                "Some input fields are missing.");
    }

    @Test
    void testParseCsv_NonNumericFields() {
        reader = new BufferedReader(new StringReader("id,firstName,lastName,salary,managerId\n" +
                "1,John,Doe,abc,def"));

        assertThrows(NumberFormatException.class, () -> parser.parse(reader),
                "Salary and managerId must be numeric.");
    }
}