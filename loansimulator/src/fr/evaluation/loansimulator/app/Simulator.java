package fr.evaluation.loansimulator.app;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.evaluation.loansimulator.Amortizer;
import fr.evaluation.loansimulator.Loan;
import fr.evaluation.loansimulator.LoanType;

/**
 * Simulator of the loan.
 * <p>
 * Receive user inputs, and then displays the amortization array.
 * 
 * @author Thierry VILLEPREUX
 */
public class Simulator {

    private static Scanner scanner;

    private static LoanType[] types = LoanType.values();

    /**
     * Runs the application.
     */
    public static void run() {
	initializeScanner();
	Loan loan = buildLoan();
	Amortizer amortizer = buildAmortizer(loan);
	List<String> amortizations = amortizer.amortize();
	Display(amortizations);
	closeScanner();
    }

    private static void Display(List<String> amortizations) {
	for (String amortization : amortizations) {
	    System.out.println(amortization);
	}
    }

    private static void initializeScanner() {
	scanner = new Scanner(System.in);
    }

    private static void closeScanner() {
	scanner.close();
    }

    private static Loan buildLoan() {
	System.out.println("Please select an amount : ");
	double amount = scanNumber();
	System.out.println(
		"Please select a type : 1 - Estate, 2 - Car 3 - Works");
	LoanType type = scanLoanType();
	System.out.println("Please select the duration in years : ");
	int duration = (int) Math.floor(scanNumber());
	System.out.println("Please type the interest rate : ");
	double interestRate = scanNumber();
	System.out.println("Please type the insurance rate : ");
	double insuranceRate = scanNumber();
	return new Loan(amount, type, duration, interestRate, insuranceRate);
    }

    @SuppressWarnings("unused")
    private static double scanNumber() {
	double number = -1;
	try {
	    number = scanner.nextInt();
	} catch (InputMismatchException exception) {
	    System.out.println("Wrong format, please type numbers");
	    scanner.next();
	    scanNumber();
	}
	return number;
    }

    @SuppressWarnings("unused")
    private static LoanType scanLoanType() {
	LoanType loanType = null;
	try {
	    int typeIndex = (int) Math.floor(scanNumber());
	    loanType = types[typeIndex];
	} catch (ArrayIndexOutOfBoundsException
		| InputMismatchException exception) {
	    System.out.println(
		    "Wrong format, please type a single number between 1 and 3");
	    scanner.next();
	    scanLoanType();
	}
	return loanType;
    }

    private static Amortizer buildAmortizer(Loan loan) {
	System.out.println(
		"When do you want to start reimbursement ? (DD/MM/YY)");
	String date = scanner.next();
	String[] dates = date.split("/");
	int year = Integer.valueOf(dates[2]);
	int month = Integer.valueOf(dates[1]);
	int day = Integer.valueOf(dates[0]);
	LocalDate startDate = LocalDate.of(year, month, day);
	return new Amortizer(loan, startDate);
    }
}
