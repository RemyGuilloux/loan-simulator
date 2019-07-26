package fr.evaluation.loansimulator;

import java.time.LocalDate;

public class MonthlyAmortization extends Amortization {

    @Override
    protected String getDate(LocalDate startDate, int currentOccurrence) {
	LocalDate date = startDate.plusMonths(currentOccurrence);
	String monthlyDate = date.getDayOfMonth() + "/" + date.getMonthValue()
		+ "/" + date.getYear();
	return monthlyDate;
    }

    @Override
    protected double getInsuranceCost(Loan loan) {
	double annualInsurance = loan.getAmount() * loan.getInsuranceRate()
		/ 100;
	return annualInsurance / 12;
    }

    @Override
    public String toString() {
	return "date=" + date + ", interests=" + interests + ", insurance="
		+ insurance + ", totalCost=" + totalCost + ", payment="
		+ payment + ", amortizedAmount=" + amortizedAmount
		+ ", remainingBalance=" + remainingBalance;
    }

    @Override
    protected double getPaymentAmount(Loan loan) {
	double amount = loan.getAmount();
	long duration = loan.getDuration();
	double rate = loan.getInterestRate() / 100;
	double divided = amount * (rate / 12);
	double divider = 1 - (Math.pow(1 + (rate / 12), (-duration)));
	return divided / divider;
    }
}
