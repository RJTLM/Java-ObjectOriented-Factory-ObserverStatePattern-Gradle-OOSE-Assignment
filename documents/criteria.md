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
