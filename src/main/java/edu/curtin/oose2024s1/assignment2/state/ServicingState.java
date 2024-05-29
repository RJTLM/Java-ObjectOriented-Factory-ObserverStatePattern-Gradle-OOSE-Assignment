package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

/*
Purpose:
    - Implement the behavior for bikes that are being serviced.
Role:
    - Handle state-specific logic for bikes in servicing.
    - Transition bikes to Available state after servicing is complete.
*/
// Concrete state for bikes that are being serviced.
public class ServicingState implements BikeState
{
    /*
    METHOD: dropOff
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Throws an exception as dropping off a bike is not a valid operation when the bike is already being serviced.
    */
    @Override
    public void dropOff(Bike bike)
    {
        throw new IllegalStateException("Bike is already being serviced.");
    }

    /*
    METHOD: pickUp
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Transitions the bike to the Available state and clears the associated email.
    */
    @Override
    public void pickUp(Bike bike)
    {
        bike.setState(new AwaitingPickupState());
    }

    /*
    METHOD: purchase
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Throws an exception as purchasing a bike is not a valid operation when the bike is being serviced.
    */
    @Override
    public void purchase(Bike bike)
    {
        throw new IllegalStateException("Bike cannot be purchased when it is being serviced.");
    }

    /*
    METHOD: toString
    IMPORT: None
    EXPORT: state (String)
    ALGORITHM:
    Returns the string representation of the state.
    */
    @Override
    public String toString()
    {
        return "Being Serviced";
    }
}
