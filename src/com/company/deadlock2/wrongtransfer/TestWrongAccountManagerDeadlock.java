package com.company.deadlock2.wrongtransfer;

import com.company.deadlock2.Account;
import com.company.deadlock2.InsufficientFundsException;

public class TestWrongAccountManagerDeadlock {

    public static void main(String[] args) throws InterruptedException {

        WrongAccountManager accountManager = new WrongAccountManager();
        Account fromAccount = new Account();
        Account toAccount = new Account();

        System.out.println(fromAccount.getBalance());
        System.out.println(toAccount.getBalance());

        Thread thread1 = new Thread(() -> {
            try {
                accountManager.transferMoney(fromAccount, toAccount, 50.0);
            } catch (InsufficientFundsException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                accountManager.transferMoney(toAccount, fromAccount, 50.0);
            } catch (InsufficientFundsException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(fromAccount.getBalance());
        System.out.println(toAccount.getBalance());

    }
}
