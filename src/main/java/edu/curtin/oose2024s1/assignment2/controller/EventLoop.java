package edu.curtin.oose2024s1.assignment2.controller;

import edu.curtin.oose2024s1.assignment2.BikeShopInput;
import edu.curtin.oose2024s1.assignment2.model.*;
import edu.curtin.oose2024s1.assignment2.view.BikeShopView;

import java.io.IOException;
import java.util.logging.Logger;

/*
Purpose:
    - This class will handle the simulation loop, controlling the flow of the simulation based on time (1 second = 1 day in the simulation). It will interact with the BikeShopController to process incoming messages and update the state of the bike shop.
Role:
    - Ensures the simulation progresses, handles the timing, and invokes the BikeShopController to process events/messages.
 */
// Manages the event loop for the simulation.
public class EventLoop
{
    private static final Logger logger = Logger.getLogger(EventLoop.class.getName());

    private final BikeShopInput bikeShopInput;
    private final BikeShopController bikeShopController;
    private final Inventory inventory;
    private final BankAccount bankAccount;
    private final BikeShopView bikeShopView;

    /*
    METHOD: EventLoop
    IMPORT: bikeShopInput (BikeShopInput), bikeShopController (BikeShopController), inventory (Inventory), bankAccount (BankAccount), bikeShopView (BikeShopView)
    EXPORT: None
    ALGORITHM:
    Constructor that initialises the input, controller, inventory, bank account, and view, and registers observers.
    */
    public EventLoop(BikeShopInput bikeShopInput, BikeShopController bikeShopController, Inventory inventory, BankAccount bankAccount, BikeShopView bikeShopView)
    {
        this.bikeShopInput = bikeShopInput;
        this.bikeShopController = bikeShopController;
        this.inventory = inventory;
        this.bankAccount = bankAccount;
        this.bikeShopView = bikeShopView;

        // Register observers after full initialisation
        this.bikeShopView.registerObservers(bankAccount, inventory);

        // Initialise the inventory with 50 bikes
        for (int i = 0; i < 50; i++) {
            inventory.addAvailableBike(new Bike());
        }
    }

    /*
   METHOD: run
   IMPORT: None
   EXPORT: None
   ALGORITHM:
   Executes the event loop, processing messages and updating the bike shop's state.
   */
    public void run() throws IOException
    {
        int daysElapsed = 0;
        logger.info("Starting event loop.");

        while(System.in.available() == 0)
        {
            // Simulate one day
            daysElapsed++;
            int finalDaysElapsed = daysElapsed;
            logger.fine(() -> "Simulated day: " + finalDaysElapsed);

            // Process all messages for this day
            String message = bikeShopInput.nextMessage();
            while(message != null)
            {
                bikeShopController.processMessage(message);
                message = bikeShopInput.nextMessage();
            }

            // Display status
            bikeShopView.displayStatus(daysElapsed, bankAccount, inventory);

            // Sleep for 1 second (simulates 1 day)
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                logger.severe(() -> "Thread interrupted: " + e.getMessage());
                throw new AssertionError(e);
            }
        }

        // Final statistics
        displayFinalStatistics();
        logger.info("Event loop ended.");
    }

    /*
    METHOD: displayFinalStatistics
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Displays final statistics at the end of the simulation.
    */
    private void displayFinalStatistics()
    {
        // Calculate and display final statistics
        System.out.println("Simulation Ended");
    }
}
