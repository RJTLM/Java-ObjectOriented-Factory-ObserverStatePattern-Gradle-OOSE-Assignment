package edu.curtin.oose2024s1.assignment2;

import edu.curtin.oose2024s1.assignment2.controller.BikeShopController;
import edu.curtin.oose2024s1.assignment2.controller.EventLoop;
import edu.curtin.oose2024s1.assignment2.model.BankAccount;
import edu.curtin.oose2024s1.assignment2.model.Inventory;
import edu.curtin.oose2024s1.assignment2.view.BikeShopView;

import java.io.IOException;

/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
/*
Note, I modified this class by centralising the event loop and message processing logic in the `EventLoop` class and refactored this class to initiate and run the `EventLoop`, making the code more modular, maintainable, and aligned with the single responsibility principle.
*/
public class App
{
    /*
    METHOD: main
    IMPORT: args (String ARRAY)
    EXPORT: None
    ALGORITHM:
    Initialises the simulation components and starts the event loop.
    */
    public static void main(String[] args)
    {
        BikeShopInput inp = new BikeShopInput();
        Inventory inventory = new Inventory();
        BankAccount bankAccount = new BankAccount(15000);
        BikeShopView bikeShopView = new BikeShopView();
        BikeShopController bikeShopController = new BikeShopController(inventory, bankAccount);

        EventLoop eventLoop = new EventLoop(inp, bikeShopController, inventory, bankAccount, bikeShopView);

        try
        {
            eventLoop.run();
        }
        catch(IOException e)
        {
            System.out.println("Error running the simulation: " + e.getMessage());
        }
    }
}
