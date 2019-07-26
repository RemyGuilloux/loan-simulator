package fr.evaluation.loansimulator;

import java.time.LocalDate;

/**
 * @author Thierry VILLEPREUX
 */
public abstract class Amortization {

    protected double interests;

    protected double insurance;

    protected double totalCost;

    protected double payment;

    protected double amortizedAmount;

    protected double remainingBalance;

    protected String date;

    public final Amortization getInfo(Loan loan, double balance,
	    LocalDate startDate, int currentOccurrence) {
	this.interests = roundToTwoDecimal(getInterest(loan, balance));
	this.insurance = roundToTwoDecimal(getInsuranceCost(loan));
	this.totalCost = roundToTwoDecimal(interests + insurance);
	this.payment = roundToTwoDecimal(getPaymentAmount(loan));
	if (payment >= balance + totalCost || loan.getDuration() == 1) {
	    payment = roundToTwoDecimal(balance + totalCost);
	}
	this.amortizedAmount = roundToTwoDecimal(
		AmortizedAmount(payment, totalCost));
	this.remainingBalance = roundToTwoDecimal(balance - amortizedAmount);
	this.date = getDate(startDate, currentOccurrence);
	return this;
    }

    protected abstract String getDate(LocalDate startDate,
	    int currentOccurrence);

    protected abstract double getInsuranceCost(Loan loan);

    private double AmortizedAmount(double payment, double totalCost) {
	return payment - totalCost;
    }

    protected abstract double getPaymentAmount(Loan loan);

    public static double getInterest(Loan loan, double balance) {
	return balance * loan.getInterestRate() / 100;
    }

    public double getRemainingBalance() {
	return remainingBalance;
    }

    public double getPayment() {
	return payment;
    }

    public double getTotalCost() {
	return totalCost;
    }

    private double roundToTwoDecimal(double number) {
	return Math.round(number * 100) / 100d;
    }
}
