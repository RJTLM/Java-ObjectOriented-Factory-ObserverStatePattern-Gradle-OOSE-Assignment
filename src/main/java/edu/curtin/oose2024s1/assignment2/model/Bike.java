package edu.curtin.oose2024s1.assignment2.model;

/*
Purpose:
    - Represents an individual bike in the shop.
Responsibilities:
    - Track the status of the bike (available, being serviced, awaiting pickup).
    - Associate the bike with a customer if it is being serviced or has been purchased online.
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

    public Bike(Status status)
    {
        this.status = status;
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

    @Override
    public String toString()
    {
        return "Bike{" +
                "status=" + status +
                ", associatedEmail='" + associatedEmail + '\'' +
                '}';
    }
}