package com.swissre.component.impl;

import com.swissre.component.Reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Implementation of IFileReader for reading files from the classpath.
 */
public class FileReader implements Reader {

    /**
     * Reads a file from the classpath and returns a BufferedReader.
     *
     * @param fileName the name of the file to read.
     * @return a BufferedReader for the file.
     * @throws IllegalArgumentException if the file is not found on the classpath.
     */
    @Override
    public BufferedReader read(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}