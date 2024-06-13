package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

/**
Purpose:
    - Define the operations that each bike state must implement.
Role:
    - Provide a contract for state-specific behavior in the Bike class.
Reference:
    - Geekific. (2020, June 6). Observer design pattern – Design patterns in plain English [Video]. YouTube. https://www.youtube.com/watch?v=abX4xzaAsoc - Used to help understand and implement state pattern (only used for contextual based knowledge and further understanding of state pattern).
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
