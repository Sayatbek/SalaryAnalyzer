package com.swissre.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileReaderTest {

    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new FileReader();
    }

    @Test
    void testReadFile_Success() throws IOException {
        BufferedReader reader = fileReader.readFile("employees.csv");
        String firstLine = reader.readLine();
        assertEquals("Id,firstName,lastName,salary,managerId", firstLine);
    }

    @Test
    void testReadFile_FileNotFound() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.readFile("nonexistentFile.csv"));
    }
}