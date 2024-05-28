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
    private final List<Observer> observers = new ArrayList<>();

    /*
    METHOD: BankAccount
    IMPORT: initialBalance (double)
    EXPORT: None
    ALGORITHM:
    Constructor initialises the bank account with an initial balance.
    */
    public BankAccount(double initialBalance)
    {
        this.balance = initialBalance;
    }

    /*
    METHOD: getBalance
    IMPORT: None
    EXPORT: balance (double)
    ALGORITHM:
    Returns the current balance of the bank account.
    */
    public double getBalance()
    {
        return balance;
    }

    /*
    METHOD: deposit
    IMPORT: amount (double)
    EXPORT: None
    ALGORITHM:
    Adds the specified amount to the balance and notifies observers of the change.
    */
    public void deposit(double amount)
    {
        balance += amount;
        logger.info(() -> "Deposited: " + amount + ", New Balance: " + balance);
        notifyObservers();
    }

    /*
   METHOD: withdraw
   IMPORT: amount (double)
   EXPORT: None
   ALGORITHM:
   Subtracts the specified amount from the balance if funds are sufficient, otherwise logs a warning. Notifies observers if withdrawal is successful.
   */
    public void withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            logger.info(() -> "Withdrew: " + amount + ", New Balance: " + balance);
            notifyObservers();
        }
        else
        {
            logger.warning(() -> "Insufficient funds for withdrawal: " + amount);
        }
    }

    /*
    METHOD: addObserver
    IMPORT: observer (Observer)
    EXPORT: None
    ALGORITHM:
    Adds an observer to the list of observers.
    */
    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    /*
   METHOD: removeObserver
   IMPORT: observer (Observer)
   EXPORT: None
   ALGORITHM:
   Removes an observer from the list of observers.
   */
    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    /*
    METHOD: notifyObservers
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Notifies all registered observers of changes by calling their update method.
    */
    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
