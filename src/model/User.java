package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int accountNumber;
    private String name;
    private String password;
    private double balance;
    private boolean isActive;
    private List<String> transactions;

    public User(int accountNumber, String name, String password, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.password = password;
        this.balance = initialDeposit;
        this.isActive = true;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public void addTransaction(String message) {
        transactions.add(message);
    }
}
