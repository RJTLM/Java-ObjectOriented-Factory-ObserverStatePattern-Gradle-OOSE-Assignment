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

    public Bike()
    {
        // Initialize the bike with the available state
        this.state = new AvailableState();
    }

    public BikeState getState()
    {
        return state;
    }

    public void setState(BikeState state)
    {
        this.state = state;
    }

    public String getAssociatedEmail()
    {
        return associatedEmail;
    }

    public void setAssociatedEmail(String email)
    {
        this.associatedEmail = email;
    }

    public void dropOff()
    {
        state.dropOff(this);
    }

    public void pickUp()
    {
        state.pickUp(this);
    }

    public void purchase()
    {
        state.purchase(this);
    }

    @Override
    public String toString()
    {
        return "Bike{" +
                "state=" + state +
                ", associatedEmail='" + associatedEmail + '\'' +
                '}';
    }
}
