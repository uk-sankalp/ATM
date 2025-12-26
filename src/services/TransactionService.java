package services;
import models.*;
import services.*;
import exceptions.*;
import java.util.*;

public class TransactionService {
    //print all transactions of an account
    public void printTransactions(Account account) {
        List<Transaction> transactions = account.getTransaction();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        transactions.forEach(System.out::println);
    }
    //print only deposites
    public void printDeposits(Account account) {
        account.getTransaction().stream()
.filter(t -> t.getType() == TransactionType.DEPOSIT)
.forEach(System.out::println);
    }
    //print only withdrawals
    public void printWithdrawals(Account account) {
        account.getTransaction().stream()
.filter(t -> t.getType() == TransactionType.WITHDRAW)
.forEach(System.out::println);
    }
}
