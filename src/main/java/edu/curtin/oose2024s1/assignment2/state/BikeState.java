package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

/**
Purpose:
    - Define the operations that each bike state must implement.
Role:
    - Provide a contract for state-specific behavior in the Bike class.
*/
// Interface for bike states.
public interface BikeState
{
    /**
    METHOD: dropOff
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Defines the behavior for dropping off a bike in a specific state.
    */
    void dropOff(Bike bike);

    /**
    METHOD: pickUp
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Defines the behavior for picking up a bike in a specific state.
    */
    void pickUp(Bike bike);

    /**
    METHOD: purchase
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Defines the behavior for purchasing a bike in a specific state.
    */
    void purchase(Bike bike);
}
