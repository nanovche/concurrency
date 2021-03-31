package com.company.deadlock2;

public class Account {

    private double balance = 1000.0;

    public void debit(double amount) {
        balance -= amount;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return this.balance;
    }

}
