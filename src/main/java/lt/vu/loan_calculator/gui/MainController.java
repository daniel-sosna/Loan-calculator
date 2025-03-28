package lt.vu.loan_calculator.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lt.vu.loan_calculator.calc.AnnuityCalculator;
import lt.vu.loan_calculator.calc.LinearCalculator;
import lt.vu.loan_calculator.calc.MonthPaymentData;

import java.io.IOException;

/**
 * The controller for the main view of the Loan Calculator application.
 * <p>
 * This class handles user input, loan calculations, and navigation to results views.
 */
public class MainController {
    @FXML private TextField sum;
    @FXML private TextField durationYears;
    @FXML private TextField durationMonths;
    @FXML private TextField interest;
    @FXML private RadioButton isAnnuity;
    @FXML private RadioButton isLinear;
    @FXML private RadioButton isCompare;
    @FXML private CheckBox isDelay;
    @FXML private GridPane delayGrid;
    @FXML private TextField delayInterest;
    @FXML private TextField delayBegin;
    @FXML private TextField delayEnd;
    private final ToggleGroup loanTypeGroup = new ToggleGroup();

    /**
     * Configures the toggle buttons and binds the visibility of the delay grid to the delay checkbox.
     */
    public void configureButtons() {
        isAnnuity.setToggleGroup(loanTypeGroup);
        isLinear.setToggleGroup(loanTypeGroup);
        isCompare.setToggleGroup(loanTypeGroup);
        delayGrid.visibleProperty().bind(isDelay.selectedProperty());
    }

    /**
     * Handles the calculation of loan payments based on user input.
     * <p>
     * Depending on the selected loan type, it calculates the repayment schedule and displays the results.
     *
     * @throws IOException If the FXML files for the results views cannot be loaded.
     */
    @FXML
    protected void calculatePayment() throws IOException {
        // Check if the required fields are filled in
        if (this.sum.getText().isEmpty() || this.interest.getText().isEmpty()) {
            System.out.println("Bad Input");
            return;
        }
        // Convert given values
        double sum = parseDouble(this.sum);
        int years = this.durationYears.getText().isEmpty() ? 0 : parseInt(this.durationYears);
        int months = this.durationMonths.getText().isEmpty() ? 0 : parseInt(this.durationMonths);
        int totalMonths = years * 12 + months;
        double interest = parseDouble(this.interest);

        if (sum != 0 && totalMonths != 0) {
            // Calculate the loan schedule and the total amount of interest to be paid then display results windows
            if (isAnnuity.isSelected()) {
                AnnuityCalculator calculator = new AnnuityCalculator(sum, interest, totalMonths);
                if (isDelay.isSelected()) calculator.addGap(parseInt(delayBegin), parseInt(delayEnd), parseDouble(delayInterest));
                createResultsTableWindow(calculator.getSchedule(), calculator.getTotalInterest(), "Anuitetinio tipo");
                createResultsChartWindow(calculator.getSchedule());
            } else if (isLinear.isSelected()) {
                LinearCalculator calculator = new LinearCalculator(sum, interest, totalMonths);
                if (isDelay.isSelected()) calculator.addGap(parseInt(delayBegin), parseInt(delayEnd), parseDouble(delayInterest));
                createResultsTableWindow(calculator.getSchedule(), calculator.getTotalInterest(), "Linijinio tipo");
                createResultsChartWindow(calculator.getSchedule());
            } else if (isCompare.isSelected()) {
                AnnuityCalculator annuityCalculator = new AnnuityCalculator(sum, interest, totalMonths);
                LinearCalculator linearCalculator = new LinearCalculator(sum, interest, totalMonths);
                if (isDelay.isSelected()) annuityCalculator.addGap(parseInt(delayBegin), parseInt(delayEnd), parseDouble(delayInterest));
                if (isDelay.isSelected()) linearCalculator.addGap(parseInt(delayBegin), parseInt(delayEnd), parseDouble(delayInterest));
                createResultsTableWindow(annuityCalculator.getSchedule(), annuityCalculator.getTotalInterest(), "Anuitetinio tipo");
                createResultsTableWindow(linearCalculator.getSchedule(), linearCalculator.getTotalInterest(), "Linijinio tipo");
                createResultsChartWindow(annuityCalculator.getSchedule(), linearCalculator.getSchedule());
            } else {
                System.out.println("No loan type selected");
            }
        }
    }

