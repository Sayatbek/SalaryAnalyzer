package com.swissre.component;

import com.swissre.component.impl.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {

    private Reader reader;

    @BeforeEach
    void setUp() {
        reader = new FileReader();
    }

    @Test
    void testReadFile_Success() throws IOException {
        BufferedReader bufferedReader = reader.read("employees.csv");
        String firstLine = bufferedReader.readLine();
        assertEquals("Id,firstName,lastName,salary,managerId", firstLine);
    }

    @Test
    void testReadFile_FileNotFound() {
        assertThrows(IllegalArgumentException.class, () -> reader.read("nonexistentFile.csv"));
    }
}