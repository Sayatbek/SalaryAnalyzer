# Salary Analysis Application

This application analyzes the organizational structure of BIG COMPANY to identify potential areas for improvement regarding salary distribution and reporting lines. It calculates if managers earn appropriately more than their direct subordinates and identifies employees with excessively long reporting lines up to the CEO.

## Features

- **Salary Analysis**: Determine which managers earn less or more than they should based on the average salary of their direct subordinates.
- **Reporting Line Analysis**: Identify employees with more than 4 managers in their reporting line to the CEO.

## Getting Started

### Prerequisites

- Java 17 or above
- Maven

### Running the Application

1. **Clone the Repository**

   ```bash
   git clone git@github.com:Sayatbek/SalaryAnalyzer.git
   cd SalaryAnalyzer
2. **Build the Application**

   ```bash
   mvn clean package
3. **Run the Tests**

   ```bash
   mvn test
4. **Generate the Documentation**

   ```bash
    mvn javadoc:javadoc
5. **View the Documentation**

   ```bash
   open in browser 'target/site/apidocs/index.html'