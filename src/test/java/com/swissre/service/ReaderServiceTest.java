package com.swissre.service;

import com.swissre.component.impl.CsvParser;
import com.swissre.component.impl.FileReader;
import com.swissre.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReaderServiceTest {

    @Test
    void testReadEmployeesFromCsv() {
        ReaderService readerService = new ReaderService(new FileReader(), new CsvParser());
        String testFilePath = "employees.csv";
        List<Employee> employees = readerService.readEmployees(testFilePath);
        Assertions.assertFalse(employees.isEmpty(), "List of employees should not be empty");
    }
}
