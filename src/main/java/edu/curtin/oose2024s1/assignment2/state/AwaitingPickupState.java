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
    @Override
    public void dropOff(Bike bike)
    {
        // This operation is not valid in this state
        throw new IllegalStateException("Bike cannot be dropped off when it is awaiting pickup.");
    }

    @Override
    public void pickUp(Bike bike)
    {
        // Transition to available state
        bike.setState(new AvailableState());
        bike.setAssociatedEmail(null);
    }

    @Override
    public void purchase(Bike bike)
    {
        // This operation is not valid in this state
        throw new IllegalStateException("Bike is already purchased.");
    }

    @Override
    public String toString()
    {
        return "Awaiting Pickup";
    }
}
