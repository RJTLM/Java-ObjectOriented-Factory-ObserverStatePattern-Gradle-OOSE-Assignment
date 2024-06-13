package edu.curtin.oose2024s1.assignment2.factory;

import edu.curtin.oose2024s1.assignment2.model.Bike;

import java.util.logging.Logger;

/**
Purpose:
    - The BikeFactory class will manage the creation of Bike instances. This centralises the object creation process, ensuring that all Bike objects are created in a consistent manner.
Role:
    - As a factory class, it simplifies the creation of Bike objects throughout the application, particularly when new bikes are added to the inventory. It abstracts the instantiation logic away from the main application logic, promoting a clean separation of concerns.
Reference:
    - JavaGuides. (2020, March 6). Factory design pattern [Video]. YouTube. https://www.youtube.com/watch?v=zgf8QD7n5qI - Used to help understand and implement bike factory (only used for contextual based knowledge and further understanding of factories).
 */
// Creates new Bike instances.
public class BikeFactory
{
    private static final Logger logger = Logger.getLogger(BikeFactory.class.getName());

    /**
    METHOD: createBike
    IMPORT: None
    EXPORT: bike (Bike)
    ALGORITHM:
    Creates and returns a new Bike instance.
    */
    public Bike createBike()
    {
        Bike bike = new Bike();
        logger.info(() -> "Created new bike: " + bike);
        return new Bike();
    }
}
