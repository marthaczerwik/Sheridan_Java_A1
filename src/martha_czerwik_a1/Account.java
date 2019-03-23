package martha_czerwik_a1;

import java.util.InputMismatchException;

/**
 * This class will keep track of customer information, such as name and bank
 * balance and interest, and will perform transactions such as depositing or
 * withdrawing money from the account
 * @author Martha Czerwik
 */
public class Account {

    // data fields
    private String customerName;
    final int ID;
    private double balance;
    private double annualInterestRate;

    /**
     * 2 parameter constructor to create an account for the customer
     * @param customerName Name of the customer
     * @param ID Customer ID number
     */
    public Account(String customerName, int ID) {
        this.customerName = customerName;
        this.ID = ID;
    }

    // Accesor methods - Getters
    public String getCustomerName() {
        return customerName;
    }

    public int getID() {
        return ID;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    // Mutator methods - Setters
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * This method deposits the amount entered by the user and adds it to the
     * balance if it is over zero and of type double
     * @param depositedAmount Amount entered by user to deposit
     * @return new balance with added deposit as double to 2 decimal spaces
     * @exception IllegalArgumentException Does not accept deposits that are 0
     * or negative
     * @exception InputMismatchException Does not accept any characters, only 
     * double values
     */
    public double deposit(double depositedAmount) {
        balance += depositedAmount;
        if (depositedAmount <= 0.00) {
            throw new IllegalArgumentException("Deposit must be greater than 0");
        } else if (depositedAmount != (double) depositedAmount) {
            throw new InputMismatchException();
        } else {
            return balance;
        }
    }

    /**
     * This method withdraws the amount entered by the user and subtracts it
     * from the balance, if both withdraw amount and balance are above zero and
     * of type double
     * @param withdrawnAmount
     * @return new balance with deducted withdrawn amount as double to 2 decimal
     * spaces
     * @exception IllegalArgumentException Does not accept withdraw if amount
     * exceeds amount in balance, or if withdraw amount is 0 or under
     * @exception InputMismatchException Does not accept any characters, only 
     * double values
     */
    public double withdraw(double withdrawnAmount) {
        if (balance < withdrawnAmount) {
            throw new IllegalArgumentException("Error: Insufficient funds.");
        } else if (withdrawnAmount <= 0.00) {
            throw new IllegalArgumentException("Error: Invalid amount (amount to withdraw must be greater than $0).");
        } else if (withdrawnAmount != (double) withdrawnAmount) {
            throw new InputMismatchException();
        } else {
            balance -= withdrawnAmount;
            return balance;
        }
    }

    /**
     * This method calculates monthly interest based on balance and user annual
     * interest rate
     * @return interest as double to 2 decimal spaces
     */
    public double monthlyInterest() {
        double interest = balance * (annualInterestRate / 12 / 100);
        return interest;
    }

    /**
     * This method prints account information
     * @return customer name, balance and monthly interest earned in string
     * format
     */
    public String accountInfo() {
        String format = "Customer: %s%nAccount Balance: $%.2f%nMonthly Interest Earned: $%.2f";
        return String.format(format, customerName, balance, monthlyInterest());
    }
    


}

/* InputMismatch condition idea found at
https://stackoverflow.com/questions/12558206/how-can-i-check-if-a-value-is-of-type-integer
*/