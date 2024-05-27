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
        // Bikes cannot be dropped off if they are awaiting pickup
        throw new UnsupportedOperationException("Bike is awaiting pickup.");
    }

    @Override
    public void pickUp(Bike bike)
    {
        bike.setState(new AvailableState());
        bike.setStatus(Bike.Status.AVAILABLE);
        // Additional logic for pick-up can be added here
    }

    @Override
    public void purchase(Bike bike)
    {
        // Bikes cannot be purchased if they are awaiting pickup
        throw new UnsupportedOperationException("Bike is awaiting pickup.");
    }
}
