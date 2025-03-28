package lt.vu.loan_calculator.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import lt.vu.loan_calculator.calc.MonthPaymentData;

import java.io.*;

/**
 * The controller for displaying loan repayment schedules in a table format.
 */
public class ResultsTableController {
    private ObservableList<MonthPaymentData> loanSchedule, filteredLoanSchedule;
    @FXML private TextField filterBegin;
    @FXML private TextField filterEnd;
    @FXML private Label totalPrincipalText;
    @FXML private TableView<MonthPaymentData> resultsTable;
    @FXML private TableColumn<MonthPaymentData, Integer> monthColumn;
    @FXML private TableColumn<MonthPaymentData, Double> debtColumn;
    @FXML private TableColumn<MonthPaymentData, Double> paymentColumn;
    @FXML private TableColumn<MonthPaymentData, Double> interestColumn;
    @FXML private TableColumn<MonthPaymentData, Double> principalColumn;
    /* Callback function to set cell precision for 2 decimal places */
    private final Callback<TableColumn<MonthPaymentData, Double>, TableCell<MonthPaymentData, Double>> setPrecision = c -> new TableCell<>() {
        @Override
        protected void updateItem(Double balance, boolean empty) {
            super.updateItem(balance, empty);
            if (balance == null || empty) {
                setText(null);
            } else {
                setText(String.format("%.2f", balance.doubleValue()));
            }
        }
    };

    /**
     * Initializes the table with the loan repayment schedule and total interest.
     *
     * @param loanSchedule The loan repayment schedule.
     * @param totalInterest The total interest paid over the loan term.
     */
    public void initializeTable(ObservableList<MonthPaymentData> loanSchedule, double totalInterest) {
        this.loanSchedule = loanSchedule;
        // Set cells values
        monthColumn.setCellValueFactory(paymentData -> new SimpleObjectProperty<>(paymentData.getValue().getMonth()));
        debtColumn.setCellValueFactory(paymentData -> new SimpleObjectProperty<>(paymentData.getValue().getRemainingDebt()));
        paymentColumn.setCellValueFactory(paymentData -> new SimpleObjectProperty<>(paymentData.getValue().getPayment()));
        interestColumn.setCellValueFactory(paymentData -> new SimpleObjectProperty<>(paymentData.getValue().getInterest()));
        principalColumn.setCellValueFactory(paymentData -> new SimpleObjectProperty<>(paymentData.getValue().getPrincipal()));
        // Set precision for cells with floating point numbers
        debtColumn.setCellFactory(setPrecision);
        paymentColumn.setCellFactory(setPrecision);
        interestColumn.setCellFactory(setPrecision);
        principalColumn.setCellFactory(setPrecision);
        // Fill the table
        resultsTable.setItems(loanSchedule);

        totalPrincipalText.setText(String.format("%.2f€", totalInterest));
    }

    /**
     * Updates the table to display a filtered range of months based on user input.
     */
    @FXML
    protected void updateFilter() {
        int begin = filterBegin.getText().isEmpty() ? 1 : Integer.parseInt(filterBegin.getText());
        begin = Math.max(1, begin);
        int end = filterEnd.getText().isEmpty() ? loanSchedule.size() : Integer.parseInt(filterEnd.getText());
        end = Math.min(end, loanSchedule.size());
        if (begin <= end) {
            filteredLoanSchedule = FXCollections.observableArrayList(loanSchedule.subList(begin - 1, end));
            resultsTable.setItems(filteredLoanSchedule);
        }
    }

    /**
     * Exports the filtered loan repayment schedule to a CSV file.
     */
    @FXML
    protected void exportCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Išsaugokite lentelę");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
                // Write UTF-8 BOM to ensure correct encoding in Excel
                writer.write("\uFEFF");
                // Write CSV header
                writer.write("Mėnuo,Paskolos likutis,Bendra įmoka,Palūkanos,Grąžinamoji dalis\n");
                // Write data
                for (MonthPaymentData month : filteredLoanSchedule) {
                    writer.write(String.format("%d,%.2f,%.2f,%.2f,%.2f%n",
                            month.getMonth(),
                            month.getRemainingDebt(),
                            month.getPayment(),
                            month.getInterest(),
                            month.getPrincipal())
                    );
                }

                System.out.println("Export successful: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error saving file: " + e.getMessage());
            }
        }
    }
}
