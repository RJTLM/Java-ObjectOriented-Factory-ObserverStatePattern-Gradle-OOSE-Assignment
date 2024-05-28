package edu.curtin.oose2024s1.assignment2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
Purpose:
    - Represents a customer who interacts with the bike shop.
Responsibilities:
    - Store customer information, such as email address.
    - Track bikes associated with the customer (dropped off for service or purchased online).
 */
// Represents a customer.
public class Customer
{
    private static final Logger logger = Logger.getLogger(Customer.class.getName());

    private final String email;
    private final List<Bike> bikes;

    /*
    METHOD: Customer
    IMPORT: email (String)
    EXPORT: None
    ALGORITHM:
    Constructor initialises the customer with an email and an empty list of bikes.
    */
    public Customer(String email)
    {
        this.email = email;
        this.bikes = new ArrayList<>();
    }

    /*
    METHOD: getEmail
    IMPORT: None
    EXPORT: email (String)
    ALGORITHM:
    Returns the email address of the customer.
    */
    public String getEmail()
    {
        return email;
    }

    /*
    METHOD: getBikes
    IMPORT: None
    EXPORT: bikes (List<Bike>)
    ALGORITHM:
    Returns the list of bikes associated with the customer.
    */
    public List<Bike> getBikes()
    {
        return bikes;
    }

    /*
    METHOD: addBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Adds the specified bike to the customer's list of bikes.
    Logs the action.
    */
    public void addBike(Bike bike)
    {
        bikes.add(bike);
        logger.info(() -> "Bike added to customer " + email + ": " + bike);
    }

    /*
    METHOD: removeBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Removes the specified bike from the customer's list of bikes.
    Logs the action.
    */
    public void removeBike(Bike bike)
    {
        bikes.remove(bike);
        logger.info(() -> "Bike removed from customer " + email + ": " + bike);
    }

    /*
    METHOD: toString
    IMPORT: None
    EXPORT: String
    ALGORITHM:
    Returns a string representation of the customer, including their email and list of bikes.
    */
    @Override
    public String toString()
    {
        return "Customer{" +
                "email='" + email + '\'' +
                ", bikes=" + bikes +
                '}';
    }
}
