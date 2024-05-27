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
    public enum Status
    {
        AVAILABLE, BEING_SERVICED, AWAITING_PICKUP
    }

    private Status status;
    private String associatedEmail;
    private BikeState state;

    public Bike(Status available)
    {
        this.state = new AvailableState();
        this.status = Status.AVAILABLE;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public String getAssociatedEmail()
    {
        return associatedEmail;
    }

    public void setAssociatedEmail(String email)
    {
        this.associatedEmail = email;
    }

    public void setState(BikeState state)
    {
        this.state = state;
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
                "status=" + status +
                ", associatedEmail='" + associatedEmail + '\'' +
                '}';
    }
}
