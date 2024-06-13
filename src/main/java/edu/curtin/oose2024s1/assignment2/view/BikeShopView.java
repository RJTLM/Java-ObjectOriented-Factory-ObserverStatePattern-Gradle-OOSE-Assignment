package edu.curtin.oose2024s1.assignment2.view;

import edu.curtin.oose2024s1.assignment2.model.*;
import edu.curtin.oose2024s1.assignment2.observer.Observer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 Purpose:
 - This class will manage the display of the bike shop's status, such as the number of bikes available, being serviced, and awaiting pickup, as well as the bank account balance. It will also handle logging messages to the console and writing them to the "sim_results.txt" file.
 Role:
 - Acts as the presentation layer of the application, showing the current state of the bike shop to the user and ensuring that all output is logged appropriately.
 */
// Displays shop statistics and messages.
public class BikeShopView implements Observer
{
    private static final Logger logger = Logger.getLogger(BikeShopView.class.getName());
    private PrintWriter writer;

    /**
     METHOD: BikeShopView
     IMPORT: None
     EXPORT: None
     ALGORITHM:
     Constructor that initialises the PrintWriter for writing to the "sim_results.txt" file.
     */
    public BikeShopView()
    {
        try
        {
            writer = new PrintWriter(new FileWriter("sim_results.txt", true));
            logger.info("sim_results.txt opened successfully.");
        }
        catch(IOException e)
        {
            logger.severe(() -> "Error opening sim_results.txt: " + e.getMessage());
        }
    }

    /**
     METHOD: registerObservers
     IMPORT: bankAccount (BankAccount), inventory (Inventory)
     EXPORT: None
     ALGORITHM:
     Registers the BikeShopView as an observer for the BankAccount and Inventory.
     */
    public void registerObservers(BankAccount bankAccount, Inventory inventory)
    {
        bankAccount.addObserver(this);
        inventory.addObserver(this);
    }

    /**
     METHOD: displayStatus
     IMPORT: daysElapsed (int), bankAccount (BankAccount), inventory (Inventory)
     EXPORT: None
     ALGORITHM:
     Displays the current status of the bike shop, including days elapsed, bank account balance, and bike counts.
     */
    public void displayStatus(int daysElapsed, BankAccount bankAccount, Inventory inventory)
    {
        String status = "Day " + daysElapsed +
                "\nBank Account Balance: $" + bankAccount.getBalance() +
                "\nBikes Available: " + inventory.getAvailableBikeCount() +
                "\nBikes Being Serviced: " + inventory.getServicedBikeCount() +
                "\nBikes Awaiting Pickup: " + inventory.getAwaitingPickupBikeCount();
        System.out.println(status);
        logToFile(status);
        logger.fine(() -> "Displayed status: " + status + "\n");
    }

    /**
     METHOD: logToFile
     IMPORT: message (String)
     EXPORT: None
     ALGORITHM:
     Writes a message to the "sim_results.txt" file and flushes the writer.
     */
    public void logToFile(String message)
    {
        writer.println(message);
        writer.flush();
    }

    /**
     METHOD: close
     IMPORT: None
     EXPORT: None
     ALGORITHM:
     Closes the PrintWriter if it is not null.
     */
    public void close()
    {
        if(writer != null)
        {
            writer.close();
            logger.info("sim_results.txt closed successfully.");
        }
    }

    /**
     METHOD: update
     IMPORT: None
     EXPORT: None
     ALGORITHM:
     Updates the view. This method is called when the observable notifies its observers.
     */
    @Override
    public void update() {}

    /**
     METHOD: unregisterObservers
     IMPORT: bankAccount (BankAccount), inventory (Inventory)
     EXPORT: None
     ALGORITHM:
     Unregisters the BikeShopView as an observer for the BankAccount and Inventory.
     */
    public void unregisterObservers(BankAccount bankAccount, Inventory inventory)
    {
        bankAccount.removeObserver(this);
        inventory.removeObserver(this);
    }
}
