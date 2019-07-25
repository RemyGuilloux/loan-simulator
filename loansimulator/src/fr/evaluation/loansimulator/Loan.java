package fr.evaluation.loansimulator;

/**
 * Representation of a loan.
 * <p>
 * Every field is mandatory.
 * <ul>
 * <li>Amount : the amount granted to the customer</li>
 * <li>loan type : Estate, Car, BuildingWork</li>
 * <li>start date : the date when the</li>
 * <li>duration : the duration in years</li>
 * <li>interestRate : the {@code interestRate} expressed in years</li>
 * <li>insuranceRate : the {@code insuranceRate} expressed in years</li>
 * 
 * @author Thierry VILLEPREUX
 */
public class Loan {

    private double amount;

    private LoanType type = LoanType.WORKS;

    private long duration;

    private double interestRate;

    private double insuranceRate;

    /**
     * Construct a new loan, regarding user inputs.
     * 
     * @param amount the given amount
     * @param type the type of loan (enum)
     * @param duration the duration in years
     * @param interestRate the yearly interest rate
     * @param insuranceRate the yearly insurance rate
     */
    public Loan(double amount, LoanType type, long duration,
	    double interestRate, double insuranceRate) {
	this.amount = amount;
	this.type = type;
	this.duration = duration;
	this.interestRate = interestRate;
	this.insuranceRate = insuranceRate;
    }

    /**
     * Returns the given borrowed {@code Amount}
     * 
     * @return an amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * returns the type of {@code Loan}.
     * 
     * @return ESTATE, CAR, WORKS
     */
    public LoanType getType() {
	return type;
    }

    /**
     * Returns the duration of the loan in months.
     * 
     * @return a duration between 12 and 360.
     */
    public long getDuration() {
	return duration;
    }

    /**
     * Returns the interest rate of the given {@code Loan}.
     * 
     * @return a {@code interestRate}
     */
    public double getInterestRate() {
	return interestRate;
    }

    /**
     * Returns the insurance rate of the given {@code Loan}.
     * 
     * @return a {@code insuranceRate}
     */
    public double getInsuranceRate() {
	return insuranceRate;
    }

    @Override
    public String toString() {
	return "{amount=" + amount + ", type=" + type + ", duration=" + duration
		+ ", interestRate=" + interestRate + ", insuranceRate="
		+ insuranceRate + "}";
    }
}
