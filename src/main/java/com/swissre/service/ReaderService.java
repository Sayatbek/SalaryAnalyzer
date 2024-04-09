package com.swissre.service;

import com.swissre.component.Parser;
import com.swissre.component.Reader;
import com.swissre.model.Employee;

import java.io.BufferedReader;
import java.util.List;

/**
 * Service for reading Employee data from a CSV file.
 */
public class ReaderService {

    private final Reader reader;
    private final Parser parser;

    /**
     * Constructs a new ReaderService with the given IFileReader and ICsvParser.
     *
     * @param reader the IReader to use for reading files.
     * @param parser the IParser to use for parsing CSV data.
     */
    public ReaderService(Reader reader, Parser parser) {
        this.reader = reader;
        this.parser = parser;
    }

    /**
     * Reads Employee data from a CSV file and returns a list of Employee objects.
     *
     * @param fileName the name of the CSV file to read.
     * @return a list of Employee objects parsed from the CSV file.
     */
    public List<Employee> readEmployees(String fileName) {
        BufferedReader bufferedReader = reader.read(fileName);
        return parser.parse(bufferedReader);
    }
}