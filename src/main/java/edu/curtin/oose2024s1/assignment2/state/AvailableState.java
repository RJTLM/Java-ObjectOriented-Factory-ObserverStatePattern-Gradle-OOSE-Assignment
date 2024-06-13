package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

import java.util.logging.Logger;

/**
 Purpose:
 - Implement the behavior for bikes that are available for purchase.
 Role:
 - Handle state-specific logic for available bikes.
 - Transition bikes to BeingServiced or AwaitingPickup states.
 Reference:
 - Geekific. (2020, June 6). Observer design pattern – Design patterns in plain English [Video]. YouTube. https://www.youtube.com/watch?v=abX4xzaAsoc - Used to help understand and implement state pattern (only used for contextual based knowledge and further understanding of state pattern).
 */
// Concrete state for bikes that are available.
public class AvailableState implements BikeState
{
    private static final Logger logger = Logger.getLogger(AvailableState.class.getName());

    /**
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
        logger.info(() -> "Bike dropped off for servicing. New state: " + bike.getState());
    }

    /**
     METHOD: pickUp
     IMPORT: bike (Bike)
     EXPORT: None
     ALGORITHM:
     Throws an IllegalStateException because picking up a bike is not valid in the available state.
     */
    @Override
    public void pickUp(Bike bike)
    {
        logger.warning("Attempt to pick up a bike in the available state.");
        throw new IllegalStateException("Bike cannot be picked up when it is available.");
    }

    /**
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
        return "Available";
    }
}
