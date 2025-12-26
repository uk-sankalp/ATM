package services;
import models.*;
import exceptions.*;
import java.time.LocalDateTime;

public class AccountService {
    private BankService bankservice;
    public AccountService(BankService bankservice){
        this.bankservice=bankservice;
    }
    //Deposite Money
    public synchronized void deposit(int acc_id,double amount) throws AccountNotFoundException{
        Account ac=bankservice.fetchAccountById(acc_id);
        ac.deposit(amount);
        ac.getTransaction().add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));
        System.out.println("Deposited ₹" + amount + " to Account ID " + acc_id);
    }
    //Withdraw Money
    public synchronized void withdraw(int accountId, double amount){
        try{
        Account account = bankservice.fetchAccountById(accountId);
        account.withdraw(amount);
        account.getTransaction().add(new Transaction(TransactionType.WITHDRAW, amount, LocalDateTime.now()));
        System.out.println("Withdrew ₹" + amount + " from Account ID " + accountId);
        }
        catch(Exception e){
            System.out.println("Not Sufficient Balance");
        }
        
    }
    //Transfer Money From One Account to Other
    public synchronized void transfer(int fromId, int toId, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {

        Account fromAccount = bankservice.fetchAccountById(fromId);
        Account toAccount = bankservice.fetchAccountById(toId);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        fromAccount.getTransaction().add(new Transaction(TransactionType.WITHDRAW, amount, LocalDateTime.now()));
        toAccount.getTransaction().add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));

        System.out.println("Transferred ₹" + amount + " from Account " + fromId + " to Account " + toId);
    }
    //Get Balance
    public double getBalance(int accountId) throws AccountNotFoundException {
        Account account = bankservice.fetchAccountById(accountId);
        return account.getBalance();
    }
    //Get Transaction History
    public void printTransactionHistory(int accountId) throws AccountNotFoundException {
        Account account = bankservice.fetchAccountById(accountId);
        System.out.println("Transaction History for Account ID " + accountId + ":");
        account.getTransaction().forEach(System.out::println);
    }

}
