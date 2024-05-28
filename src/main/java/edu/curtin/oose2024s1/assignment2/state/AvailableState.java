package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

/*
Purpose:
    - Implement the behavior for bikes that are available for purchase.
Role:
    - Handle state-specific logic for available bikes.
    - Transition bikes to BeingServiced or AwaitingPickup states.
*/
// Concrete state for bikes that are available.
public class AvailableState implements BikeState
{
    /*
    METHOD: dropOff
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Transitions the bike to the servicing state and disassociates any email.
    */
    @Override
    public void dropOff(Bike bike)
    {
        bike.setState(new ServicingState());
        bike.setAssociatedEmail(null);
    }

    /*
    METHOD: pickUp
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Throws an IllegalStateException because picking up a bike is not valid in the available state.
    */
    @Override
    public void pickUp(Bike bike)
    {
        throw new IllegalStateException("Bike cannot be picked up when it is available.");
    }

    /*
    METHOD: purchase
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Transitions the bike to the awaiting pickup state.
    */
    @Override
    public void purchase(Bike bike)
    {
        bike.setState(new AwaitingPickupState());
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
        return "Available";
    }
}
