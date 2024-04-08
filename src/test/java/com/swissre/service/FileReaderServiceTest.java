package com.swissre.service;

import com.swissre.component.CsvParser;
import com.swissre.component.FileReader;
import com.swissre.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileReaderServiceTest {

    @Test
    void testReadEmployeesFromCsv() {
        FileReaderService fileReaderService = new FileReaderService(new FileReader(), new CsvParser());
        String testFilePath = "employees.csv";
        List<Employee> employees = fileReaderService.readEmployeesFromCsv(testFilePath);
        Assertions.assertFalse(employees.isEmpty(), "List of employees should not be empty");
    }
}
