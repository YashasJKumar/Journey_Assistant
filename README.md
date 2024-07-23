# Journey Assistant

Journey Assistant is a Java application that helps users manage and plan their travel using different types of vehicles. It provides functionality to insert, delete, update, and view journey data stored in a MySQL database.

![image](https://cdn.pixabay.com/animation/2023/09/26/12/59/12-59-46-685_512.gif)


A simple Java program which is connected to the database via JDBC and illustrates all core fundamentals of Java OOP.


## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Classes](#classes)
- [Exception Handling](#exception-handling)
- [Contributing](#contributing)

## Features

- Insert journey data for cars and bikes
- Delete the last record from the database
- Update vehicle names in the database
- View all journey records in the database
- Calculate fuel quantity required for a journey
- Calculate fuel price based on fuel type and quantity
- Handle exceptions for invalid seat numbers

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver for MySQL)

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/journey-assistant.git
   cd journey-assistant
   ```

2. Set up the MySQL database:
   - Create a new database
   - Update the database connection details in the `Main.java` file:
     ```java
     String url = "jdbc:mysql://localhost:3306/your-database-name";
     String uname = "your_username";
     String password = "your_password";
     ```

3. Compile the Java files:
   ```sh
   javac Project/*.java
   ```

## Usage

To run the Journey Assistant application:

1. Navigate to the project directory
2. Run the main class:
   ```sh
   java Project.Main
   ```
3. Follow the on-screen prompts to interact with the application

## Database Schema

The application uses a table named `Journey` with the following structure:

- SL_No (INT): Serial number (Primary Key)
- Name (VARCHAR): Vehicle name
- Type (VARCHAR): Vehicle type (Car or Bike)
- Manufacturer (VARCHAR): Vehicle manufacturer
- Fuel_Type (VARCHAR): Type of fuel used
- Mileage (FLOAT): Vehicle mileage
- Distance (FLOAT): Travel distance
- Fuel_Required (FLOAT): Quantity of fuel required
- Price (FLOAT): Total fuel price for the journey

## Classes

- `Main`: The main class that handles user interaction and database operations
- `Automobile`: Base class for vehicles
- `Car`: Subclass of Automobile for car-specific operations
- `Bike`: Subclass of Automobile for bike-specific operations

## Exception Handling

The application includes a custom exception `Invalid_seat` to handle cases where the number of persons exceeds the available seats in the vehicle.

## Contributing

Contributions to the Journey Assistant project are welcome. Please ensure that your code adheres to the project's coding standards and includes appropriate error handling.

