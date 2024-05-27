package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.observer.Observable;
import edu.curtin.oose2024s1.assignment2.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
Purpose:
    - Represents the financial state of the bike shop, including the balance and financial transactions.
Responsibilities:
    - Track the current balance.
    - Handle transactions such as paying the employee and purchasing bikes.
 */
// Manages the shop's bank account.
public class BankAccount implements Observable
{
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private double balance;
    private List<Observer> observers = new ArrayList<>();

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
        notifyObservers();
    }

    public void withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            logger.info("Withdrew: " + amount + ", New Balance: " + balance);
            notifyObservers();
        }
        else
        {
            logger.warning("Insufficient funds for withdrawal: " + amount);
        }
    }

    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
