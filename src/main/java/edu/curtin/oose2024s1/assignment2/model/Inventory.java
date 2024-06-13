package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.observer.Observable;
import edu.curtin.oose2024s1.assignment2.observer.Observer;
import edu.curtin.oose2024s1.assignment2.state.AwaitingPickupState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 Purpose:
 - Manages the collection of bikes in the shop.
 Responsibilities:
 - Keep track of all bikes in different states (available, being serviced, awaiting pickup).
 - Manage adding and removing bikes from the inventory.
 Reference:
 - Dewan, P. (2000). 15. Model-View-Controller (MVC) and Observer. University of North Carolina Computer Science at Chapel Hill. https://www.cs.unc.edu/~carterjl/teaching/notes/15_MVC_Notes.pdf - Used to help understand and implement observer pattern (only used for contextual based knowledge and further understanding of observer pattern).
 */
// Manages the bike inventory.
public class Inventory implements Observable
{
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());

    private final List<Bike> availableBikes;
    private final List<Bike> servicedBikes;
    private final List<Bike> awaitingPickupBikes;
    private final List<Observer> observers = new ArrayList<>();

    /**
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
        logger.info("Inventory initialised.");
    }

    /**
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

    /**
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
        notifyObservers();
    }

    /**
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

    /**
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

    /**
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

    /**
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

    /**
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

    /**
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

    /**
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

    /**
     METHOD: incrementDaysInServicingState
     IMPORT: None
     EXPORT: None
     ALGORITHM:
     Increments the days in servicing state for all serviced bikes.
     */
    public void incrementDaysInServicingState()
    {
        Iterator<Bike> iterator = servicedBikes.iterator();
        while (iterator.hasNext())
        {
            Bike bike = iterator.next();
            bike.incrementDaysInServicingState();
            if (bike.getState() instanceof AwaitingPickupState)
            {
                iterator.remove();
                addAwaitingPickupBike(bike);
                logger.info(() -> "Bike transitioned from servicing to awaiting pickup: " + bike);
            }
        }
        notifyObservers();
    }

    /**
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
        logger.info(() -> "Observer added: " + observer);
    }

    /**
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
        logger.info(() -> "Observer removed: " + observer);
    }

    /**
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
