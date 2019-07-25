package fr.evaluation.loansimulator;

/**
 * An utility class providing algorithms.
 * <p>
 * Gives convenient methods to proceed business calculations such ass a loan
 * total cost or the amortization of a loan.
 * 
 * @author vilth
 */
public class LoanAlgorithms {

    /**
     * Provides the total cost of a {@code Loan}, regarding is interest Rate,
     * insurance Rate, amount and duration.
     * 
     * @param loan the given loan
     * @return a total cost.
     */
    public static double getTotalCost(Loan loan) {
	long duration = loan.getDuration();
	double balance = loan.getAmount();
	double cost = 0;
	double amortizing = balance / duration;
	while (duration != 0) {
	    cost += balance * (loan.getInterestRate() + loan.getInsuranceRate())
		    / 100;
	    balance -= amortizing;
	    duration--;
	}
	return cost;
    }

    /**
     * Returns the annual payments for a given {@code Loan}.
     * <p>
     * annual payment is determined by the total amount plus the total cost. the
     * result is divided by the duration in years.
     * 
     * @param loan the given {@code Loan}
     * @return an annual payment.
     */
    public static double getAnnualPayments(Loan loan) {
	return (loan.getAmount() + loan.getTotalCost()) / loan.getDuration();
    }
}
