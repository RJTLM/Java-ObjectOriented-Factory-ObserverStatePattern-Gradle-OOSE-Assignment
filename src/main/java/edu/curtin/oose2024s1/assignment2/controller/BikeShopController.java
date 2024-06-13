package edu.curtin.oose2024s1.assignment2.controller;

import edu.curtin.oose2024s1.assignment2.factory.BikeFactory;
import edu.curtin.oose2024s1.assignment2.model.*;
import edu.curtin.oose2024s1.assignment2.state.AvailableState;
import edu.curtin.oose2024s1.assignment2.state.AwaitingPickupState;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
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
    private final BikeFactory bikeFactory;
    private final Map<String, Customer> customers = new HashMap<>();

    /**
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
        this.bikeFactory = new BikeFactory();
    }

    /**
     METHOD: processMessage
     IMPORT: message (String)
     EXPORT: String (indicating success or failure message)
     ALGORITHM:
     Processes the input message and delegates to the appropriate handler based on message type.
     */
    public String processMessage(String message)
    {
        if(message == null || message.isEmpty())
        {
            logger.warning("Received invalid message.");
            return "FAILURE: Invalid message.";
        }

        String[] parts = message.split(" ", 2);
        String type = parts[0];
        String email = parts.length > 1 ? parts[1] : null;

        String result = switch (type) {
            case "DELIVERY" -> handleDelivery();
            case "DROP-OFF" -> handleDropOff(email);
            case "PURCHASE-ONLINE" -> handlePurchaseOnline(email);
            case "PURCHASE-IN-STORE" -> handlePurchaseInStore();
            case "PICK-UP" -> handlePickUp(email);
            default -> "FAILURE: Invalid message type.";
        };

        if(result.startsWith("FAILURE"))
        {
            logger.warning(result);
        }
        else
        {
            logger.info(result);
        }

        return result;
    }

    /**
     METHOD: handleDelivery
     IMPORT: None
     EXPORT: String (result message)
     ALGORITHM:
     Handles the "DELIVERY" message by adding bikes to the inventory and deducting the cost from the bank account if conditions are met.
     */
    private String handleDelivery()
    {
        boolean notEnoughSpace = inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() > 90;
        boolean notEnoughCash = bankAccount.getBalance() < 10000;

        if (notEnoughSpace && notEnoughCash)
        {
            return "FAILURE: Not enough space and not enough cash to purchase stock.";
        }
        else if (notEnoughSpace)
        {
            return "FAILURE: Not enough space.";
        }
        else if (notEnoughCash)
        {
            return "FAILURE: Not enough cash to purchase stock.";
        }
        else
        {
            for(int i = 0; i < 10; i++)
            {
                Bike bike = bikeFactory.createBike(); // Use factory to create bikes
                inventory.addAvailableBike(bike);
            }
            bankAccount.withdraw(5000);
            logger.info("Delivery accepted: 10 bikes added.");
            return "Delivery accepted: 10 bikes added.";
        }
    }

    /**
     METHOD: handleDropOff
     IMPORT: email (String)
     EXPORT: String (result message)
     ALGORITHM:
     Handles the "DROP-OFF" message by adding a bike to the serviced bikes if conditions are met.
     */
    private String handleDropOff(String email)
    {
        if(email != null && inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() <= 99)
        {
            Bike bike = bikeFactory.createBike();
            bike.setAssociatedEmail(email);
            bike.dropOff();
            inventory.addServicedBike(bike);

            // Add bike to customer
            customers.putIfAbsent(email, new Customer(email));
            customers.get(email).addBike(bike);

            logger.info(() -> "Drop-off accepted: Bike added for servicing for " + email + ".");
            return "Drop-off accepted: Bike added for servicing for " + email + ".";
        }
        else
        {
            if(email == null)
            {
                logger.warning("Invalid email for drop-off.");
                return "FAILURE: Invalid email.";
            }
            if(inventory.getAvailableBikeCount() + inventory.getServicedBikeCount() + inventory.getAwaitingPickupBikeCount() > 99)
            {
                logger.warning("Not enough space for drop-off.");
                return "FAILURE: Not enough space.";
            }
        }
        logger.warning("Unknown drop-off error.");
        return "FAILURE: Unknown drop-off error.";
    }

    /**
     METHOD: handlePurchaseOnline
     IMPORT: email (String)
     EXPORT: String (result message)
     ALGORITHM:
     Handles the "PURCHASE-ONLINE" message by selling a bike to a customer if conditions are met.
     */
    private String handlePurchaseOnline(String email)
    {
        if (email != null && inventory.getAvailableBikeCount() > 0)
        {
            Bike bike = inventory.getAvailableBikes().removeFirst();
            if (bike.getState() instanceof AvailableState) {
                bike.setAssociatedEmail(email);
                bike.purchase();
                inventory.removeAvailableBike(bike); // Ensure bike is removed from the available list
                inventory.addAwaitingPickupBike(bike);
                bankAccount.deposit(1000);

                // Add bike to customer
                customers.putIfAbsent(email, new Customer(email));
                customers.get(email).addBike(bike);

                logger.info(() -> "Purchase online accepted: Bike sold to " + email + ".");
                return "Purchase online accepted: Bike sold to " + email + ".";
            } else {
                logger.warning("Bike is not available for purchase.");
                return "FAILURE: Bike is not available for purchase.";
            }
        }
        else
        {
            if (email == null)
            {
                logger.warning("Invalid email for online purchase.");
                return "FAILURE: Invalid email.";
            }
            if (inventory.getAvailableBikeCount() == 0)
            {
                logger.warning("No bikes available for online purchase.");
                return "FAILURE: No bikes available.";
            }
        }
        logger.warning("Unknown online purchase error.");
        return "FAILURE: Unknown online purchase error.";
    }

    /**
     METHOD: handlePurchaseInStore
     IMPORT: None
     EXPORT: String (result message)
     ALGORITHM:
     Handles the "PURCHASE-IN-STORE" message by selling a bike to a customer if conditions are met.
     */
    private String handlePurchaseInStore()
    {
        if (inventory.getAvailableBikeCount() > 0)
        {
            Bike bike = inventory.getAvailableBikes().removeFirst(); // Correctly remove the bike from the list
            if (bike.getState() instanceof AvailableState) {
                bike.purchase();
                inventory.removeAvailableBike(bike); // Ensure bike is removed from the available list
                bankAccount.deposit(1000);
                logger.info("Purchase in-store accepted: Bike sold.");
                return "Purchase in-store accepted: Bike sold.";
            } else {
                logger.warning("Bike is not available for purchase.");
                return "FAILURE: Bike is not available for purchase.";
            }
        }
        else
        {
            logger.warning("No bikes available for in-store purchase.");
            return "FAILURE: No bikes available.";
        }
    }

    /**
     METHOD: handlePickUp
     IMPORT: email (String)
     EXPORT: String (result message)
     ALGORITHM:
     Handles the "PICK-UP" message by allowing a customer to pick up their bike if conditions are met.
     */
    private String handlePickUp(String email)
    {
        if (email != null)
        {
            Customer customer = customers.get(email);
            if (customer != null)
            {
                for (Bike bike : customer.getBikes())
                {
                    if (bike.getState() instanceof AwaitingPickupState)
                    {
                        customer.removeBike(bike);
                        bike.pickUp();
                        inventory.removeAwaitingPickupBike(bike);

                        // Charge the $100 service fee if the bike was serviced
                        if (bike.getState() instanceof AvailableState && bike.getDaysInServicingState() > 0) {
                            bankAccount.deposit(100); // Charge for servicing
                            System.out.println("$100 SERVICE FEE RECEIVED");
                            logger.info("$100 SERVICE FEE RECEIVED");
                        }

                        logger.info(() -> "Pick-up accepted: Bike given to " + email + ".");
                        return "Pick-up accepted: Bike given to " + email + ".";
                    }
                }
                logger.warning(() -> "No bike matching customer email: " + email + ".");
                return "FAILURE: No bike matching customer email: " + email + ".";
            }
            else
            {
                logger.warning(() -> "No customer with email: " + email + ".");
                return "FAILURE: No customer with email: " + email + ".";
            }
        }
        else
        {
            logger.warning("Invalid email for pick-up.");
            return "FAILURE: Invalid email.";
        }
    }
}
