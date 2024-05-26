package edu.curtin.oose2024s1.assignment2.observer;

/*
Purpose:
    - Defines the contract for the subject that maintains a list of observers and notifies them of changes.
Role:
    - Provides methods to add, remove, and notify observers.
 */
// Interface for observable objects.
public interface Observable
{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
