package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.observer.Observable;
import edu.curtin.oose2024s1.assignment2.observer.Observer;

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
public class Inventory implements Observable
{
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());

    private List<Bike> availableBikes;
    private List<Bike> servicedBikes;
    private List<Bike> awaitingPickupBikes;
    private List<Observer> observers = new ArrayList<>();

    /*
    METHOD: Inventory
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Constructor initialises the inventory lists for available bikes, serviced bikes, and bikes awaiting pickup.
    */
    public Inventory()
    {
        availableBikes = new ArrayList<>();
        servicedBikes = new ArrayList<>();
        awaitingPickupBikes = new ArrayList<>();
    }

    /*
    METHOD: getAvailableBikes
    IMPORT: None
    EXPORT: availableBikes (List<Bike>)
    ALGORITHM:
    Returns the list of available bikes.
    */
    public List<Bike> getAvailableBikes()
    {
        return availableBikes;
    }

    /*
    METHOD: getServicedBikes
    IMPORT: None
    EXPORT: servicedBikes (List<Bike>)
    ALGORITHM:
    Returns the list of bikes being serviced.
    */
    public List<Bike> getServicedBikes()
    {
        return servicedBikes;
    }

    /*
    METHOD: getAwaitingPickupBikes
    IMPORT: None
    EXPORT: awaitingPickupBikes (List<Bike>)
    ALGORITHM:
    Returns the list of bikes awaiting pickup.
    */
    public List<Bike> getAwaitingPickupBikes()
    {
        return awaitingPickupBikes;
    }

    /*
    METHOD: addAvailableBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Adds a bike to the available bikes list.
    Logs the action and notifies observers.
    */
    public void addAvailableBike(Bike bike)
    {
        availableBikes.add(bike);
        logger.info(() -> "Bike added to available: " + bike);
        notifyObservers();
    }

    /*
    METHOD: removeAvailableBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Removes a bike from the available bikes list.
    Logs the action and notifies observers.
    */
    public void removeAvailableBike(Bike bike)
    {
        availableBikes.remove(bike);
        logger.info(() -> "Bike removed from available: " + bike);
        notifyObservers();
    }

    /*
    METHOD: addServicedBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Adds a bike to the serviced bikes list.
    Logs the action and notifies observers.
    */
    public void addServicedBike(Bike bike)
    {
        servicedBikes.add(bike);
        logger.info(() -> "Bike added to serviced: " + bike);
        notifyObservers();
    }

    /*
    METHOD: removeServicedBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Removes a bike from the serviced bikes list.
    Logs the action and notifies observers.
    */
    public void removeServicedBike(Bike bike)
    {
        servicedBikes.remove(bike);
        logger.info(() -> "Bike removed from serviced: " + bike);
        notifyObservers();
    }

    /*
    METHOD: addAwaitingPickupBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Adds a bike to the awaiting pickup bikes list.
    Logs the action and notifies observers.
    */
    public void addAwaitingPickupBike(Bike bike)
    {
        awaitingPickupBikes.add(bike);
        logger.info(() -> "Bike added to awaiting pickup: " + bike);
        notifyObservers();
    }

    /*
    METHOD: removeAwaitingPickupBike
    IMPORT: bike (Bike)
    EXPORT: None
    ALGORITHM:
    Removes a bike from the awaiting pickup bikes list.
    Logs the action and notifies observers.
    */
    public void removeAwaitingPickupBike(Bike bike)
    {
        awaitingPickupBikes.remove(bike);
        logger.info(() -> "Bike removed from awaiting pickup: " + bike);
        notifyObservers();
    }

    /*
    METHOD: getAvailableBikeCount
    IMPORT: None
    EXPORT: int
    ALGORITHM:
    Returns the number of available bikes.
    */
    public int getAvailableBikeCount()
    {
        return availableBikes.size();
    }

    /*
    METHOD: getServicedBikeCount
    IMPORT: None
    EXPORT: int
    ALGORITHM:
    Returns the number of bikes being serviced.
    */
    public int getServicedBikeCount()
    {
        return servicedBikes.size();
    }

    /*
    METHOD: getAwaitingPickupBikeCount
    IMPORT: None
    EXPORT: int
    ALGORITHM:
    Returns the number of bikes awaiting pickup.
    */
    public int getAwaitingPickupBikeCount()
    {
        return awaitingPickupBikes.size();
    }

    /*
    METHOD: addObserver
    IMPORT: observer (Observer)
    EXPORT: None
    ALGORITHM:
    Adds an observer to the list of observers.
    */
    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    /*
   METHOD: removeObserver
   IMPORT: observer (Observer)
   EXPORT: None
   ALGORITHM:
   Removes an observer from the list of observers.
   */
    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    /*
    METHOD: notifyObservers
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Notifies all observers of changes to the inventory.
    */
    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
