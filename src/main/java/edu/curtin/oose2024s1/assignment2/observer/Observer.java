package edu.curtin.oose2024s1.assignment2.observer;

/*
Purpose:
    - Defines the contract for observer objects that need to be notified of changes.
Role:
    - Provides a method to update the observer, used by the observable to notify observers of changes.
 */
// Interface for observers.
public interface Observer {
    /*
    METHOD: update
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Called by the observable to notify the observer of changes.
    */
    void update();
}
