package com.company.deadlock3;

import com.company.deadlock2.Account;
import com.company.deadlock2.InsufficientFundsException;
import com.company.deadlock2.righttransfer.RightAccountManager;
import com.company.deadlock2.wrongtransfer.WrongAccountManager;

import java.util.Random;

public class DemonstratedDeadlock {

    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {

        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++){
            accounts[i] = new Account();
        }

        RightAccountManager accountManager = new RightAccountManager(); //does not deadlock
//        WrongAccountManager accountManager = new WrongAccountManager(); //deadlocks

        class TransferThread extends Thread {
            public void run() {
                for (int i=0; i<NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    double amount = rnd.nextInt(1000);
                    try {
                        accountManager.transferMoney(accounts[fromAcct],
                                accounts[toAcct], amount);
                        System.out.println(accounts[fromAcct].getBalance());
                        System.out.println(accounts[toAcct].getBalance());
                    } catch (InsufficientFundsException e) {
                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }
}