package fr.evaluation.loansimulator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the amortization of a given {@code Loan}.
 * <p>
 * 
 * @author Thierry VILLEPREUX
 */
public class Amortizer {

    private Loan loan;

    private LocalDate startDate;

    private double currentBalance;

    private int currentYear;

    /**
     * Construct the Amortizer for a given {@code Loan}, with the given
     * {@code startDate}
     * 
     * @param loan the given {@code Loan} to amortize
     * @param startDate the starting date of the {@code Loan}
     */
    public Amortizer(Loan loan, LocalDate startDate) {
	this.loan = loan;
	this.startDate = startDate;
	currentBalance = loan.getAmount();
	currentYear = (int) loan.getDuration();
    }

    private String getYearInfo() {
	int year = getYear();
	double payments = LoanAlgorithms.getAnnualPayments(loan);
	if (loan.getDuration() == 1) {
	    payments = payments + currentBalance;
	}
	double interests = LoanAlgorithms.getYearInterest(loan, currentBalance);
	double insurance = LoanAlgorithms.getInsuranceAnnualCost(loan);
	double totalCost = interests + insurance;
	if (payments >= currentBalance + totalCost) {
	    payments = currentBalance + totalCost;
	}
	double amortizedAmount = roundToTwoDecimal(payments - totalCost);
	currentBalance = roundToTwoDecimal(currentBalance - amortizedAmount);
	String yearInfo = "year=" + year + ", amortized="
		+ roundToTwoDecimal(amortizedAmount) + ", interests="
		+ roundToTwoDecimal(interests) + ", remainingBalance="
		+ roundToTwoDecimal(currentBalance) + ", payments="
		+ roundToTwoDecimal(payments) + ", insurance="
		+ roundToTwoDecimal(insurance) + ", totalCost="
		+ roundToTwoDecimal(totalCost);
	return yearInfo;
    }

    /**
     * Retrieve the amortization informations in a list.
     * 
     * @return a list of amortization informations
     */
    public List<String> amortize() {
	List<String> amortizationInformations = new ArrayList<>();
	while (currentBalance != 0) {
	    String info = getYearInfo();
	    amortizationInformations.add(info);
	    currentYear++;
	}
	return Collections.unmodifiableList(amortizationInformations);
    }

    private int getYear() {
	return startDate.getYear() + currentYear;
    }

    private double roundToTwoDecimal(double number) {
	return Math.round(number * 100) / 100d;
    }
}
