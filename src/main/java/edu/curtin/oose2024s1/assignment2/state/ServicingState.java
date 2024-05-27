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
    @Override
    public void dropOff(Bike bike)
    {
        // This operation is not valid in this state
        throw new IllegalStateException("Bike is already being serviced.");
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
        throw new IllegalStateException("Bike cannot be purchased when it is being serviced.");
    }

    @Override
    public String toString()
    {
        return "Being Serviced";
    }
}
