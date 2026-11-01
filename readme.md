# Mars Colony Simulation

A Java console application that simulates the daily operations and resource management of a Mars colony. This project heavily utilizes Object-Oriented Programming (OOP) principles—such as abstraction, inheritance, interfaces, and polymorphism—to model a dynamic environment where agents, facilities, and missions interact.

## Core Mechanics

*   **Facilities & Resource Management:** The colony consists of distinct facilities (Habitats, Laboratories, Storage, etc.) that house agents, equipment, and resources. If a facility goes offline (e.g., due to a fire), its resources become inaccessible.
*   **Agent System:** Personnel are divided into Human `CrewMember`s (Scientists, Engineers, Medical Officers) and `RoboticUnit`s (Drones, Rovers, Repair Bots). Each agent role has specific skill sets, fatigue/battery levels, and restrictions on which missions or emergencies they can handle.
*   **Mission Dispatch:** The colony supports Exploration, Repair, Rescue, and Research missions. When a mission is submitted, the system automatically attempts to assemble a qualified team from the available agent pool, validates equipment/resources, and tracks mission progress over time. Only one mission can be active at a time.
*   **Emergency Handling:** Simulates localized crises (Fires, Oxygen Leaks, Rover Breakdowns, Medical Emergencies). The `EmergencyHandler` dynamically dispatches available, qualified agents to the affected location. Success consumes specific resources (like spare parts or medical supplies), while failures result in compounding penalties (e.g., continued oxygen loss).
*   **Environmental Events:** External conditions, such as dust storms, dynamically affect colony operations, halting ongoing exploration missions and preventing new ones from launching.

## Project Structure

The codebase is organized into modular packages:
*   `agents/`: Interfaces and implementations for all colony personnel and robots.
*   `colony/`: Core management classes (`MarsColony`, `EmergencyHandler`, and the `Main` entry point).
*   `emergencies/`: Logic for different crisis events and their specific penalties.
*   `enums/`: Constants for roles, resources, facility types, mission states, etc.
*   `equipments/`: Tools required for specific mission types.
*   `facilities/`: Colony infrastructure, including Habitats, Labs, and Storage.
*   `missions/`: Abstract mission logic and specific concrete implementations.
*   `resources/`: Inventory mapping and consumption logic.

## Compiling and Running

1. Ensure you have the Java Development Kit (JDK) installed.
2. Open a terminal in the root directory of the project.
3. Compile all source files (assuming a standard `src` and `bin` structure):
   ```bash
   javac -d bin src/**/*.java
   ```
4. Run the main demonstration scenarios:
   ```bash
   java -cp bin colony.Main
   ```

## Demonstration Scenarios

The `colony.Main` class contains six pre-configured scenarios that validate the system's strict logic checks:
1.  **Successful Exploration:** A standard mission launching and completing successfully over several days.
2.  **Resource Shortage:** Mission rejection due to insufficient colony resources.
3.  **Unsuitable Agent:** Mission rejection when the available agents lack the required `SkillType`.
4.  **Oxygen Leak:** Tests dynamic emergency resolution using an Engineer, a Repair Robot, and spare parts.
5.  **Rover Breakdown:** Tests mid-mission failures and robotic repair mechanics.
6.  **Dust Storm:** Tests environmental blocks suspending outdoor operations.