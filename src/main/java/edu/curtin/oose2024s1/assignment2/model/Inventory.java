package edu.curtin.oose2024s1.assignment2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
Purpose:
    - Manages the collection of bikes in the shop.
Responsibilities:
    - Keep track of all bikes in different states (available, being serviced, awaiting pickup).
    - Manage adding and removing bikes from the inventory.
 */
// Manages the bike inventory.
public class Inventory
{
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());

    private List<Bike> availableBikes;
    private List<Bike> servicedBikes;
    private List<Bike> awaitingPickupBikes;

    public Inventory()
    {
        availableBikes = new ArrayList<>();
        servicedBikes = new ArrayList<>();
        awaitingPickupBikes = new ArrayList<>();
    }

    public List<Bike> getAvailableBikes()
    {
        return availableBikes;
    }

    public List<Bike> getServicedBikes()
    {
        return servicedBikes;
    }

    public List<Bike> getAwaitingPickupBikes()
    {
        return awaitingPickupBikes;
    }

    public void addAvailableBike(Bike bike)
    {
        availableBikes.add(bike);
        logger.info("Bike added to available: " + bike);
    }

    public void removeAvailableBike(Bike bike)
    {
        availableBikes.remove(bike);
        logger.info("Bike removed from available: " + bike);
    }

    public void addServicedBike(Bike bike)
    {
        servicedBikes.add(bike);
        logger.info("Bike added to serviced: " + bike);
    }

    public void removeServicedBike(Bike bike)
    {
        servicedBikes.remove(bike);
        logger.info("Bike removed from serviced: " + bike);
    }

    public void addAwaitingPickupBike(Bike bike)
    {
        awaitingPickupBikes.add(bike);
        logger.info("Bike added to awaiting pickup: " + bike);
    }

    public void removeAwaitingPickupBike(Bike bike)
    {
        awaitingPickupBikes.remove(bike);
        logger.info("Bike removed from awaiting pickup: " + bike);
    }

    public int getAvailableBikeCount()
    {
        return availableBikes.size();
    }

    public int getServicedBikeCount()
    {
        return servicedBikes.size();
    }

    public int getAwaitingPickupBikeCount()
    {
        return awaitingPickupBikes.size();
    }
}