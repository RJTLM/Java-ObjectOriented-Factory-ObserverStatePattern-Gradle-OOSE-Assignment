package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.state.BikeState;
import edu.curtin.oose2024s1.assignment2.state.AvailableState;

/*
Purpose:
    - Represents an individual bike in the shop.
    - Maintain the current state of the bike and delegate state-specific behavior.
Responsibilities:
    - Track the status and associated customer email of the bike.
    - Manage state transitions and delegate behavior to the current state.
Role:
    - Act as the context in the State Pattern, holding a reference to the current state.
*/
// Represents a bike.
public class Bike
{
    private BikeState state;
    private String associatedEmail;

    /*
    METHOD: Bike
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Constructor initialises the bike with the available state.
    */
    public Bike()
    {
        this.state = new AvailableState();
    }

    /*
    METHOD: getState
    IMPORT: None
    EXPORT: state (BikeState)
    ALGORITHM:
    Returns the current state of the bike.
    */
    public BikeState getState()
    {
        return state;
    }

    /*
    METHOD: setState
    IMPORT: state (BikeState)
    EXPORT: None
    ALGORITHM:
    Sets the state of the bike to the given state.
    */
    public void setState(BikeState state)
    {
        this.state = state;
    }

    /*
    METHOD: getAssociatedEmail
    IMPORT: None
    EXPORT: associatedEmail (String)
    ALGORITHM:
    Returns the email associated with the bike.
    */
    public String getAssociatedEmail()
    {
        return associatedEmail;
    }

    /*
    METHOD: setAssociatedEmail
    IMPORT: email (String)
    EXPORT: None
    ALGORITHM:
    Sets the email associated with the bike to the given email.
    */
    public void setAssociatedEmail(String email)
    {
        this.associatedEmail = email;
    }

    /*
    METHOD: dropOff
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Delegates the drop-off action to the current state.
    */
    public void dropOff()
    {
        state.dropOff(this);
    }

    /*
    METHOD: pickUp
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Delegates the pick-up action to the current state.
    */
    public void pickUp()
    {
        state.pickUp(this);
    }

    /*
    METHOD: purchase
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Delegates the purchase action to the current state.
    */
    public void purchase()
    {
        state.purchase(this);
    }

    /*
    METHOD: toString
    IMPORT: None
    EXPORT: String
    ALGORITHM:
    Returns a string representation of the bike, including its state and associated email.
    */
    @Override
    public String toString()
    {
        return "Bike{" +
                "state=" + state +
                ", associatedEmail='" + associatedEmail + '\'' +
                '}';
    }
}
