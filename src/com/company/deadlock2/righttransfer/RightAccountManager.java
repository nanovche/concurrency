package com.company.deadlock2.righttransfer;

import com.company.deadlock2.Account;
import com.company.deadlock2.InsufficientFundsException;


/*To ensure same ordering of arguments we can invoke System.identityhashcode() which returns
* the same int value as Object.hashcode(). If arguments are swapped when passed we know which
* one is which be comparing their hashcodes and invoke them correspondigly. If a tie occurs(identical
* hashcodes which happens very rare) we lock on third tie lock.   */
public class RightAccountManager {

    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct, final Account toAcct, final double amount) throws InsufficientFundsException {
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance() < amount)
                    throw new InsufficientFundsException();
                else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
        int fromHash = System.identityHashCode(fromAcct);
//        System.out.println("f hash : " + fromHash);
        int toHash = System.identityHashCode(toAcct);
//        System.out.println("t hash : " + toHash);
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                /* try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                /* try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
