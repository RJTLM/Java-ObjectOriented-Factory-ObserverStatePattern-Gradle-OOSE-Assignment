### Assignment Requirements and Plan

1. **General Code Quality (6 marks)**
    - Code should be well-commented.
    - No PMD warnings should be present unless justified with comments.

2. **Clear and Distinct Package/Class/Interface/Method Responsibilities (6 marks)**
    - Application should be divided into meaningful packages.
    - The application is not specifically required to use MVC, but may take inspiration from it as a means of dividing up responsibilities.

3. **Error Handling and Logging (6 marks)**
    - Proper use of exception handling.
    - Include a reasonable set of logging statements.

4. **Factory and Dependency Injection (6 marks)**
    - Implement a factory (or factories) and use dependency injection appropriately.

5. **Observer Pattern (7 marks)**
    - Implement a meaningful use of the Observer Pattern with actual polymorphism.

6. **State Pattern (7 marks)**
    - Implement a meaningful use of the State Pattern with actual polymorphism.

7. **UML Diagrams (7 marks)**
    - Provide one class diagram showing the structure of the entire app; and
    - One state chart showing states and state transitions corresponding to your implementation of the State Pattern.

8. **Bonus Marks for Generics (up to 4 bonus marks)**
    - Meaningful use of generics for type-safe code reuse.

### Problem Description

The application will simulate a bicycle shop that both sells and repairs bikes.

#### Basic Operation
- Event loop that simulates the passing of time.
- Loop until the user presses Enter.
- Each loop iteration represents one simulated day.
- Wait 1 second between steps.

#### Simulation Input
- Use the provided `BikeShopInput` class to obtain input messages.
- Messages include "DELIVERY", "DROP-OFF", "PURCHASE-ONLINE", "PURCHASE-IN-STORE", and "PICK-UP".
- Validate messages.

#### Messages and Simulation Logic
- Handle each message type appropriately.
- Manage inventory, servicing, and customer interactions.
- Update the shop's state based on the messages.

#### Outputs
- Display shop statistics each day.
- Show messages and failures on the screen.
- Write messages and failures to `sim_results.txt`.
- Display overall statistics at the end of the simulation.

### Plan for Files, Packages, and Responsibilities

1. **Packages and Classes**
    - **`edu.curtin.oose2024s1.assignment2.model`**:
        - `Bike`: Represents a bike.
        - `Customer`: Represents a customer.
        - `Inventory`: Manages the bike inventory.
        - `BankAccount`: Manages the shop's bank account.
    - **`edu.curtin.oose2024s1.assignment2.view`**:
        - `BikeShopView`: Displays shop statistics and messages.
    - **`edu.curtin.oose2024s1.assignment2.controller`**:
        - `BikeShopController`: Handles the main simulation logic and message processing.
        - `EventLoop`: Manages the event loop for the simulation.
    - **`edu.curtin.oose2024s1.assignment2.factory`**:
        - `BikeFactory`: Creates new `Bike` instances.
    - **`edu.curtin.oose2024s1.assignment2.observer`**:
        - `Observable`: Interface for observable objects.
        - `Observer`: Interface for observers.
    - **`edu.curtin.oose2024s1.assignment2.state`**:
        - `BikeState`: Interface for bike states.
        - `AvailableState`, `ServicingState`, `AwaitingPickupState`: Concrete states for bikes.

2. **Responsibilities**
    - **`BikeShopController`**: Implements the main logic for handling messages and updating the shop's state.
    - **`EventLoop`**: Manages the timing and execution of the simulation loop.
    - **`BikeFactory`**: Creates new bikes, potentially using dependency injection to manage dependencies.
    - **`BikeShopView`**: Displays current statistics and messages to the user.
    - **`Inventory`**: Keeps track of available bikes, bikes being serviced, and bikes awaiting pickup.
    - **`BankAccount`**: Manages the shop's finances, including handling purchases and employee payments.
    - **`BikeState` and Concrete States**: Manage the state transitions for bikes, implementing the State Pattern.
    - **`Observable` and `Observer` Interfaces**: Implement the Observer Pattern to manage notifications between components.

### Steps to Implement the Assignment

1. **Setup the Project**
    - ~~Start with the provided `2024_assignment2_starter_java.zip` and extract the contents.~~
    - ~~Organise the project structure into the planned packages.~~

2. **Implement the Model Classes**
    - ~~Create `Bike`, `Customer`, `Inventory`, and `BankAccount` classes.~~
    - ~~Implement necessary methods and attributes.~~

3. **Implement the View**
    - ~~Create `BikeShopView` to handle displaying statistics and messages.~~

4. **Implement the Controller**
    - ~~Create `BikeShopController` to handle the main logic.~~
    - ~~Implement methods to process each type of message.~~

5. **Implement the Factory**
    - ~~Create `BikeFactory` to handle bike creation.~~
    - Factory Pattern: `BikeFactory` class is simple and effective. Ensure that all object creation is done through this factory to maintain consistency. 
    - Dependency Injection: Ensure that dependencies are injected into classes rather than being created within the classes themselves. This makes your code more modular and easier to test.

6. **Implement the Observer Pattern**
    - ~~Create `Observable` and `Observer` interfaces.~~
    - ~~Implement the pattern in appropriate classes.~~
    - Implementation: Implementation of the Observer pattern is correct. Ensure that all necessary updates are propagated to observers. In BikeShopView class, implement the update method to refresh the view when the state changes.

7. **Implement the State Pattern**
    - ~~Create `BikeState` interface and concrete state classes.~~
    - ~~Implement state transitions.~~
    - State Transitions: Ensure that all state transitions are correctly handled in the BikeState implementations. Each state class should only handle operations that are valid for that state.

8. **Error Handling and Logging**
   - Exception Handling: Ensure that all potential error conditions are handled gracefully. For example, if a file cannot be opened, provide a meaningful error message and take appropriate action.
   - Logging: Add more logging statements to help trace the program's execution. Make sure to log significant events like state changes, errors, and important actions.

9. **Create UML Diagrams**
    - Draw class and state diagrams using draw.io.
    - Ensure diagrams accurately represent the implemented design.

10. **Testing and Validation**
    - Ensure no PMD warnings remain, unless justified.
    - Test the application to ensure it meets all requirements and handles errors gracefully.
    - Validate the output in `sim_results.txt`.
