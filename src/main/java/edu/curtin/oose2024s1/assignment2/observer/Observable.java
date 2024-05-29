package edu.curtin.oose2024s1.assignment2.observer;

/**
Purpose:
    - Defines the contract for the subject that maintains a list of observers and notifies them of changes.
Role:
    - Provides methods to add, remove, and notify observers.
*/
// Interface for observable objects.
public interface Observable
{
    /**
    METHOD: addObserver
    IMPORT: observer (Observer)
    EXPORT: None
    ALGORITHM:
    Adds an observer to the list of observers.
    */
    void addObserver(Observer observer);

    /**
    METHOD: removeObserver
    IMPORT: observer (Observer)
    EXPORT: None
    ALGORITHM:
    Removes an observer from the list of observers.
    */
    void removeObserver(Observer observer);

    /**
    METHOD: notifyObservers
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Notifies all observers of changes.
    */
    void notifyObservers();
}
