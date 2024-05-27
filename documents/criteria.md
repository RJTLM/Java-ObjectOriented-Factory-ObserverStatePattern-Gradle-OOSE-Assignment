Observer Pattern:
- `observer` package with `Observable` and `Observer` classes.

`BikeShopView` is the observer.

`BankAccount` and `Inventory` are the observables.


The model classes, such as BankAccount and Inventory, hold the core state of the application that needs to be observed by the view to reflect the current status of the bike shop. The controller will interact with these model classes to update the state based on business logic, and these changes will be communicated to the view through the observer pattern.


### Explanation of the Observer Pattern Implementation
1. Observable (BankAccount and Inventory):
   - Both `BankAccount` and `Inventory` classes implement the `Observable` interface.
   - They maintain a list of observers and notify them whenever their state changes (e.g., when the balance is updated or when a bike is added/removed).

2. Observer (BikeShopView):
   - `BikeShopView` implements the `Observer` interface. 
   - It registers itself as an observer to both `BankAccount` and `Inventory`.
   - The `update` method in `BikeShopView` is called whenever `BankAccount` or `Inventory` notify their observers of a change. This ensures that the view always displays the current state of the model.

By following this approach, you ensure that the view is always up-to-date with the latest state of the model without needing to manually refresh or poll the model for changes. This design makes the application more modular, easier to maintain, and scalable.


`BikeShopController` IS NOT the observable:
The `BikeShopController` indeed manages operations and processes events, but it does not hold the state that the view needs to observe directly. Instead, it interacts with the model components (e.g., `BankAccount`, `Inventory`), which hold the actual data.

The Observer pattern is primarily used to keep the view updated with changes in the model. Here's a more detailed breakdown:

Model: Holds the state of the application (BankAccount, Inventory, etc.).
View: Displays the state of the application (BikeShopView).
Controller: Manages operations, processes input, and updates the model (BikeShopController).

In this setup, the model should be observable because it contains the data that the view needs to display. The controller triggers changes in the model, which then notifies the view through the observer pattern.


The Observer Pattern is used when you have a class that needs to be notified of certain events happening in another class. This class, the observer, registers its interest in these events with the event source (the observable). When the event occurs, the observable notifies all registered observers so that they can react accordingly.

Implementation:

Observable: A class that maintains a list of its dependents (observers) and notifies them of state changes.
Observer: A class that registers with an observable to be notified of events and takes action when those events occur.


For the bike shop application:

BankAccount and Inventory should be observable because changes in the bank account balance and inventory are important events.
BikeShopView should observe these classes because it needs to update the display whenever there's a change in the bank account balance or inventory.




-------------------------------------------------------------------------------------------------

Based on the provided codebase, assignment requirements, and your question about the Observer pattern, here's a detailed breakdown of which classes should be observers, which should be observables, and why:

### Observables
1. **BankAccount**:
   - **Why**: The `BankAccount` class represents the financial state of the bike shop. Any changes in the bank account balance should be observable because they affect the overall status of the shop.
   - **How**: Implement the `Observable` interface to allow views to register as observers and get notified whenever there's a change in the balance.

2. **Inventory**:
   - **Why**: The `Inventory` class manages the collection of bikes and their states. Changes in inventory, such as adding or removing bikes, should be observable because they directly impact the shop's status.
   - **How**: Implement the `Observable` interface to notify observers (views) when the inventory changes.

### Observers
1. **BikeShopView**:
   - **Why**: The `BikeShopView` class displays the bike shop's status and logs messages. It should observe changes in the `BankAccount` and `Inventory` to update the displayed information accordingly.
   - **How**: Implement the `Observer` interface to update the view whenever the observables (`BankAccount` and `Inventory`) change.

### Implementation

#### Observable Interface Implementation
```java
package edu.curtin.oose2024s1.assignment2.observer;

import java.util.ArrayList;
import java.util.List;

// Interface for observable objects.
public interface Observable
{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
```

#### Observer Interface Implementation
```java
package edu.curtin.oose2024s1.assignment2.observer;

// Interface for observers.
public interface Observer
{
    void update();
}
```

#### BankAccount Implementation
```java
package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.observer.Observable;
import edu.curtin.oose2024s1.assignment2.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// Manages the shop's bank account.
public class BankAccount implements Observable
{
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private double balance;
    private List<Observer> observers = new ArrayList<>();

    public BankAccount(double initialBalance)
    {
        this.balance = initialBalance;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        balance += amount;
        logger.info("Deposited: " + amount + ", New Balance: " + balance);
        notifyObservers();
    }

    public void withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            logger.info("Withdrew: " + amount + ", New Balance: " + balance);
            notifyObservers();
        }
        else
        {
            logger.warning("Insufficient funds for withdrawal: " + amount);
        }
    }

    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
```

