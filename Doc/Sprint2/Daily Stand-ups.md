**Hena Patel**
-
November 27th - Sprint 2 Standup 1
-
What did you work on since the last standup?
- AdminDashBoardPage and CustomerDashBoardPage:
  - Adjusted flightButtonPanel to use vertical BoxLayout and added vertical spacing for buttons.
  - Positioned flightButtonPanel on the left for better UI organization.
  - Refined button actions for adding, removing, and updating flights and searching by city.
- DB.java:
  - Added getFlightsByDepartureCity(String city) and getFlightsByDestinationCity(String city) to fetch flights based on departure or destination cities from the database.

What do you commit to next?
  - Working on completing Task ID: AIR-11

When do you think you'll be done?
  - The next standup should complete Integration and testing (November 30th).

Do you have any blockers?
- None currently, but additional testing might reveal issues with city-based search performance or edge cases.

November 29th - Sprint 2 Standup 2
-
What did you work on since the last standup?
- CustomerDashBoardPage and AdminDashBoardPage:
  - Implemented filtering flights by minimum and maximum prices.
  - Added error handling for minimum prices below $0.
  - Integrated UI components for flight filtering.
- DB.java:
  - Added mapResultSetToFlight to map database rows to Flight objects.
  - Implemented getFlightsByPriceRange to retrieve flights within a specified price range.

What do you commit to next?
  - Task ID: AIR-12
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
- DB.java:
  - Implemented filtering by price range and shortest travel time.
  - Added methods to retrieve flights based on departure and destination cities.
  - Fixed inconsistencies in flight creation and update queries.
- CustomerDashBoardPage and AdminDashBoardPage:
  - Handled user inputs for filtering by travel time and cities through pop-up dialogs.

What do you commit to next?
  - Task ID: AIR-13
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

When do you think you'll be done?
  - All testing will be completed by December 2nd.

Do you have any blockers?
- None at the moment, but testing may uncover unanticipated bugs.




Ossama
-




Dilpreet
-

