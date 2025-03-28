package lt.vu.loan_calculator.calc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A base class for loan calculators, providing common functionality for generating loan schedules.
 */
class BaseCalculator {
    protected double loanAmount;
    protected double interestRate;
    protected double annualInterestRate;
    protected int totalMonths;
    protected double totalInterest;
    protected ObservableList<MonthPaymentData> schedule;

    /**
     * Constructs a BaseCalculator with the specified loan amount, interest rate, and total months.
     *
     * @param loanAmount   The total loan amount.
     * @param interestRate The annual interest rate as a percentage.
     * @param totalMonths  The total number of months for loan repayment.
     */
    public BaseCalculator(double loanAmount, double interestRate, int totalMonths) {
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.annualInterestRate = interestRate / 100 / 12;
        this.totalMonths = totalMonths;
        this.schedule = FXCollections.observableArrayList();
    }

    /**
     * Returns the loan repayment schedule.
     *
     * @return An observable list of {@link MonthPaymentData} representing the loan schedule.
     */
    public ObservableList<MonthPaymentData> getSchedule() { return schedule; }

    /**
     * Returns the total number of months for loan repayment.
     *
     * @return The total number of months.
     */
    public int getTotalMonths() { return totalMonths; }

    /**
     * Returns the total interest paid over the loan term.
     *
     * @return The total interest paid.
     */
    public double getTotalInterest() { return totalInterest; }

    public double getMonthPayment(int month) { return schedule.get(month - 1).getPayment(); }
    public double getMonthPrincipal(int month) { return schedule.get(month - 1).getPrincipal(); }
    public double getMonthInterest(int month) { return schedule.get(month - 1).getInterest(); }
    public double getMonthRemainingDebt(int month) { return schedule.get(month - 1).getRemainingDebt(); }

    /**
     * Adds a month's payment details to the loan schedule.
     *
     * @param month          The month number.
     * @param monthlyPayment The total payment for the month.
     * @param principal      The principal portion of the payment.
     * @param interest       The interest portion of the payment.
     * @param remainingDebt  The remaining debt before the payment.
     * @see MonthPaymentData#MonthPaymentData(int, double, double, double, double)
     */
    public void addMonth(int month, double monthlyPayment, double principal, double interest, double remainingDebt) {
        this.schedule.add(new MonthPaymentData(month, monthlyPayment, principal, interest, remainingDebt));
        this.totalInterest += interest;
    }
    
    /**
     * Adds a payment gap to the loan schedule with a custom interest rate.
     *
     * @param beginMonth   The starting month of the gap.
     * @param endMonth     The ending month of the gap.
     * @param interestRate The interest rate during the gap period.
     */
    public void addGap(int beginMonth, int endMonth, double interestRate) {
        double currentRemainingDebt = this.schedule.get(beginMonth - 1).getRemainingDebt();
        for (int i = beginMonth - 1; i < endMonth; i++) {
            double interest = currentRemainingDebt * interestRate / 100 / 12;
            this.schedule.add(i, new MonthPaymentData(i + 1, interest, 0, interest, currentRemainingDebt));
        }
        for (int i = endMonth; i < this.schedule.size(); i++) {
            this.schedule.get(i).setMonth(i + 1);
        }
    }
}
