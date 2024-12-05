**Hena Patel**
-
November 27th - Sprint 2 Standup 1
-
What did you work on since the last standup?

Task IDs:  **AIR-9** (Any user should be able to search for a specific departing city. An error message will be displayed to the user if the departing city is not in our databases) and **AIR-10** (Any user should be able to search for a specific destination. An error message will be displayed to the user if the destination city is not in our databases)
- AdminDashBoardPage and CustomerDashBoardPage:
  - Adjusted flightButtonPanel to use vertical BoxLayout and added vertical spacing for buttons.
  - Positioned flightButtonPanel on the left for better UI organization.
  - Refined button actions for adding, removing, and updating flights and searching by city.
- DB.java:
  - Added getFlightsByDepartureCity(String city) and getFlightsByDestinationCity(String city) to fetch flights based on departure or destination cities from the database.

What do you commit to next?
  - Working on completing Task ID: *AIR-11*

When do you think you'll be done?
  - The next standup should complete Integration and testing (November 30th).

Do you have any blockers?
- None currently, but additional testing might reveal issues with city-based search performance or edge cases.

November 29th - Sprint 2 Standup 2
-
What did you work on since the last standup?

Task ID: **AIR-11** (Any user should be able to filter with minimum and maximum prices. An error message will be displayed if the user inputs a minimum price of less than 0$)
- CustomerDashBoardPage and AdminDashBoardPage:
  - Implemented filtering flights by minimum and maximum prices.
  - Added error handling for minimum prices below $0.
  - Integrated UI components for flight filtering.
- DB.java:
  - Added mapResultSetToFlight to map database rows to Flight objects.
  - Implemented getFlightsByPriceRange to retrieve flights within a specified price range.

What do you commit to next?
  - Task ID: *AIR-12*
  - Enhance the DB.java class with filtering for shortest travel time.
  - Update dashboards to handle user inputs for filtering by travel time and cities.
  - Improve flight creation and update queries with error handling

When do you think you'll be done?
- All tasks will be completed by November 30th
  
Do you have any blockers?
- None currently, but further UI integration might need extra debugging.
  
November 30th - Sprint 2 Standup 3
-
What did you work on since the last standup?

Task ID: **AIR-12** (Any user should be able to filter to search for shortest travel time or to omit connecting flights)
- DB.java:
  - Implemented filtering by price range and shortest travel time.
  - Added methods to retrieve flights based on departure and destination cities.
  - Fixed inconsistencies in flight creation and update queries.
- CustomerDashBoardPage and AdminDashBoardPage:
  - Handled user inputs for filtering by travel time and cities through pop-up dialogs.

What do you commit to next?
  - Task ID: *AIR-13*
  - Implement date filtering in both dashboards.
  - Integrate JDateChooser for user-friendly date selection.
  - Add error handling for invalid date inputs.

When do you think you'll be done?
  - All tasks will be completed by December 1st.

Do you have any blockers?
- None, but ensuring compatibility with JDateChooser might need additional testing.

December 1st - Sprint 2 Standup 4
-
What did you work on since the last standup?

Task ID: **AIR-13** (Any user should be able to filter the search by departing date or arrival date. An error will be displayed to the user if the departing date has already passed or if the arrival date is before the departing date)
- CustomerDashBoardPage and AdminDashBoardPage:
  - Implemented filtering by departure and arrival dates.
  - Integrated JDateChooser for date selection.
  - Added error handling for invalid or past date selections.
  - Updated UI to handle multiple flights and display flight count.
- DB.java:
  - Added getFlightsByDateRange to retrieve flights based on date filtering.
  - Enhanced queries for travel duration using TIMESTAMPDIFF.
- Dependencies:
  - Added JCalendar JAR and moved MySQL Connector JAR to the dependencies folder.

What do you commit to next?
  - Perform end-to-end testing of all filtering features.
  - Completed remaining documentation (JIRA related)
  - Update system design documentation

When do you think you'll be done?
  - All testing will be completed by December 2nd.

Do you have any blockers?
- None at the moment, but testing may uncover unanticipated bugs.




Ossama
-
November 27th - Sprint 2 Standup 1
-

What did you work on since the last standup?

Task ID: AIR-14 (Any user should be able to book as many flights as they would like.)

CustomerDashBoardPage:
  - Checked and removed any restrictions on the number of flights that a customer is allowed to book.
  - Removed restrictions on the same customer booking two flights at the same time.

When do you think you'll be done?
  - All testing will be completed by December 3nd.

What do you commit to next?
  - Working on completing Task ID: AIR-17

Do you have any blockers?
  - None currently, but buttons and GUI might become oversaturated with the addition of new methods.

December 1st - Sprint 2 Standup 2
-

What did you work on since the last standup?
  - Task ID: AIR-17 (Any user should be able to view transaction history and flight history.)

User.java
  - Added some parameters to the User class such as ArrayLists to hold past transactions and flights.
  - Added the getter and setter methods for those parameters.

