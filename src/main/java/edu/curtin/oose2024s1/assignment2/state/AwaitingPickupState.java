package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

/*
Purpose:
    - Implement the behavior for bikes that are awaiting pickup.
Role:
    - Handle state-specific logic for bikes awaiting pickup.
    - Transition bikes to Available state after pickup.
*/
// Concrete state for bikes that are awaiting pickup.
public class AwaitingPickupState implements BikeState
{
    /*
    METHOD: dropOff
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Throws an IllegalStateException because dropping off a bike is not valid in the awaiting pickup state.
    */
    @Override
    public void dropOff(Bike bike)
    {
        throw new IllegalStateException("Bike cannot be dropped off when it is awaiting pickup.");
    }

    /*
   METHOD: pickUp
   IMPORT: bike (Bike)
   EXPORT: None
   ALGORITHM:
   Transitions the bike to the available state and disassociates any email.
   */
    @Override
    public void pickUp(Bike bike)
    {
        bike.setState(new AvailableState());
        bike.setAssociatedEmail(null);
    }

    /*
    METHOD: purchase
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Throws an IllegalStateException because purchasing a bike is not valid in the awaiting pickup state.
    */
    @Override
    public void purchase(Bike bike)
    {
        throw new IllegalStateException("Bike is already purchased.");
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
        return "Awaiting Pickup";
    }
}
