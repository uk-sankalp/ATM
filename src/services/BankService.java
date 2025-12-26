package services;
import java.util.*;
import models.*;
import exceptions.*;

public class BankService {
    private Map<Integer,Account> allAccounts;
    public BankService(){
        this.allAccounts=new HashMap<>();
    }
    public void createAccount(int id,String name,double initialBalance) throws AccountAlreadyExistsException{
        if(allAccounts.containsKey(id)){
            throw new AccountAlreadyExistsException("Account with ID " + id + " already exists.");
        }
        Account ac=new Account(id,name, initialBalance);
        allAccounts.put(id,ac);
    }

    public Account fetchAccountById(int id) throws AccountNotFoundException{
        if (!allAccounts.containsKey(id)) {
            throw new AccountNotFoundException("Account ID " + id + " not found.");
        }
        return allAccounts.get(id);
    }
    public boolean accountExists(int id) {
        return allAccounts.containsKey(id);
    }
    public Collection<Account> getAllAccounts() {
        return allAccounts.values();
    }

}
