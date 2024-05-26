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

    private Inventory inventory;
    private BankAccount bankAccount;

    public BikeShopController(Inventory inventory, BankAccount bankAccount)
    {
        this.inventory = inventory;
        this.bankAccount = bankAccount;
    }

    public void processMessage(String message)
    {
        if(message == null || message.isEmpty())
        {
            logger.warning("Invalid message: " + message);
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
                logger.warning("Invalid message type: " + type);
                break;
        }
    }

    private void handleDelivery()
    {
        if(inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() <= 90 &&
                bankAccount.getBalance() >= 10000)
        {
            for(int i = 0; i < 10; i++)
            {
                inventory.addAvailableBike(new Bike(Bike.Status.AVAILABLE));
            }
            bankAccount.withdraw(5000);
            logger.info("Delivery accepted: 10 bikes added.");
        }
        else
        {
            logger.warning("Delivery failed: Not enough space or cash.");
        }
    }

    private void handleDropOff(String email)
    {
        if(email != null && inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() <= 99)
        {
            Bike bike = new Bike(Bike.Status.BEING_SERVICED);
            bike.setAssociatedEmail(email);
            inventory.addServicedBike(bike);
            logger.info("Drop-off accepted: Bike added for servicing.");
        }
        else
        {
            logger.warning("Drop-off failed: Not enough space or invalid email.");
        }
    }

    private void handlePurchaseOnline(String email)
    {
        if(email != null && inventory.getAvailableBikeCount() > 0)
        {
            Bike bike = inventory.getAvailableBikes().remove(0);
            bike.setStatus(Bike.Status.AWAITING_PICKUP);
            bike.setAssociatedEmail(email);
            inventory.addAwaitingPickupBike(bike);
            bankAccount.deposit(1000);
            logger.info("Purchase online accepted: Bike sold to " + email);
        }
        else
        {
            logger.warning("Purchase online failed: No bikes available or invalid email.");
        }
    }

    private void handlePurchaseInStore()
    {
        if(inventory.getAvailableBikeCount() > 0)
        {
            inventory.removeAvailableBike(inventory.getAvailableBikes().get(0));
            bankAccount.deposit(1000);
            logger.info("Purchase in-store accepted: Bike sold.");
        }
        else
        {
            logger.warning("Purchase in-store failed: No bikes available.");
        }
    }

    private void handlePickUp(String email)
    {
        if(email != null)
        {
            for(Bike bike : inventory.getAwaitingPickupBikes())
            {
                if(bike.getAssociatedEmail().equals(email))
                {
                    inventory.removeAwaitingPickupBike(bike);
                    logger.info("Pick-up accepted: Bike given to " + email);
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
