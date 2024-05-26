package edu.curtin.oose2024s1.assignment2.view;

import edu.curtin.oose2024s1.assignment2.model.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/*
Purpose:
    - This class will manage the display of the bike shop's status, such as the number of bikes available, being serviced, and awaiting pickup, as well as the bank account balance. It will also handle logging messages to the console and writing them to the "sim_results.txt" file.
Role:
    - Acts as the presentation layer of the application, showing the current state of the bike shop to the user and ensuring that all output is logged appropriately.
 */
// Displays shop statistics and messages.
public class BikeShopView
{
    private static final Logger logger = Logger.getLogger(BikeShopView.class.getName());
    private PrintWriter writer;

    public BikeShopView()
    {
        try
        {
            writer = new PrintWriter(new FileWriter("sim_results.txt", true));
        }
        catch(IOException e)
        {
            logger.severe("Error opening sim_results.txt: " + e.getMessage());
        }
    }

    public void displayStatus(int daysElapsed, BankAccount bankAccount, Inventory inventory)
    {
        String status = "Day " + daysElapsed +
                "\nBank Account Balance: $" + bankAccount.getBalance() +
                "\nBikes Available: " + inventory.getAvailableBikeCount() +
                "\nBikes Being Serviced: " + inventory.getServicedBikeCount() +
                "\nBikes Awaiting Pickup: " + inventory.getAwaitingPickupBikeCount();
        System.out.println(status);
        logToFile(status);
    }

    public void logMessage(String message)
    {
        System.out.println(message);
        logToFile(message);
    }

    public void logFailure(String failureReason)
    {
        String failureMessage = "FAILURE: " + failureReason;
        System.out.println(failureMessage);
        logToFile(failureMessage);
    }

    private void logToFile(String message)
    {
        writer.println(message);
        writer.flush();
    }

    public void close()
    {
        if(writer != null)
        {
            writer.close();
        }
    }
}
