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

    private int currentOccurrence;

    private int period;

    /**
     * Construct the Amortizer for a given {@code Loan}, with the given
     * {@code startDate}
     * 
     * @param loan the given {@code Loan} to amortize
     * @param startDate the starting date of the {@code Loan}
     * @param period the period between 2 payments
     */
    public Amortizer(Loan loan, LocalDate startDate, int period) {
	this.loan = loan;
	this.startDate = startDate;
	this.period = period;
	currentBalance = loan.getAmount();
	currentOccurrence = 0;
    }

    private Amortization getInfo() {
	Amortization amortization;
	if (period == 1) {
	    amortization = new MonthlyAmortization();
	} else {
	    amortization = new AnnualAmortization();
	}
	amortization.getInfo(loan, currentBalance, startDate,
		currentOccurrence);
	currentBalance = amortization.getRemainingBalance();
	return amortization;
    }

    /**
     * Retrieve the amortization informations in a list.
     * 
     * @return a list of amortization informations
     */
    public List<Amortization> amortize() {
	List<Amortization> amortizationInformations = new ArrayList<>();
	while (currentBalance > 0) {
	    Amortization info = getInfo();
	    amortizationInformations.add(info);
	    currentOccurrence++;
	}
	return Collections.unmodifiableList(amortizationInformations);
    }
}
