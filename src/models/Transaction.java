package models;
import java.time.LocalDateTime;

public class Transaction {
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;
public Transaction(TransactionType type,double amount,LocalDateTime timestamp){
    this.type=type;
    this.amount=amount;
    this.timestamp=timestamp;
}
public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String toString(){
        return type + " of ₹" + amount + "at" + timestamp;
    }
}
