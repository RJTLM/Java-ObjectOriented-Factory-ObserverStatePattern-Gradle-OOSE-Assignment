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
        // Bikes cannot be dropped off if they are already being serviced
        throw new UnsupportedOperationException("Bike is already being serviced.");
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
        // Bikes cannot be purchased if they are being serviced
        throw new UnsupportedOperationException("Bike is being serviced.");
    }
}
