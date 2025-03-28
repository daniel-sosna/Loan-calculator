/**
 * This package contains classes for calculating loan repayment schedules.
 * 
 * <p>It includes the following components:
 * <ul>
 *   <li>{@link lt.vu.loan_calculator.calc.BaseCalculator} - A base class for loan calculators, providing common functionality.</li>
 *   <li>{@link lt.vu.loan_calculator.calc.AnnuityCalculator} - A calculator for loans with annuity repayment schedules.</li>
 *   <li>{@link lt.vu.loan_calculator.calc.LinearCalculator} - A calculator for loans with linear repayment schedules.</li>
 *   <li>{@link lt.vu.loan_calculator.calc.MonthPaymentData} - A data model representing monthly payment details.</li>
 * </ul>
 * 
 * <p>The calculators support features such as:
 * <ul>
 *   <li>Generating repayment schedules.</li>
 *   <li>Calculating total interest paid over the loan term.</li>
 *   <li>Adding payment gaps with custom interest rates.</li>
 * </ul>
 */
package lt.vu.loan_calculator.calc;
