package lt.vu.loan_calculator.calc;

/**
 * A data model representing the details of a monthly loan payment.
 */
public class MonthPaymentData {
    private int month;
    private double payment;
    private double principal;
    private double interest;
    private double remainingDebt;

    /**
     * Constructs a MonthPaymentData object with the specified details.
     *
     * @param month          The month number.
     * @param payment        The total payment for the month.
     * @param principal      The principal portion of the payment.
     * @param interest       The interest portion of the payment.
     * @param remainingDebt  The remaining debt before the payment.
     */
    public MonthPaymentData(int month, double payment, double principal, double interest, double remainingDebt) {
        this.month = month;
        this.payment = payment;
        this.principal = principal;
        this.interest = interest;
        this.remainingDebt = remainingDebt;
    }

    public int getMonth() { return month; }
    public double getPayment() { return payment; }
    public double getPrincipal() { return principal; }
    public double getInterest() { return interest; }
    public double getRemainingDebt() { return remainingDebt; }

    public void setMonth(int month) { this.month = month; }
    public void setPayment(double payment) { this.payment = payment; }
    public void setPrincipal(double principal) { this.principal = principal; }
    public void setInterest(double interest) { this.interest = interest; }
    public void setRemainingDebt(double remainingDebt) { this.remainingDebt = remainingDebt; }
}
