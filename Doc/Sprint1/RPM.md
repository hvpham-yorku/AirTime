
Release Planning Meeting
Date: November 11th 2024
Time: 7:00 PM
Location: ZOOM Meeting

The following participants attended and contributed to the meeting:

Dilpreet Bansi
Hena Patel 
Ossama Benaini

Release Goals: 

The primary goal of this release is to deliver a vaiable product for the Flight Booking System. This includes enabling both admin and customer functionalities to interact with the system seamlessly.

Specific Goals:
Provide admin functionalities to manage flights (add, update, delete flights).
Implement flight search and booking features for customers.
Ensure robust user authentication for both admins and customers.
Deploy a user-friendly interface for all users.
Establish a reliable backend that communicates effectively with the database.

Project Scope: 
The release scope includes the following Epics/Key Features:

Admin Features:
Add Flight: Allow admins to input and save flight details (origin, destination, date, time, price, and seats available).
Update Flight: Provide functionality to modify existing flight details.
Remove Flight: Enable admins to remove flights from the database.
Browse Flights: Display all flights in a JTable for easy management.

Customer Features:
Search Flights: Allow customers to search for flights based on origin, destination, and date.
Book Flights: Enable customers to select a flight, choose a seat, and confirm booking.
View Bookings: Display a list of customer bookings.

Authentication:
Implement secure login for both admins and customers.
Ensure role-based access control.

Error Handling:
Handle invalid inputs and database connection failures gracefully.
Provide user-friendly error messages.

User Stories for Completion During the Release
The following user stories were prioritized for this release:

Admin User Stories:

As an admin, I want to add new flights so that customers can book them.
Acceptance Criteria: Admin inputs valid flight details and clicks "Add Flight". The flight appears in the flight list.

As an admin, I want to update flight details so that incorrect information can be corrected.
Acceptance Criteria: Admin selects a flight from the list, edits details in a form, and clicks "Save". Updates reflect in the flight list.

As an admin, I want to remove flights so that outdated or canceled flights are removed.
Acceptance Criteria: Admin selects a flight and clicks "Remove". The flight is removed from the list.

Customer User Stories:
As a customer, I want to search for flights so that I can view available options.
Acceptance Criteria: Customer inputs search criteria and clicks "Search". Matching flights appear in a JTable.

As a customer, I want to book a flight so that I can secure a seat.
Acceptance Criteria: Customer selects a flight, chooses a seat, and clicks "Book". A confirmation message appears.

Meeting Outcomes:
All participants agreed on the release goals and scope.
Prioritized the most critical user stories for delivery within this release cycle.