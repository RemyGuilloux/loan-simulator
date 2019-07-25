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

    private static double getMensuality(Loan loan) {
	double amount = loan.getAmount();
	long duration = loan.getDuration() * 12;
	double rate = loan.getInterestRate() / 100;
	double divided = amount * (rate / 12);
	double divider = 1 - (Math.pow(1 + (rate / 12), (-duration)));
	return divided / divider;
    }

    private static double getAnnuality(Loan loan) {
	return getMensuality(loan) * 12;
    }

    /**
     * Returns the amount of interests paid in one year regarding the balance.
     * 
     * @param loan the given {@code Loan}
     * @param balance the amount to be paid
     * @return the amount of interests.
     */
    public static double getYearInterest(Loan loan, double balance) {
	return balance * loan.getInterestRate() / 100;
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
	return getAnnuality(loan) + getInsuranceAnnualCost(loan);
    }

    /**
     * Retrieve annual insurance cost.
     * <p>
     * Insurance cost is calculated on the initial amount of the loan.
     * 
     * @param loan the given {@code Loan}
     * @return an annual cost
     */
    public static double getInsuranceAnnualCost(Loan loan) {
	return loan.getAmount() * loan.getInsuranceRate() / 100;
    }
}
