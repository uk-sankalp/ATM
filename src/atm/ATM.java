package atm;
import models.*;
import services.*;
import exceptions.*;
import java.util.*;

public class ATM {

    private BankService bankService;
    private AccountService accountService;
    private TransactionService transactionService;
    private Scanner scanner;

    public ATM() {
        this.bankService = new BankService();
        this.accountService = new AccountService(bankService);
        this.transactionService = new TransactionService();
        this.scanner = new Scanner(System.in);
    }
    private void mainMenu() {
    while (true) {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Create Account");
        System.out.println("2. Login to Account");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        switch (choice) {
            case 1:
                createAccountMenu();
                break;
            case 2:
                loginMenu();
                break;
            case 3:
                System.out.println("Thank you for using the ATM!");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}
private void createAccountMenu() {
    System.out.print("Enter Account ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Initial Balance: ");
    double balance = scanner.nextDouble();

    try {
        bankService.createAccount(id, name, balance);
        System.out.println("Account created successfully!");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

private void loginMenu() {
    System.out.print("Enter Account ID: ");
    int id = scanner.nextInt();

    try {
        accountOperationsMenu(id);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

private void accountOperationsMenu(int accountId) {
    while (true) {
        System.out.println("\n===== ACCOUNT MENU =====");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. View Transactions");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double d = scanner.nextDouble();
                    accountService.deposit(accountId, d);
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double w = scanner.nextDouble();
                    accountService.withdraw(accountId, w);
                    break;

                case 3:
                    System.out.print("Enter Target Account ID: ");
                    int toId = scanner.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = scanner.nextDouble();
                    accountService.transfer(accountId, toId, amt);
                    break;

                case 4:
                    System.out.println("Balance: " + accountService.getBalance(accountId));
                    break;

                case 5:
                    accountService.printTransactionHistory(accountId);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



    public void start() {
        System.out.println("===== WELCOME TO ATM SIMULATOR =====");
        mainMenu();
    }
}

