package models;
import java.time.LocalDateTime;
import java.util.*;

import exceptions.InsufficientBalanceException;

public class Account {
    private int id;
    private String name;
    private double initialBalance;
    private List<Transaction> transactions;
    public Account(int id, String name,double initialBalance) {
        this.id = id;
        this.name = name;
        this.initialBalance =initialBalance;
        this.transactions=new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public synchronized double getBalance() {
        return initialBalance;
    }
    public synchronized void deposit(double amount){
        initialBalance+=amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT,amount,LocalDateTime.now()));
    }
    public synchronized void withdraw (double amount) throws InsufficientBalanceException{
        if (amount > initialBalance) {
        throw new InsufficientBalanceException("Insufficient funds");
        }
        initialBalance-=amount;
        transactions.add(new Transaction(TransactionType.WITHDRAW,amount,LocalDateTime.now()));
    }
    public List<Transaction> getTransaction() {
        return Collections.unmodifiableList(transactions);
    }
    
}
