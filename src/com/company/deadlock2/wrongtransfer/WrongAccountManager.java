package com.company.deadlock2.wrongtransfer;

import com.company.deadlock2.Account;
import com.company.deadlock2.InsufficientFundsException;

/* Deadlock may occur because it the locking order depends on
* the order of arguments passed to the method, something we
* have no control over. */
public class WrongAccountManager {
    public void transferMoney(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException {
        synchronized (fromAccount) {
            /*try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (toAccount) {
                if (fromAccount.getBalance() < amount)
                    throw new InsufficientFundsException();
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }
}
