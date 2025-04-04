# Loan Calculator

The **Loan Calculator** is a JavaFX-based application designed to calculate and visualize loan repayment schedules. It supports different loan repayment types, including annuity and linear schedules, and provides features such as payment gap handling and comparison between repayment types.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
  - [1. `lt.vu.loan_calculator.calc`](#1-ltvuloan_calculatorcalc)
  - [2. `lt.vu.loan_calculator.gui`](#2-ltvuloan_calculatorgui)
- [FXML Files](#fxml-files)
- [How to Run](#how-to-run)
  - [On Windows](#on-windows)
  - [On macOS/Linux](#on-macoslinux)
- [Usage](#usage)
- [GUI Preview](#gui-preview)
  - [Main Window](#main-window)
  - [Single Loan Type Selected](#single-loan-type-selected)
  - [Comparison Mode Selected](#comparison-mode-selected)
- [Author](#author)

## Features

- **Loan Repayment Calculation**:
  - Supports annuity and linear repayment schedules.
  - Calculates monthly payments, interest, and remaining debt.

- **Payment Gap Handling**:
  - Allows adding custom payment gaps with adjustable interest rates.

- **Visualization**:
  - Displays repayment schedules in a table format.
  - Visualizes repayment data using line charts.

- **Comparison**:
  - Compares annuity and linear repayment schedules side by side.

- **Export**:
  - Exports repayment schedules to CSV files.

## Project Structure

The project is organized into two main packages:

### 1. `lt.vu.loan_calculator.calc`

This package contains the core logic for loan calculations. It includes:

- **[`BaseCalculator`](src/main/java/lt/vu/loan_calculator/calc/BaseCalculator.java)**: A base class providing common functionality for loan calculators.
- **[`AnnuityCalculator`](src/main/java/lt/vu/loan_calculator/calc/AnnuityCalculator.java)**: A calculator for loans with annuity repayment schedules.
- **[`LinearCalculator`](src/main/java/lt/vu/loan_calculator/calc/LinearCalculator.java)**: A calculator for loans with linear repayment schedules.
- **[`MonthPaymentData`](src/main/java/lt/vu/loan_calculator/calc/MonthPaymentData.java)**: A data model representing monthly payment details.

### 2. `lt.vu.loan_calculator.gui`

This package contains the graphical user interface (GUI) components for the application. It includes:

- **[`MainApplication`](src/main/java/lt/vu/loan_calculator/gui/MainApplication.java)**: The main entry point for the JavaFX application.
- **[`MainController`](src/main/java/lt/vu/loan_calculator/gui/MainController.java)**: Handles user input, loan calculations, and navigation to results views.
- **[`ResultsTableController`](src/main/java/lt/vu/loan_calculator/gui/ResultsTableController.java)**: Displays loan repayment schedules in a table format.
- **[`ResultsChartController`](src/main/java/lt/vu/loan_calculator/gui/ResultsChartController.java)**: Visualizes loan repayment data using line charts.

## FXML Files

The GUI layout is defined using FXML files located in the `resources` directory:

- **[`main-view.fxml`](src/main/resources/lt/vu/loan_calculator/gui/main-view.fxml)**: The main view for inputting loan details.
- **[`results-table-view.fxml`](src/main/resources/lt/vu/loan_calculator/gui/results-table-view.fxml)**: Displays repayment schedules in a table.
- **[`results-chart-view.fxml`](src/main/resources/lt/vu/loan_calculator/gui/results-chart-view.fxml)**: Displays repayment data using line charts.

## How to Run

### On Windows

1. Ensure you have **Java 17** or later installed.
   - You can verify it by running:
     ```cmd
     java -version
     ```
2. Set environment variable `JAVA_HOME` to point to your Java JDK installation:
   ```cmd
   SET JAVA_HOME=C:\path\to\JDKxx
   ```
   - Verify it is set correctly:
     ```cmd
     echo %JAVA_HOME%
     ```
3. Clone the repository and navigate to the project directory.
   ```cmd
   git clone <repository-url>
   cd "Loan Calculator"
   ```
4. Run the application using one of the following methods:
   - **From your preferred IDE**: Execute the `MainApplication` class.
   - **Using Maven configuration**: Run the following command in the terminal:
     ```cmd
     mvnw.cmd clean javafx:run
     ```

### On macOS/Linux

1. Ensure you have **Java 17** or later installed.
    - You can verify it by running:
      ```bash
      java -version
      ```
2. Set environment variable `JAVA_HOME` to point to your Java JDK installation:
   ```bash
   export JAVA_HOME="/path/to/JDKxx"
   ```
   - Verify it is set correctly:
     ```bash
     echo $JAVA_HOME
     ```
3. Clone the repository and navigate to the project directory.
   ```bash
   git clone <repository-url>
   cd "Loan Calculator"
   ```
4. Run the application using one of the following methods:
    - **From your preferred IDE**: Execute the `MainApplication` class.
    - **Using Maven configuration**: Run the following command in the terminal:
      ```bash
      ./mvnw clean javafx:run
      ```

## Usage

1. Launch the application.
2. Enter the loan details:
   - Loan amount
   - Loan duration (in years or months)
   - Annual interest rate
3. Select the repayment type:
   - Annuity
   - Linear
   - Comparison (to compare both types side by side)
4. (Optional) Add payment gaps with custom interest rates.
5. Click the **"Skaičiuoti"** button to calculate the repayment schedule.
6. View the results:
   - In a table format for detailed monthly breakdowns.
   - In a chart format for visual representation.
7. Export the repayment schedule to a CSV file if needed.

## GUI Preview

### Main Window

<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/main_blank.jpg" alt="Blank main window" height="400"/>

### Single Loan Type Selected

<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/main_filled_annuity.jpg" alt="Filled main window (annuity selected)" height="400"/>
<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/annuity_table.jpg" alt="Results table (annuity)" height="400"/>
<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/annuity_chart.jpg" alt="Results chart (annuity)" height="300"/>

### Comparison Mode Selected

<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/main_filled_compare.jpg" alt="Filled main window (compare selected)" height="400"/>
<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/compare_table_annuity.jpg" alt="Results table (annuity)" height="400"/>
<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/compare_table_linear.jpg" alt="Results table (linear)" height="400"/>
<img src="https://raw.githubusercontent.com/daniel-sosna/Loan-calculator/docs-screenshots/data/screenshots/compare_chart.jpg" alt="Results chart (compare)" height="300"/>

## Author

Daniel Sosna – Vilnius University student.
