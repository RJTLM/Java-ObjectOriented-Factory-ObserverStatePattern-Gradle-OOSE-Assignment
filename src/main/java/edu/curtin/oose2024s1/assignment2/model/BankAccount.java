package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.observer.Observable;
import edu.curtin.oose2024s1.assignment2.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
Purpose:
    - Represents the financial state of the bike shop, including the balance and financial transactions.
Responsibilities:
    - Track the current balance.
    - Handle transactions such as paying the employee and purchasing bikes.
 Reference:
 - Dewan, P. (2000). 15. Model-View-Controller (MVC) and Observer. University of North Carolina Computer Science at Chapel Hill. https://www.cs.unc.edu/~carterjl/teaching/notes/15_MVC_Notes.pdf - Used to help understand and implement observer pattern (only used for contextual based knowledge and further understanding of observer pattern).
*/
// Manages the shop's bank account.
public class BankAccount implements Observable
{
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());

    private int balance;
    private final List<Observer> observers = new ArrayList<>();

    /**
    METHOD: BankAccount
    IMPORT: initialBalance (double)
    EXPORT: None
    ALGORITHM:
    Constructor initialises the bank account with an initial balance.
    */
    public BankAccount(int initialBalance)
    {
        this.balance = initialBalance;
        logger.info(() -> "Bank account created with initial balance: " + initialBalance);
    }

    /**
    METHOD: getBalance
    IMPORT: None
    EXPORT: balance (double)
    ALGORITHM:
    Returns the current balance of the bank account.
    */
    public int getBalance()
    {
        return balance;
    }

    /**
    METHOD: deposit
    IMPORT: amount (double)
    EXPORT: None
    ALGORITHM:
    Adds the specified amount to the balance and notifies observers of the change.
    */
    public void deposit(int amount)
    {
        balance += amount;
        logger.info(() -> "Deposited: " + amount + ", New Balance: " + balance);
        notifyObservers();
    }

    /**
    METHOD: withdraw
    IMPORT: amount (int)
    EXPORT: None
    ALGORITHM:
    Calls the withdraw method with isEmployeePayment set to false.
    */
    public void withdraw(int amount)
    {
        withdraw(amount, false); // Default case, not employee payment
    }

    /**
    METHOD: withdraw
    IMPORT: amount (int), isEmployeePayment (boolean)
    EXPORT: None
    ALGORITHM:
    Subtracts the specified amount from the balance. If the amount is for employee payment, it allows the balance to go negative.
    Otherwise, it logs a warning if funds are insufficient. Notifies observers if withdrawal is successful.
    */
    public void withdraw(int amount, boolean isEmployeePayment)
    {
        if (isEmployeePayment || balance >= amount)
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

    /**
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
        logger.info(() -> "Observer added: " + observer);
    }

    /**
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
        logger.info(() -> "Observer removed: " + observer);
    }

    /**
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
