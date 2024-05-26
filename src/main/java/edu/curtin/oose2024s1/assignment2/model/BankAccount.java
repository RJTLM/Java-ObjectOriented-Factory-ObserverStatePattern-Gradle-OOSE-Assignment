package edu.curtin.oose2024s1.assignment2.model;

import java.util.logging.Logger;

/*
Purpose:
    - Represents the financial state of the bike shop, including the balance and financial transactions.
Responsibilities:
    - Track the current balance.
    - Handle transactions such as paying the employee and purchasing bikes.
 */
// Manages the shop's bank account.
public class BankAccount
{
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private double balance;

    public BankAccount(double initialBalance)
    {
        this.balance = initialBalance;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        balance += amount;
        logger.info("Deposited: " + amount + ", New Balance: " + balance);
    }

    public void withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            logger.info("Withdrew: " + amount + ", New Balance: " + balance);
        }
        else
        {
            logger.warning("Insufficient funds for withdrawal: " + amount);
        }
    }
}