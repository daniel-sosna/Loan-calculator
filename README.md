# Loan Calculator

The **Loan Calculator** is a JavaFX-based application designed to calculate and visualize loan repayment schedules. It supports different loan repayment types, including annuity and linear schedules, and provides features such as payment gap handling and comparison between repayment types.

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

1. Ensure you have Java 17 or later installed.
2. Clone the repository and navigate to the project directory.
3. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Visual Studio Code).
4. Build the project using your IDE's build tools.
5. Run the application by executing the `MainApplication` class.

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

## Author

Daniel Sosna
