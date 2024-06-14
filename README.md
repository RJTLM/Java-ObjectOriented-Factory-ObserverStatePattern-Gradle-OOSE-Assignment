## Java-ObjectOriented-Factory-ObserverStatePattern-Gradle

This repository contains the implementation of a Bike Shop simulation project for the Object-Oriented Software Engineering (OOSE) course, Semester 1, 2024. The project demonstrates the application of various design patterns including Factory, Observer, and State patterns using Java. The project is built and managed using Gradle.

### Project Overview

The Bike Shop simulation includes functionalities for selling and repairing bikes, handling various customer interactions, and managing the shop's inventory and finances. The simulation runs in an event loop, processing input messages that trigger different operations in the shop.

### Key Features

- **Object-Oriented Design**: Utilises principles of object-oriented programming to create a modular and maintainable codebase.
- **Factory Pattern**: Implements factories for creating objects, promoting loose coupling and flexibility.
- **Observer Pattern**: Applies the observer pattern to manage and respond to changes in state across different parts of the application.
- **State Pattern**: Uses the state pattern to handle the various states and transitions within the bike shop simulation.
- **Gradle Build System**: The project is managed using Gradle, providing an efficient build process and dependency management.

### Contents

- **src**: Source code implementing the Bike Shop simulation.
- **documents**: Documentation including UML diagrams and detailed descriptions of the design and architecture.
- **build.gradle**: Gradle build configuration file.

### Directory Structure

```plaintext
2024_assignment2_starter_java [assignment2_app]
│
├── .gradle
├── .idea
├── build
├── documents
│   ├── Brief.txt
│   ├── criteria.md
│   ├── LogOutput.JPG
│   ├── ObserverPatternDirectory.JPG
│   ├── PackageDirectory.JPG
│   ├── Plan.md
│   ├── sim_resultsOutput.JPG
│   ├── TerminalOutput.JPG
│   ├── UMLGeneralBikeStateChart.png
│   ├── UMLBikeDropOffStateChart.png
│   ├── UMLClassDiagram.png
│   └── UMLBikePickUpStateChart.png
│
├── gradle
├── src
│   └── main
│       └── java
│           └── edu
│               └── curtin
│                   └── oose2024s1
│                       └── assignment2
│                           ├── controller
│                           │   ├── BikeShopController.java
│                           │   └── EventLoop.java
│                           ├── factory
│                           │   └── BikeFactory.java
│                           ├── model
│                           │   ├── BankAccount.java
│                           │   ├── Bike.java
│                           │   ├── Customer.java
│                           │   └── Inventory.java
│                           ├── observer
│                           │   ├── Observable.java
│                           │   └── Observer.java
│                           ├── state
│                           │   ├── AvailableState.java
│                           │   ├── AwaitingPickupState.java
│                           │   ├── BikeState.java
│                           │   └── ServicingState.java
│                           └── view
│                               ├── BikeShopView.java
│                               ├── App.java
│                               └── BikeShopInput.java
│
├── app0.log
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── LICENSE
├── logging.properties
├── oose-pmd-rules.xml
├── README.md
├── settings.gradle
└── sim_results.txt
```

### Getting Started

1. **Clone the repository (Note this is currently private)**:
   ```sh
   git clone https://github.com/RJTLM/Java-ObjectOriented-Factory-ObserverStatePattern-Gradle.git
   ```

2. **Navigate to the project directory**:
   ```sh
   cd Java-ObjectOriented-Factory-ObserverStatePattern-Gradle
   ```

3. **Build the project using Gradle**:
   ```sh
   gradle build
   ```
OR
   ```sh
   gradle ./build
   ```

4. **Run the simulation**:
   ```sh
   gradle run
   ```
OR
   ```sh
   gradle ./run
   ```

This will vary depending on OS, IDE use, etc.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes.

### Acknowledgements

This project is a part of the Object Oriented Software Engineering (OOSE)(COMP2003) unit at Curtin University.
