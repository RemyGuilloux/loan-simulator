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

    private int year;

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
	year = 0;
    }

    private String getYearInfo() {
	int year = getYear();
	double payments = loan.getPayments();
	double interests = getInterestsCost();
	double insurance = getInsuranceCost();
	double totalCost = interests + insurance;
	if (payments >= currentBalance) {
	    payments = roundToTwoDecimal(currentBalance + totalCost);
	}
	double amortizedAmount = roundToTwoDecimal(payments - totalCost);
	currentBalance = roundToTwoDecimal(currentBalance - amortizedAmount);
	String yearInfo = "year=" + year + ", amortized=" + amortizedAmount
		+ ", interests=" + interests + ", remainingBalance="
		+ currentBalance + ", payments=" + payments + ", insurance="
		+ insurance + ", totalCost=" + totalCost;
	return yearInfo;
    }

    /**
     * Retrieve the amortization informations in a list.
     * 
     * @return a list of amortization informations
     */
    public List<String> amortize() {
	List<String> amortizationInformations = new ArrayList<>();
	while (currentBalance > 0) {
	    String info = getYearInfo();
	    amortizationInformations.add(info);
	    year++;
	}
	return Collections.unmodifiableList(amortizationInformations);
    }

    private double getInterestsCost() {
	double interests = currentBalance * (loan.getInterestRate() / 100);
	return roundToTwoDecimal(interests);
    }

    private double getInsuranceCost() {
	double insurance = currentBalance * (loan.getInsuranceRate() / 100);
	return roundToTwoDecimal(insurance);
    }

    private int getYear() {
	return startDate.getYear() + year;
    }

    private double roundToTwoDecimal(double number) {
	return Math.round(number * 100) / 100d;
    }
}