    /**
     * Parses an integer value from a text field.
     *
     * @param text The text field containing the integer value.
     * @return The parsed integer value.
     */
    private int parseInt(TextField text) { return Integer.parseInt(text.getText()); }

    /**
     * Parses a double value from a text field.
     *
     * @param text The text field containing the double value.
     * @return The parsed double value.
     */
    private double parseDouble(TextField text) { return Double.parseDouble(text.getText()); }

    /**
     * Creates and displays a results table window for a single loan type.
     *
     * @param loanSchedule The loan repayment schedule.
     * @param totalInterest The total interest paid over the loan term.
     * @param type The type of loan (e.g., "Anuitetinio tipo").
     * @throws IOException If the FXML file for the results table view cannot be loaded.
     */
    private void createResultsTableWindow(ObservableList<MonthPaymentData> loanSchedule, double totalInterest, String type) throws IOException {
        // Display the table window
        Stage tableStage = new Stage();
        FXMLLoader tableFxmlLoader = new FXMLLoader(MainController.class.getResource("results-table-view.fxml"));
        Scene tableScene = new Scene(tableFxmlLoader.load(), 600, 600);
        ResultsTableController resultsTableController = tableFxmlLoader.getController();
        resultsTableController.initializeTable(loanSchedule, totalInterest);
        tableStage.setTitle(type + " paskolos įmokų lentelė");
        tableStage.setScene(tableScene);
        tableStage.show();
    }

    /**
     * Creates and displays a results chart window for a single loan type.
     *
     * @param loanSchedule The loan repayment schedule.
     * @throws IOException If the FXML file for the results chart view cannot be loaded.
     */
    private void createResultsChartWindow(ObservableList<MonthPaymentData> loanSchedule) throws IOException {
        // Display the single loan type window
        Stage chartStage = new Stage();
        FXMLLoader chartFxmlLoader = new FXMLLoader(MainController.class.getResource("results-chart-view.fxml"));
        Scene chartScene = new Scene(chartFxmlLoader.load(), 500, 400);
        ResultsChartController resultsChartController = chartFxmlLoader.getController();
        resultsChartController.initializeSingleLoanTypeChart(loanSchedule);
        chartStage.setTitle("Paskolos įmokų grafikas");
        chartStage.setScene(chartScene);
        chartStage.show();
    }

    /**
     * Creates and displays a results chart window comparing two loan types.
     *
     * @param annuityLoanSchedule The repayment schedule for the annuity loan.
     * @param linearLoanSchedule The repayment schedule for the linear loan.
     * @throws IOException If the FXML file for the results chart view cannot be loaded.
     */
    private void createResultsChartWindow(ObservableList<MonthPaymentData> annuityLoanSchedule, ObservableList<MonthPaymentData> linearLoanSchedule) throws IOException {
        // Display the loan types comparison window
        Stage chartStage = new Stage();
        FXMLLoader chartFxmlLoader = new FXMLLoader(MainController.class.getResource("results-chart-view.fxml"));
        Scene chartScene = new Scene(chartFxmlLoader.load(), 500, 400);
        ResultsChartController resultsChartController = chartFxmlLoader.getController();
        resultsChartController.initializeComparisonChart(annuityLoanSchedule, linearLoanSchedule);
        chartStage.setTitle("Paskolos įmokų palyginimo grafikas");
        chartStage.setScene(chartScene);
        chartStage.show();
    }
}