#### Inventory Implementation
```java
package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.observer.Observable;
import edu.curtin.oose2024s1.assignment2.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// Manages the bike inventory.
public class Inventory implements Observable
{
    private static final Logger logger = Logger.getLogger(Inventory.class.getName());

    private List<Bike> availableBikes;
    private List<Bike> servicedBikes;
    private List<Bike> awaitingPickupBikes;
    private List<Observer> observers = new ArrayList<>();

    public Inventory()
    {
        availableBikes = new ArrayList<>();
        servicedBikes = new ArrayList<>();
        awaitingPickupBikes = new ArrayList<>();
    }

    public List<Bike> getAvailableBikes()
    {
        return availableBikes;
    }

    public List<Bike> getServicedBikes()
    {
        return servicedBikes;
    }

    public List<Bike> getAwaitingPickupBikes()
    {
        return awaitingPickupBikes;
    }

    public void addAvailableBike(Bike bike)
    {
        availableBikes.add(bike);
        logger.info("Bike added to available: " + bike);
        notifyObservers();
    }

    public void removeAvailableBike(Bike bike)
    {
        availableBikes.remove(bike);
        logger.info("Bike removed from available: " + bike);
        notifyObservers();
    }

    public void addServicedBike(Bike bike)
    {
        servicedBikes.add(bike);
        logger.info("Bike added to serviced: " + bike);
        notifyObservers();
    }

    public void removeServicedBike(Bike bike)
    {
        servicedBikes.remove(bike);
        logger.info("Bike removed from serviced: " + bike);
        notifyObservers();
    }

    public void addAwaitingPickupBike(Bike bike)
    {
        awaitingPickupBikes.add(bike);
        logger.info("Bike added to awaiting pickup: " + bike);
        notifyObservers();
    }

    public void removeAwaitingPickupBike(Bike bike)
    {
        awaitingPickupBikes.remove(bike);
        logger.info("Bike removed from awaiting pickup: " + bike);
        notifyObservers();
    }

    public int getAvailableBikeCount()
    {
        return availableBikes.size();
    }

    public int getServicedBikeCount()
    {
        return servicedBikes.size();
    }

    public int getAwaitingPickupBikeCount()
    {
        return awaitingPickupBikes.size();
    }

    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update();
        }
    }
}
```

#### BikeShopView Implementation
```java
package edu.curtin.oose2024s1.assignment2.view;

import edu.curtin.oose2024s1.assignment2.model.BankAccount;
import edu.curtin.oose2024s1.assignment2.model.Inventory;
import edu.curtin.oose2024s1.assignment2.observer.Observer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

// Displays shop statistics and messages.
public class BikeShopView implements Observer
{
    private static final Logger logger = Logger.getLogger(BikeShopView.class.getName());
    private PrintWriter writer;
    private BankAccount bankAccount;
    private Inventory inventory;

    public BikeShopView(BankAccount bankAccount, Inventory inventory)
    {
        this.bankAccount = bankAccount;
        this.inventory = inventory;
        bankAccount.addObserver(this);
        inventory.addObserver(this);

        try
        {
            writer = new PrintWriter(new FileWriter("sim_results.txt", true));
        }
        catch(IOException e)
        {
            logger.severe("Error opening sim_results.txt: " + e.getMessage());
        }
    }

    @Override
    public void update()
    {
        displayStatus();
    }

    public void displayStatus()
    {
        String status = "Bank Account Balance: $" + bankAccount.getBalance() +
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
```

### Explanation

- **Observables**:
   - `BankAccount` and `Inventory` are observables because they contain state information that, when changed, should notify other parts of the system.
   - They use the `Observable` interface to maintain a list of observers and notify them when changes occur.

- **Observers**:
   - `BikeShopView` is an observer because it needs to update the displayed information whenever the state of the `BankAccount` or `Inventory` changes.
   - It implements the `Observer` interface and registers itself with the observables.

By implementing the Observer pattern this way, you ensure that your views always reflect the current state of the models, adhering to the principles of the MVC architecture and the Observer pattern.





------------------------------------------------------------------------
### State Pattern Overview

The State Pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes. This pattern is particularly useful when an object needs to exhibit different behaviors based on its current state.

**Key Components of the State Pattern:**
1. **Context**: The object that contains a state.
2. **State Interface**: An interface that defines the behavior associated with a state.
3. **Concrete State Classes**: Implementations of the state interface that provide behavior for different states.
4. **State Transitions**: The context delegates state-specific behavior to the current state object, and state transitions are handled by changing the current state.

### Implementation in the Bike Shop Project

For this project, the `Bike` class will serve as the context, with different states representing its status: `Available`, `BeingServiced`, and `AwaitingPickup`. The state pattern will allow the `Bike` class to change its behavior based on its current state.

#### Steps to Implement the State Pattern

1. **Define the State Interface (`BikeState`)**:
   - This interface will declare methods that each state must implement. These methods will define the behavior that varies depending on the bike's state.

2. **Implement Concrete State Classes**:
   - Create concrete classes for each state (`AvailableState`, `BeingServicedState`, `AwaitingPickupState`). Each class will implement the `BikeState` interface and provide specific behavior for that state.

