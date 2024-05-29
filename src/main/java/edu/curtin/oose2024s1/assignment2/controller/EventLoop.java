package edu.curtin.oose2024s1.assignment2.controller;

import edu.curtin.oose2024s1.assignment2.BikeShopInput;
import edu.curtin.oose2024s1.assignment2.model.*;
import edu.curtin.oose2024s1.assignment2.view.BikeShopView;

import java.io.IOException;
import java.util.logging.Logger;

/**
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

    private int totalMessages = 0;
    private int totalFailures = 0;

    /**
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

    /**
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
            // Clear the console for better UX
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Simulate one day
            daysElapsed++;
            int finalDaysElapsed = daysElapsed;
            logger.fine(() -> "Simulated day: " + finalDaysElapsed);

            // Display status
            bikeShopView.displayStatus(daysElapsed, bankAccount, inventory);

            // Pay the employee every 7 days
            if (daysElapsed % 7 == 0)
            {
                bankAccount.withdraw(1000); // $1000 payment to the employee
                System.out.println("Employee paid $1000.");
                logger.info("Employee paid $1000.");
            }

            // Process all messages for this day
            String message = bikeShopInput.nextMessage();
            while(message != null)
            {
                totalMessages++;
                String result = bikeShopController.processMessage(message);
                if(result.startsWith("FAILURE"))
                {
                    totalFailures++;
                }
                System.out.println(result);
                bikeShopView.logToFile(result);
                message = bikeShopInput.nextMessage();
            }

            // Increment the days in servicing state for all serviced bikes
            inventory.incrementDaysInServicingState();

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
        displayFinalStatistics(totalMessages, totalFailures);
        logger.info("Event loop ended.");
    }

    /**
    METHOD: displayFinalStatistics
    IMPORT: totalMessages (int), totalFailures (int)
    EXPORT: None
    ALGORITHM:
    Displays final statistics at the end of the simulation and logs them to the file.
    */
    private void displayFinalStatistics(int totalMessages, int totalFailures)
    {
        String finalStats = "\nSimulation Ended\n" +
                "Total number of input messages: " + totalMessages + "\n" +
                "Total number of failures: " + totalFailures;
        System.out.println(finalStats);
        bikeShopView.logToFile(finalStats);
        bikeShopView.close();
    }
}
