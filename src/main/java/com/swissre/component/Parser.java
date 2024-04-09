package com.swissre.component;

import com.swissre.model.Employee;

import java.io.Reader;
import java.util.List;

public interface Parser {
    List<Employee> parse(Reader reader);
}
