package lt.vu.loan_calculator.calc;

/**
 * A calculator for loans with linear repayment schedules.
 * @see BaseCalculator BaseCalculator parent class for shared schedule managing methods.
 */
public class LinearCalculator extends BaseCalculator {
    private double monthlyPrincipal;

    /**
     * Constructs a LinearCalculator with the specified loan amount, interest rate, and total months.
     *
     * @param loanAmount   The total loan amount.
     * @param interestRate The annual interest rate as a percentage.
     * @param totalMonths  The total number of months for loan repayment.
     */
    public LinearCalculator(double loanAmount, double interestRate, int totalMonths) {
        super(loanAmount, interestRate, totalMonths);
        this.monthlyPrincipal = loanAmount / totalMonths;
        generateSchedule();
    }

    /**
     * Generates the loan repayment schedule for a linear loan.
     */
    private void generateSchedule() {
        double remainingDebt = loanAmount;
        for (int month = 1; month <= totalMonths; month++) {
            double interest = remainingDebt * annualInterestRate;
            double payment = monthlyPrincipal + interest;
            addMonth(month, payment, monthlyPrincipal, interest, Math.max(0, remainingDebt));
            remainingDebt -= monthlyPrincipal;
        }
    }
}
