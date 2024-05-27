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
        bike.setState(new ServicingState());
        bike.setStatus(Bike.Status.BEING_SERVICED);
        // Additional logic for drop-off can be added here
    }

    @Override
    public void pickUp(Bike bike)
    {
        // Bikes cannot be picked up if they are available
        throw new UnsupportedOperationException("Bike is already available.");
    }

    @Override
    public void purchase(Bike bike)
    {
        bike.setState(new AwaitingPickupState());
        bike.setStatus(Bike.Status.AWAITING_PICKUP);
        // Additional logic for purchase can be added here
    }
}
