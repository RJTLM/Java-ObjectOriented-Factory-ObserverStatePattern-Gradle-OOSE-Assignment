package edu.curtin.oose2024s1.assignment2.controller;

import edu.curtin.oose2024s1.assignment2.model.*;
import java.util.logging.Logger;

/*
Purpose:
    - This class will manage the operations of the bike shop, such as processing messages (e.g., deliveries, drop-offs, purchases), updating the model (e.g., Inventory, BankAccount), and ensuring the state transitions (e.g., bike statuses) are handled correctly.
Role:
    - Acts as an intermediary between the model (e.g., Inventory, Customer, BankAccount) and the inputs (e.g., messages from BikeShopInput).
 */
// Handles the main simulation logic and message processing.
public class BikeShopController
{
    private static final Logger logger = Logger.getLogger(BikeShopController.class.getName());

    private final Inventory inventory;
    private final BankAccount bankAccount;

    /*
    METHOD: BikeShopController
    IMPORT: inventory (Inventory), bankAccount (BankAccount)
    EXPORT: None
    ALGORITHM:
    Constructor that initialises the inventory and bank account.
    */
    public BikeShopController(Inventory inventory, BankAccount bankAccount)
    {
        this.inventory = inventory;
        this.bankAccount = bankAccount;
    }

    /*
    METHOD: processMessage
    IMPORT: message (String)
    EXPORT: None
    ALGORITHM:
    Processes the input message and delegates to the appropriate handler based on message type.
    */
    public void processMessage(String message)
    {
        if(message == null || message.isEmpty())
        {
            logger.warning(() -> "Invalid message: " + message);
            return;
        }

        String[] parts = message.split(" ", 2);
        String type = parts[0];
        String email = parts.length > 1 ? parts[1] : null;

        switch(type)
        {
            case "DELIVERY":
                handleDelivery();
                break;
            case "DROP-OFF":
                handleDropOff(email);
                break;
            case "PURCHASE-ONLINE":
                handlePurchaseOnline(email);
                break;
            case "PURCHASE-IN-STORE":
                handlePurchaseInStore();
                break;
            case "PICK-UP":
                handlePickUp(email);
                break;
            default:
                logger.warning(() -> "Invalid message type: " + type);
                break;
        }
    }

    /*
    METHOD: handleDelivery
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Handles the "DELIVERY" message by adding bikes to the inventory and deducting the cost from the bank account if conditions are met.
    */
    private void handleDelivery()
    {
        if(inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() <= 90 &&
                bankAccount.getBalance() >= 10000)
        {
            for(int i = 0; i < 10; i++)
            {
                inventory.addAvailableBike(new Bike());
            }
            bankAccount.withdraw(5000);
            logger.info("Delivery accepted: 10 bikes added.");
        }
        else
        {
            logger.warning("Delivery failed: Not enough space or cash.");
        }
    }

    /*
    METHOD: handleDropOff
    IMPORT: email (String)
    EXPORT: None
    ALGORITHM:
    Handles the "DROP-OFF" message by adding a bike to the serviced bikes if conditions are met.
    */
    private void handleDropOff(String email)
    {
        if(email != null && inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() <= 99)
        {
            Bike bike = new Bike();
            bike.setAssociatedEmail(email);
            bike.dropOff();
            inventory.addServicedBike(bike);
            logger.info("Drop-off accepted: Bike added for servicing.");
        }
        else
        {
            logger.warning("Drop-off failed: Not enough space or invalid email.");
        }
    }

    /*
    METHOD: handlePurchaseOnline
    IMPORT: email (String)
    EXPORT: None
    ALGORITHM:
    Handles the "PURCHASE-ONLINE" message by selling a bike to a customer if conditions are met.
    */
    private void handlePurchaseOnline(String email)
    {
        if(email != null && inventory.getAvailableBikeCount() > 0)
        {
            Bike bike = inventory.getAvailableBikes().removeFirst();
            bike.setAssociatedEmail(email);
            bike.purchase();
            inventory.addAwaitingPickupBike(bike);
            bankAccount.deposit(1000);
            logger.info(() -> "Purchase online accepted: Bike sold to " + email);
        }
        else
        {
            logger.warning("Purchase online failed: No bikes available or invalid email.");
        }
    }

    /*
    METHOD: handlePurchaseInStore
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Handles the "PURCHASE-IN-STORE" message by selling a bike to a customer if conditions are met.
    */
    private void handlePurchaseInStore()
    {
        if(inventory.getAvailableBikeCount() > 0)
        {
            Bike bike = inventory.getAvailableBikes().removeFirst();
            bike.purchase();
            bankAccount.deposit(1000);
            logger.info("Purchase in-store accepted: Bike sold.");
        }
        else
        {
            logger.warning("Purchase in-store failed: No bikes available.");
        }
    }

    /*
    METHOD: handlePickUp
    IMPORT: email (String)
    EXPORT: None
    ALGORITHM:
    Handles the "PICK-UP" message by allowing a customer to pick up their bike if conditions are met.
    */
    private void handlePickUp(String email)
    {
        if(email != null)
        {
            for(Bike bike : inventory.getAwaitingPickupBikes())
            {
                if(bike.getAssociatedEmail().equals(email))
                {
                    bike.pickUp();
                    inventory.removeAwaitingPickupBike(bike);
                    logger.info(() -> "Pick-up accepted: Bike given to " + email);
                    return;
                }
            }
            logger.warning("Pick-up failed: No bike matching customer email.");
        }
        else
        {
            logger.warning("Pick-up failed: Invalid email.");
        }
    }
}
