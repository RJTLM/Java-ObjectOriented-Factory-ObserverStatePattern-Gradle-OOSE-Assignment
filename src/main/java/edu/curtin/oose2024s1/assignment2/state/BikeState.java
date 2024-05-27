package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

/*
Purpose:
    - Define the operations that each bike state must implement.
Role:
    - Provide a contract for state-specific behavior in the Bike class.
*/
// Interface for bike states.
public interface BikeState
{
    void dropOff(Bike bike);
    void pickUp(Bike bike);
    void purchase(Bike bike);
}
