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
    @Override
    public void dropOff(Bike bike)
    {
        // Transition to servicing state
        bike.setState(new ServicingState());
        bike.setAssociatedEmail(null);
    }

    @Override
    public void pickUp(Bike bike)
    {
        // This operation is not valid in this state
        throw new IllegalStateException("Bike cannot be picked up when it is available.");
    }

    @Override
    public void purchase(Bike bike)
    {
        // Transition to awaiting pickup state
        bike.setState(new AwaitingPickupState());
    }

    @Override
    public String toString()
    {
        return "Available";
    }
}
