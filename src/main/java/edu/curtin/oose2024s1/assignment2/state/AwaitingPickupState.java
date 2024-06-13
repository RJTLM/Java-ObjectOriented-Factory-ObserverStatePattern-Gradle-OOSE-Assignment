package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

import java.util.logging.Logger;

/**
 Purpose:
 - Implement the behavior for bikes that are awaiting pickup.
 Role:
 - Handle state-specific logic for bikes awaiting pickup.
 - Transition bikes to Available state after pickup.
 */
// Concrete state for bikes that are awaiting pickup.
public class AwaitingPickupState implements BikeState
{
    private static final Logger logger = Logger.getLogger(AwaitingPickupState.class.getName());

    /**
     METHOD: dropOff
     IMPORT: bike (Bike)
     EXPORT: None
     ALGORITHM:
     Throws an IllegalStateException because dropping off a bike is not valid in the awaiting pickup state.
     */
    @Override
    public void dropOff(Bike bike)
    {
        logger.warning("Attempt to drop off a bike in the awaiting pickup state.");
        throw new IllegalStateException("Bike cannot be dropped off when it is awaiting pickup.");
    }

    /**
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
        logger.info(() -> "Bike picked up. New state: " + bike.getState());
    }

    /**
     METHOD: purchase
     IMPORT: bike (Bike)
     EXPORT: None
     ALGORITHM:
     Throws an IllegalStateException because purchasing a bike is not valid in the awaiting pickup state.
     */
    @Override
    public void purchase(Bike bike)
    {
        logger.warning("Attempt to purchase a bike in the awaiting pickup state.");
        throw new IllegalStateException("Bike is already purchased.");
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
        return "Awaiting Pickup";
    }
}
