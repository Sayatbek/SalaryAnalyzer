package com.swissre.service;

import com.swissre.component.CsvParser;
import com.swissre.component.FileReader;
import com.swissre.model.Employee;

import java.io.BufferedReader;
import java.util.List;

/**
 * Service for reading Employee data from a CSV file.
 */
public class FileReaderService {

    private final FileReader fileReader;
    private final CsvParser csvParser;

    /**
     * Constructs a new FileReaderService with the given IFileReader and ICsvParser.
     *
     * @param fileReader the IFileReader to use for reading files.
     * @param csvParser the ICsvParser to use for parsing CSV data.
     */
    public FileReaderService(FileReader fileReader, CsvParser csvParser) {
        this.fileReader = fileReader;
        this.csvParser = csvParser;
    }

    /**
     * Reads Employee data from a CSV file and returns a list of Employee objects.
     *
     * @param fileName the name of the CSV file to read.
     * @return a list of Employee objects parsed from the CSV file.
     */
    public List<Employee> readEmployeesFromCsv(String fileName) {
        BufferedReader bufferedReader = fileReader.readFile(fileName);
        return csvParser.parse(bufferedReader);
    }
}