3. **Modify the `Bike` Class**:
   - Add a `BikeState` field to represent the current state of the bike.
   - Implement methods to set the current state and delegate state-specific behavior to the state object.

4. **Handle State Transitions**:
   - Ensure that state transitions (e.g., from `Available` to `BeingServiced`) are handled correctly by changing the current state object.

### Code Implementation

#### 1. State Interface (`BikeState`)
```java
package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

// Interface for bike states.
public interface BikeState
{
    void dropOff(Bike bike);
    void pickUp(Bike bike);
    void purchase(Bike bike);
}
```

#### 2. Concrete State Classes

**AvailableState**
```java
package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

// Concrete state for bikes that are available.
public class AvailableState implements BikeState
{
    @Override
    public void dropOff(Bike bike)
    {
        bike.setState(new BeingServicedState());
        bike.setStatus(Bike.Status.BEING_SERVICED);
        // Additional logic for drop-off can be added here
    }

    @Override
    public void pickUp(Bike bike)
    {
        // Bikes cannot be picked up if they are available
        throw new UnsupportedOperationException("Bike is already available.");
    }

    @Override
    public void purchase(Bike bike)
    {
        bike.setState(new AwaitingPickupState());
        bike.setStatus(Bike.Status.AWAITING_PICKUP);
        // Additional logic for purchase can be added here
    }
}
```

**BeingServicedState**
```java
package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

// Concrete state for bikes that are being serviced.
public class BeingServicedState implements BikeState
{
    @Override
    public void dropOff(Bike bike)
    {
        // Bikes cannot be dropped off if they are already being serviced
        throw new UnsupportedOperationException("Bike is already being serviced.");
    }

    @Override
    public void pickUp(Bike bike)
    {
        bike.setState(new AvailableState());
        bike.setStatus(Bike.Status.AVAILABLE);
        // Additional logic for pick-up can be added here
    }

    @Override
    public void purchase(Bike bike)
    {
        // Bikes cannot be purchased if they are being serviced
        throw new UnsupportedOperationException("Bike is being serviced.");
    }
}
```

**AwaitingPickupState**
```java
package edu.curtin.oose2024s1.assignment2.state;

import edu.curtin.oose2024s1.assignment2.model.Bike;

// Concrete state for bikes that are awaiting pickup.
public class AwaitingPickupState implements BikeState
{
    @Override
    public void dropOff(Bike bike)
    {
        // Bikes cannot be dropped off if they are awaiting pickup
        throw new UnsupportedOperationException("Bike is awaiting pickup.");
    }

    @Override
    public void pickUp(Bike bike)
    {
        bike.setState(new AvailableState());
        bike.setStatus(Bike.Status.AVAILABLE);
        // Additional logic for pick-up can be added here
    }

    @Override
    public void purchase(Bike bike)
    {
        // Bikes cannot be purchased if they are awaiting pickup
        throw new UnsupportedOperationException("Bike is awaiting pickup.");
    }
}
```

#### 3. Modify the `Bike` Class
```java
package edu.curtin.oose2024s1.assignment2.model;

import edu.curtin.oose2024s1.assignment2.state.BikeState;
import edu.curtin.oose2024s1.assignment2.state.AvailableState;

// Represents a bike.
public class Bike
{
    public enum Status
    {
        AVAILABLE, BEING_SERVICED, AWAITING_PICKUP
    }

    private Status status;
    private String associatedEmail;
    private BikeState state;

    public Bike()
    {
        this.state = new AvailableState();
        this.status = Status.AVAILABLE;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public String getAssociatedEmail()
    {
        return associatedEmail;
    }

    public void setAssociatedEmail(String email)
    {
        this.associatedEmail = email;
    }

    public void setState(BikeState state)
    {
        this.state = state;
    }

    public void dropOff()
    {
        state.dropOff(this);
    }

    public void pickUp()
    {
        state.pickUp(this);
    }

    public void purchase()
    {
        state.purchase(this);
    }

    @Override
    public String toString()
    {
        return "Bike{" +
                "status=" + status +
                ", associatedEmail='" + associatedEmail + '\'' +
                '}';
    }
}
```

### How it All Fits Together

1. **Bike (Context)**: The `Bike` class maintains a reference to a `BikeState` object, representing its current state. It delegates state-specific behavior to this state object.
2. **BikeState (State Interface)**: Defines the operations that each state must implement.
3. **AvailableState, BeingServicedState, AwaitingPickupState (Concrete States)**: Each class implements the `BikeState` interface and provides behavior specific to the state it represents.
4. **State Transitions**: The `Bike` class handles state transitions by changing its current state object. For example, calling `dropOff()` on a bike in the `AvailableState` transitions it to the `BeingServicedState`.

By implementing the State Pattern, you ensure that the `Bike` class can change its behavior dynamically based on its current state, leading to a more flexible and maintainable design.
