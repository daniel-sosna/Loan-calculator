package lt.vu.loan_calculator.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import lt.vu.loan_calculator.calc.MonthPaymentData;

/**
 * The controller for visualizing loan repayment data using line charts.
 */
public class ResultsChartController {
    @FXML private LineChart<Number, Number> resultsLineChart;

    /**
     * Initializes a line chart for a single loan type.
     *
     * @param loanSchedule The loan repayment schedule.
     */
    public void initializeSingleLoanTypeChart(ObservableList<MonthPaymentData> loanSchedule) {
        XYChart.Series paymentSeries = new XYChart.Series();
        paymentSeries.setName("Bendra įmoka");
        XYChart.Series principalSeries = new XYChart.Series();
        principalSeries.setName("Grąžinamoji dalis");
        XYChart.Series interestSeries = new XYChart.Series();
        interestSeries.setName("Palūkanos");
        for (int month = 1; month <= loanSchedule.size(); month++) {
            paymentSeries.getData().add(new XYChart.Data(month, loanSchedule.get(month - 1).getPayment()));
            principalSeries.getData().add(new XYChart.Data(month, loanSchedule.get(month - 1).getPrincipal()));
            interestSeries.getData().add(new XYChart.Data(month, loanSchedule.get(month - 1).getInterest()));
        }
        resultsLineChart.getData().addAll(paymentSeries, principalSeries, interestSeries);
    }

    /**
     * Initializes a line chart comparing two loan types.
     *
     * @param annuitySchedule The repayment schedule for the annuity loan.
     * @param linearSchedule The repayment schedule for the linear loan.
     */
    public void initializeComparisonChart(ObservableList<MonthPaymentData> annuitySchedule, ObservableList<MonthPaymentData> linearSchedule) {
        XYChart.Series annuityPaymentSeries = new XYChart.Series();
        annuityPaymentSeries.setName("Anuitetinė bendra įmoka");
        XYChart.Series annuityInterestSeries = new XYChart.Series();
        annuityInterestSeries.setName("Anuitetinės palūkanos");
        for (int month = 1; month <= annuitySchedule.size(); month++) {
            annuityPaymentSeries.getData().add(new XYChart.Data(month, annuitySchedule.get(month - 1).getPayment()));
            annuityInterestSeries.getData().add(new XYChart.Data(month, annuitySchedule.get(month - 1).getInterest()));
        }
        XYChart.Series linearPaymentSeries = new XYChart.Series();
        linearPaymentSeries.setName("Linijinė bendra įmoka");
        XYChart.Series linearInterestSeries = new XYChart.Series();
        linearInterestSeries.setName("Linijinės palūkanos");
        for (int month = 1; month <= linearSchedule.size(); month++) {
            linearPaymentSeries.getData().add(new XYChart.Data(month, linearSchedule.get(month - 1).getPayment()));
            linearInterestSeries.getData().add(new XYChart.Data(month, linearSchedule.get(month - 1).getInterest()));
        }
        resultsLineChart.getData().addAll(annuityPaymentSeries, linearPaymentSeries, annuityInterestSeries, linearInterestSeries);
    }
}
