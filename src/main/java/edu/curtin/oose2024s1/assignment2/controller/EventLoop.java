package edu.curtin.oose2024s1.assignment2.controller;

import edu.curtin.oose2024s1.assignment2.BikeShopInput;
import edu.curtin.oose2024s1.assignment2.model.BankAccount;
import edu.curtin.oose2024s1.assignment2.model.Inventory;

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

    private BikeShopInput bikeShopInput;
    private BikeShopController bikeShopController;
    private Inventory inventory;
    private BankAccount bankAccount;

    public EventLoop(BikeShopInput bikeShopInput, BikeShopController bikeShopController, Inventory inventory, BankAccount bankAccount)
    {
        this.bikeShopInput = bikeShopInput;
        this.bikeShopController = bikeShopController;
        this.inventory = inventory;
        this.bankAccount = bankAccount;
    }

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
            displayStatus(daysElapsed);

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

    private void displayStatus(int daysElapsed)
    {
        System.out.println("Day " + daysElapsed);
        System.out.println("Bank Account Balance: $" + bankAccount.getBalance());
        System.out.println("Bikes Available: " + inventory.getAvailableBikeCount());
        System.out.println("Bikes Being Serviced: " + inventory.getServicedBikeCount());
        System.out.println("Bikes Awaiting Pickup: " + inventory.getAwaitingPickupBikeCount());
    }

    private void displayFinalStatistics()
    {
        // Calculate and display final statistics
        System.out.println("Simulation Ended");
    }
}
