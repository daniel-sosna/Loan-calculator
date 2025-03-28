package lt.vu.loan_calculator.calc;

/**
 * A calculator for loans with annuity repayment schedules.
 * @see BaseCalculator BaseCalculator parent class for shared schedule managing methods.
 */
public class AnnuityCalculator extends BaseCalculator {
    private double monthlyPayment;

    /**
     * Constructs an AnnuityCalculator with the specified loan amount, interest rate, and total months.
     *
     * @param loanAmount   The total loan amount.
     * @param interestRate The annual interest rate as a percentage.
     * @param totalMonths  The total number of months for loan repayment.
     */
    public AnnuityCalculator(double loanAmount, double interestRate, int totalMonths) {
        super(loanAmount, interestRate, totalMonths);
        this.monthlyPayment = calculateMonthlyPayment();
        generateSchedule();
    }

    /**
     * Generates the loan repayment schedule for an annuity loan.
     */
    private void generateSchedule() {
        double remainingDebt = loanAmount;
        for (int month = 1; month <= totalMonths; month++) {
            double interest = remainingDebt * annualInterestRate;
            double principal = monthlyPayment - interest;
            addMonth(month, monthlyPayment, principal, interest, Math.max(0, remainingDebt));
            remainingDebt -= principal;
        }
    }

    /**
     * Calculates the fixed monthly payment for the annuity loan.
     *
     * @return The fixed monthly payment amount.
     */
    private double calculateMonthlyPayment() {
        return loanAmount * (annualInterestRate * Math.pow(1 + annualInterestRate, totalMonths)) /
                (Math.pow(1 + annualInterestRate, totalMonths) - 1);
    }
}
