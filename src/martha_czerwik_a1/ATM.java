package martha_czerwik_a1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements a console user interface for the ATM and provides the
 * user with a menu to select from; The user can view account info, withdraw or
 * deposit;
 *
 * @author Martha Czerwik
 */
public class ATM {

    /**
     * Main method prompts user to enter ID which opens the menu method; Passes
     * account parameters or user ID to menu method
     * @param args
     */
    public static void main(String[] args) {

        //instances of object variables
        Account account101 = new Account("Martha Czerwik", 101);
        Account account102 = new Account("Thom Yorke", 102);
        ATM atmAccount = new ATM();

        account101.setBalance(50);
        account102.setBalance(1000);
        account101.setAnnualInterestRate(5);
        account102.setAnnualInterestRate(5);

        // Loop to keep ATM user ID prompt ongoing, does not accept invalid IDs
        boolean continueId = true;
        while (continueId) {
            Scanner in = new Scanner(System.in);
            try {
                System.out.println("Please enter ID number.");
                int enteredId = in.nextInt();
                switch (enteredId) {

                    case 101:
                        atmAccount.menu(account101);
                        break;
                    case 102:
                        atmAccount.menu(account102);
                        break;
                    default:
                        System.out.println("Invalid ID.\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Error: ID must be in digit format.");
            }
        }
    }

    /**
     * Menu method provides the user with a menu of options to select from;
     * Options 1, 2 and 3 let user view account info, withdraw or deposit;
     * Option 4 exits the menu loop and returns to the main method
     *
     * @param acct Account associated with ID which is passed to the menu method
     * @exception IllegalArgumentException Input from user cannot be negative,
     * 0, or cannot exceed the balance if withdrawing
     * @exception InputMismatchException Input from user can only be of type
     * double, any other characters will not be accepted
     */
    public void menu(Account acct) {

        int userOption;
        double amountToWithdraw;
        double deposit;
        String menu = "\nMain Menu\n" + "1. Account Info\n" + "2. Withdraw\n"
                    + "3. Deposit\n" + "4. Exit\n" + "Please choose what you’d like to do: ";

        do {
            Scanner in = new Scanner(System.in);
            System.out.print(menu);
            
            while (!in.hasNextInt()) {        
                in.next(); // Read and discard offending non-int input
                System.out.println("Invalid input."); // Re-prompt
                System.out.print(menu);
            }
            
            userOption = in.nextInt();
            System.out.println();

            try {
                switch (userOption) {
                    case 1:
                        System.out.println(acct.accountInfo());
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: \n$");
                        amountToWithdraw = in.nextDouble();
                        acct.withdraw(amountToWithdraw);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: \n$");
                        deposit = in.nextDouble();
                        acct.deposit(deposit);
                        break;
                    default:
                        if (userOption != 4)
                        System.out.println("Invalid input.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (InputMismatchException ime) {
                System.out.println("Error: Amount must be in digit format.");
            }
        } while (userOption != 4);
        System.out.print("Thank you for using the ATM.\n\n");
    }
}


//https://stackoverflow.com/questions/13115266/how-to-insist-that-a-users-input-is-an-int line 79-86