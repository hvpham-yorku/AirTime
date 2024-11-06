![alt text]("C:\Users\Dilpreet Bansi\Pictures\Screenshots\Screenshot 2024-11-05 230350.png")




# AirTime Flight Booking System

## Motivation
AirTime is a user-friendly flight booking system developed to address common issues in online flight bookings, such as overbooking. Unlike competitors like Air Canada, Expedia, Google Flights, and Skyscanner, AirTime guarantees that no flight will be overbooked, providing customers—especially economy travelers—with peace of mind knowing their seats are secured. By preventing overbooking, AirTime improves the booking experience and aims to build trust and loyalty among users.

The system also features a simple interface designed with Java Swing, allowing admins to manage flights (add, delete, update) while enabling customers to search for flights, select seats, and book with ease. Backend security and efficient data management are ensured through MySQL integration, making AirTime reliable and secure for both administrators and customers.

## Installation

### Prerequisites
- **Java Development Kit (JDK) 8 or higher** - for compiling and running the Java code.
- **MySQL Server** - for database management.
- **Java Swing** - included in the JDK.
- **JDBC Driver for MySQL** - to connect Java applications with MySQL.

### Setup Instructions

1. **Clone the Repository**
   git clone https://github.com/hvpham-yorku/AirTime.git

2. **Database Setup**
    Install MySQL and create a database for the application:
    
    CREATE DATABASE airtime_db;

3. **Configure Database Connection**

4. **Run the Application**

## Usage

### Admin Features:

* Admin Login: Access the Admin Dashboard using valid credentials.
* Admin Dashboard: Manage flights by adding, deleting, or updating flight details (e.g., origin, destination, date, seats, price).

### Customer Features:

* Flight Search: Enter search criteria to view available flights.
* Booking: Select a flight, choose a seat, and confirm the booking with personal and payment details.

## Contribution

We welcome contributions! Here’s how to get started:

1. Fork the Repository: Create a fork of the project on GitHub.
2. Create a Branch: Use a consistent naming scheme for branches.
    * Feature branches: feature/short-description
    * Bugfix branches: bugfix/short-description

3. Git Flow: We use a simple Git flow model.
    * Work on feature branches off of main.
    * Submit pull requests (PRs) to merge changes into main.
4. Ticketing System: Open an issue for discussion on GitHub before starting work, to avoid duplicate efforts.
5. Pull Requests: Submit PRs on GitHub for review. Ensure PRs are well-documented, describe changes made, and reference any relevant issue numbers.

## License
This project is open-source and distributed.

## Acknowledgments

We would like to acknowledge the following competitors for highlighting gaps in the industry and inspiring AirTime’s commitment to solving overbooking:

* Air Canada
* Expedia
* Google Flights
* Skyscanner