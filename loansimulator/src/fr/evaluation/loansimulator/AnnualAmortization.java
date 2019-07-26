package fr.evaluation.loansimulator;

import java.time.LocalDate;

public class AnnualAmortization extends Amortization {

    @Override
    protected double getPaymentAmount(Loan loan) {
	double amount = loan.getAmount();
	long duration = loan.getDuration();
	double rate = loan.getInterestRate() / 100;
	double divided = amount * (rate);
	double divider = 1 - (Math.pow(1 + (rate), (-duration)));
	return divided / divider;
    }

    @Override
    protected double getInsuranceCost(Loan loan) {
	return loan.getAmount() * loan.getInsuranceRate() / 100;
    }

    @Override
    protected String getDate(LocalDate startDate, int currentOccurrence) {
	int date = startDate.getYear() + currentOccurrence;
	return Integer.toString(date);
    }

    @Override
    public String toString() {
	return "Year=" + date + ", interests=" + interests + ", insurance="
		+ insurance + ", totalCost=" + totalCost + ", payment="
		+ payment + ", amortizedAmount=" + amortizedAmount
		+ ", remainingBalance=" + remainingBalance;
    }
}
