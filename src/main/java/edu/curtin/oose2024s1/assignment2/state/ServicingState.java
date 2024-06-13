package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

import java.util.logging.Logger;

/**
 Purpose:
 - Implement the behavior for bikes that are being serviced.
 Role:
 - Handle state-specific logic for bikes in servicing.
 - Transition bikes to Available state after servicing is complete.
 */
// Concrete state for bikes that are being serviced.
public class ServicingState implements BikeState
{
    private static final Logger logger = Logger.getLogger(ServicingState.class.getName());

    /**
     METHOD: dropOff
     IMPORT: bike (Bike)
     EXPORT: None
     ALGORITHM:
     Throws an exception as dropping off a bike is not a valid operation when the bike is already being serviced.
     */
    @Override
    public void dropOff(Bike bike)
    {
        logger.warning("Attempt to drop off a bike in the servicing state.");
        throw new IllegalStateException("Bike is already being serviced.");
    }

    /**
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
        logger.info(() -> "Bike picked up. New state: " + bike.getState());
    }

    /**
     METHOD: purchase
     IMPORT: bike (Bike)
     EXPORT: None
     ALGORITHM:
     Throws an exception as purchasing a bike is not a valid operation when the bike is being serviced.
     */
    @Override
    public void purchase(Bike bike)
    {
        logger.warning("Attempt to purchase a bike in the servicing state.");
        throw new IllegalStateException("Bike cannot be purchased when it is being serviced.");
    }

    /**
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
