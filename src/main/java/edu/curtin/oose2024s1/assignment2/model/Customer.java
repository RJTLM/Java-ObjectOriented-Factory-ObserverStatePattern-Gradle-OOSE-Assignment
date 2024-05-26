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

    private String email;
    private List<Bike> bikes;

    public Customer(String email)
    {
        this.email = email;
        this.bikes = new ArrayList<>();
    }

    public String getEmail()
    {
        return email;
    }

    public List<Bike> getBikes()
    {
        return bikes;
    }

    public void addBike(Bike bike)
    {
        bikes.add(bike);
        logger.info("Bike added to customer " + email + ": " + bike);
    }

    public void removeBike(Bike bike)
    {
        bikes.remove(bike);
        logger.info("Bike removed from customer " + email + ": " + bike);
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "email='" + email + '\'' +
                ", bikes=" + bikes +
                '}';
    }
}