CustomerDashBoardPage.java
  - Implemented the checkTransactionHistory() method for users to check their transaction history.
  - Implemented the buttons and panels for the user to access their transaction history.

When do you think you'll be done?
  - All implementation and testing will be complete by December 3rd.

Do you have any blockers?
  - None at the moment.

December 2nd - Sprint 2 Standup 3
-

What did you work on since the last standup?
  - Task ID: AIR-17 (Any user should be able to see their transaction history and flight history)

CustomerDashBoardPage:
  - Implemented the checkFlightHistory() method for users to be able to get their past flights.
  - Implemented the buttons and panels for users to access their past flights.

Users.java:
  - Added a bookings ArrayList as a parameter for Users.
  - Implemented the getter and setter methods for that parameter.

When do you think you'll be done?
  - All testing will be completed by December 3nd.

Do you have any blockers?
  - None at the moment.

December 3rd - Sprint 2 Standup 4
-
What did you work on since the last standup?
  - Task ID: AIR-15 (Any user should be able to cancel a flight that they have booked.)

CustomerDashBoardPage:
  - Implemented the cancelFlight() method for users to be able to cancel any flgihts that they have booked.
  - Added the appropriate buttons and panels for the cancelFlight() method.

When do you think you'll be done?
  - All testing will be completed by December 3nd.

Do you have any blockers?
  - There are some issues with the buttons and panels not showing up for some of the methods.

December 3rd - Sprint 2 Standup 5
-
What did you work on since the last standup?
  - Task ID: AIR-16 (Any user should be able to recieve a refund for a flight that they cancel if it is within a certain amount of time.

CustomerDashBoardPage:
  - Updated the cancelFlight() method to include a refund to the users account if the current date was within two weeks of the booked date.

User.Java:
  - Added an accountBalance parameter for the user.
  -  Implemented the getter and setter methods for that parameter.

When do you think you'll be done?
  - All testing will be completed by December 3rd.

Do you have any blockers?
  - The buttons and panels for some of the methods are interfering with each other, and therefore are not being displayed correctly.

**Dilpreet**
-November 27th - Sprint 2 Standup 1

What did you work on since the last standup?
Task ID: AIR-18 (Any user must be able to add a booking into their checkout cart and continue shopping before they decide to pay if they want to)

CustomerDashBoardPage:
  - Implemented the ability for users to add flights to their cart by flight ID.
  - Added a viewCart() method to display the current contents of the user's cart in a table format.
  - Integrated clearCart() to allow users to remove all items from their cart.

User.java:
  - Added methods to the User class

DB.java:
  - Ensured flight data retrieval is robust for adding flights to the cart.

When do you think you'll be done?
  - All testing will be completed by December 3nd.

Do you have any blockers?
  - None at the moment, but testing may uncover unanticipated bugs.

- November 29th - Sprint 2 Standup 2
What did you work on since the last standup?
Task ID: AIR-19 (Users must pay to book a flight)

CustomerDashBoardPage:
  - Added a "Process Payments" button for users to finalize bookings.
  - Integrated with the createTransaction method to update the database with the booking and payment details.
  - Added UI messages to confirm successful payments or display errors for empty carts.

DB.java:
  - Updated createTransaction to log transactions for specific users.
  - Added logic to decrement available seats for a flight upon successful payment.

When do you think you'll be done?
  - All testing will be completed by December 3nd.

Do you have any blockers?
  - None at the moment, but testing may uncover unanticipated bugs.

- November 30th - Sprint 2 Standup 3
What did you work on since the last standup?
Task ID: AIR-20 (Any user should be able to add travel insurance to their booking. If travel insurance has already been added, display an error message)

CustomerDashBoardPage:
  - Added an "Add Travel Insurance" button for selected bookings in the cart.
  - Integrated checks to ensure travel insurance is not duplicated.
  - Displayed appropriate error messages if the user attempts to add travel insurance twice.

DB.java:
  - Enhanced updateBooking to modify the travel insurance flag for specific bookings.
  - Validated if the booking ID exists before updating to handle edge cases.

When do you think you'll be done?
  - All testing will be completed by December 3nd.

Do you have any blockers?
  - None at the moment, but testing may uncover unanticipated bugs.


- December 1st - Sprint 2 Standup 4
What did you work on since the last standup?
Task ID: AIR-21 (The system must be able to add or remove admins from the system. An error will be displayed by the system if it is attempting to remove an admin from the system that does not exist)

AdminDashBoardPage:
  - Added "Add Admin" and "Remove Admin" buttons.
  - Implemented pop-up prompts to collect admin details for addition or removal.
  - Displayed error messages if the system attempts to remove a non-existent admin.

Controller.java:
  - Updated createUser to include validation for admin role creation.
  - Added logic to verify and delete admin accounts when requested by authorized users.

DB.java:
  - Enhanced deleteUser to check user roles before deletion to ensure only admin users are removed via this function.

When do you think you'll be done?
  - All testing will be completed by December 4nd.

Do you have any blockers?
  - None at the moment, but testing may uncover unanticipated bugs.

