module com.example.loan_calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens lt.vu.loan_calculator.gui to javafx.fxml;
    exports lt.vu.loan_calculator.gui;
}