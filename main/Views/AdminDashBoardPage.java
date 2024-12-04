package main.Views;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import main.Controller.Controller;
import main.DB.DB;
import main.Models.Flight;

/**
 * @author henap
 *
 */
public class AdminDashBoardPage extends JPanel implements ActionListener {

	private Controller controller;
	
	String username;
	JButton logout = new JButton("Logout");
	private JButton addFlight = new JButton("Add Flight");
    private JButton removeFlight = new JButton("Remove Flight");
    private JButton updateFlight = new JButton("Update Flight");
    private JButton searchDepartureCityButton = new JButton("Search by Departure City");
    private JButton searchDestinationCityButton = new JButton("Search by Destination City");
    private JButton filterByPriceButton = new JButton("Filter by Price");
    private JButton searchShortestTravelTimeButton = new JButton("Shortest Travel Time");
    private JButton searchTravelTimeButton = new JButton("Search Travel Time by cities");
    private JButton searchByDateButton = new JButton("Search by Dates");

    private JButton addAdminButton = new JButton("Add Admin");
    private JButton removeAdminButton = new JButton("Remove Admin");
    
	JPanel buttons = new JPanel();
	JPanel title = new JPanel();

	
	JLabel titleMsg;
	
	JPanel main;
	CardLayout card;
	private final JButton btnNewButton = new JButton("Profile");

    private JTable flightTable;
    private DB database; // Add a reference to the DB instance
    private JButton browseFlightsButton = new JButton("Browse Flights");
    private JScrollPane tableScrollPane;
	
    //private JButton updateFlightButton = new JButton("Update Flight");
	
	public AdminDashBoardPage(Controller controller) {
		setBackground(new Color(255, 255, 255));
		this.controller = controller;
		initialize();
	}
	
	private void initialize() {
		
		titleMsg = new JLabel("Welcome " + controller.getCurrentUser().getUsername() + "!");
        titleMsg.setFont(new Font("Arial", Font.BOLD, 18));

        title.setLayout(new BorderLayout()); // Set BorderLayout for title panel
        title.add(titleMsg, BorderLayout.CENTER); // Add title message to the center of the title panel

        // Set up the logout button and its position in the right corner
        logout.setBackground(new Color(0, 0, 0));
        logout.setForeground(new Color(255, 255, 255));
        logout.addActionListener(e -> controller.logout());

        // Set up the profile button and its position in the left corner
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.addActionListener(e -> {
            // controller.profilePage(controller.getCurrentUser()); --> NEED TO CREATE!!
        });

        // Create a small panel to hold the two buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        buttonPanel.add(btnNewButton);
        buttonPanel.add(logout);

        // Create the flight button panel with a vertical layout
        JPanel flightButtonPanel = new JPanel();
        flightButtonPanel.setLayout(new BoxLayout(flightButtonPanel, BoxLayout.Y_AXIS)); // Vertical layout
        flightButtonPanel.setBackground(Color.WHITE);

        // Add the buttons to the panel
        flightButtonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
        flightButtonPanel.add(browseFlightsButton);
        flightButtonPanel.add(Box.createVerticalStrut(10)); // Add spacing between buttons
        flightButtonPanel.add(addFlight);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(updateFlight);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(removeFlight);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(searchDepartureCityButton);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(searchDestinationCityButton);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(filterByPriceButton);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(searchShortestTravelTimeButton);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(searchTravelTimeButton);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(searchByDateButton);
        
        // Set button colors and listeners
        browseFlightsButton.setForeground(Color.WHITE);
        browseFlightsButton.setBackground(Color.BLACK);
        browseFlightsButton.addActionListener(e -> toggleBrowseFlights());

        addFlight.setForeground(Color.WHITE);
        addFlight.setBackground(Color.BLACK);
        addFlight.addActionListener(e -> addFlight());

        updateFlight.setForeground(Color.WHITE);
        updateFlight.setBackground(Color.BLACK);
        updateFlight.addActionListener(e -> openUpdateFlightForm());

        removeFlight.setForeground(Color.WHITE);
        removeFlight.setBackground(Color.BLACK);
        removeFlight.addActionListener(e -> removeFlight());

        searchDepartureCityButton.setForeground(Color.WHITE);
        searchDepartureCityButton.setBackground(Color.BLACK);
        searchDepartureCityButton.addActionListener(e -> searchByDepartureCity());

        searchDestinationCityButton.setForeground(Color.WHITE);
        searchDestinationCityButton.setBackground(Color.BLACK);
        searchDestinationCityButton.addActionListener(e -> searchByDestinationCity());

        filterByPriceButton.setForeground(Color.WHITE);
        filterByPriceButton.setBackground(Color.BLACK);
        filterByPriceButton.addActionListener(e -> filterByPriceRange());

        searchShortestTravelTimeButton.setForeground(Color.WHITE);
        searchShortestTravelTimeButton.setBackground(Color.BLACK);
        searchShortestTravelTimeButton.addActionListener(e -> searchShortestTravelTime());
        
        searchTravelTimeButton.setForeground(Color.WHITE);
        searchTravelTimeButton.setBackground(Color.BLACK);
        searchTravelTimeButton.addActionListener(e -> searchTravelTime());

        searchByDateButton.setForeground(Color.WHITE);
        searchByDateButton.setBackground(Color.BLACK);
        searchByDateButton.addActionListener(e -> showDateInputDialog());
        
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH); // Add title to the top
        
        // Add title and flightButtonPanel to the main layout
        this.add(flightButtonPanel, BorderLayout.WEST); // Place buttons on the left
        this.add(buttonPanel, BorderLayout.SOUTH); // Add the logout and profile buttons to the right

        // Set up the table for displaying flight data
        flightTable = new JTable();
        tableScrollPane = new JScrollPane(flightTable);
        tableScrollPane.setVisible(false); // Initially hidden
        this.add(tableScrollPane, BorderLayout.CENTER);

        addAdminButton.setForeground(Color.WHITE);
        addAdminButton.setBackground(Color.BLACK);
        addAdminButton.addActionListener(e -> addAdmin());

        removeAdminButton.setForeground(Color.WHITE);
        removeAdminButton.setBackground(Color.BLACK);
        removeAdminButton.addActionListener(e -> removeAdmin());

        flightButtonPanel.add(addAdminButton);
        flightButtonPanel.add(Box.createVerticalStrut(10));
        flightButtonPanel.add(removeAdminButton);

     }

     private void toggleBrowseFlights() {
        if (!tableScrollPane.isVisible()) {
            loadFlightData(); // Load flight data from the database
            tableScrollPane.setVisible(true);
            updateFlight.setVisible(true);
            removeFlight.setVisible(true);
        } else {
            tableScrollPane.setVisible(false);
            updateFlight.setVisible(false);
            removeFlight.setVisible(false);
        }
        revalidate();
        repaint();
    }

    private void loadFlightData() {
        try {
            ArrayList<Flight> flights = new ArrayList<>();
            int maxFlightID = 1000; // Replace with the actual maximum flight ID in your database
    
            for (int flightID = 1; flightID <= maxFlightID; flightID++) {
                Flight flight = controller.getDatabase().getFlight(flightID);
                if (flight != null) {
                    flights.add(flight); // Add to the list if flight exists
                }
            }

            // Prepare table data
            String[] columnNames = {"Flight ID", "Flight Number", "Departure City", "Destination City", 
                                     "Departure Time", "Arrival Time", "Price", "Seats Available"};
            Object[][] tableData = new Object[flights.size()][columnNames.length];

            for (int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
                tableData[i][0] = flight.getFlightID();
                tableData[i][1] = flight.getFlightNumber();
                tableData[i][2] = flight.getDepartureCity();
                tableData[i][3] = flight.getDestinationCity();
                tableData[i][4] = flight.getDepartureTime();
                tableData[i][5] = flight.getArrivalTime();
                tableData[i][6] = flight.getPrice();
                tableData[i][7] = flight.getSeatsAvailable();
            }

            // Set table model
            flightTable.setModel(new DefaultTableModel(tableData, columnNames));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load flight data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
    private void openUpdateFlightForm() {
        int selectedRow = flightTable.getSelectedRow();
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a flight to update.", "No Flight Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Get flight details from the selected row
        int flightID = (int) flightTable.getValueAt(selectedRow, 0);
        String flightNumber = (String) flightTable.getValueAt(selectedRow, 1);
        String departureCity = (String) flightTable.getValueAt(selectedRow, 2);
        String destinationCity = (String) flightTable.getValueAt(selectedRow, 3);
        String departureTime = (String) flightTable.getValueAt(selectedRow, 4);
        String arrivalTime = (String) flightTable.getValueAt(selectedRow, 5);
        double price = (double) flightTable.getValueAt(selectedRow, 6);
        int seatsAvailable = (int) flightTable.getValueAt(selectedRow, 7);
    
        // Open a new JFrame for updating the flight
        JFrame updateFrame = new JFrame("Update Flight");
        updateFrame.setSize(400, 400);
        updateFrame.setLayout(new GridLayout(0, 2));
    
        // Add form fields
        JTextField flightNumberField = new JTextField(flightNumber);
        JTextField departureCityField = new JTextField(departureCity);
        JTextField destinationCityField = new JTextField(destinationCity);
        JTextField departureTimeField = new JTextField(departureTime);
        JTextField arrivalTimeField = new JTextField(arrivalTime);
        JTextField priceField = new JTextField(String.valueOf(price));
        JTextField seatsAvailableField = new JTextField(String.valueOf(seatsAvailable));
    
        updateFrame.add(new JLabel("Flight Number:"));
        updateFrame.add(flightNumberField);
        updateFrame.add(new JLabel("Departure City:"));
        updateFrame.add(departureCityField);
        updateFrame.add(new JLabel("Destination City:"));
        updateFrame.add(destinationCityField);
        updateFrame.add(new JLabel("Departure Time:"));
        updateFrame.add(departureTimeField);
        updateFrame.add(new JLabel("Arrival Time:"));
        updateFrame.add(arrivalTimeField);
        updateFrame.add(new JLabel("Price:"));
        updateFrame.add(priceField);
        updateFrame.add(new JLabel("Seats Available:"));
        updateFrame.add(seatsAvailableField);
    
        // Add Save button
        JButton saveButton = new JButton("Save");
        // Define the date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        saveButton.addActionListener(e -> {
            try {
                // Get updated flight details
                String updatedFlightNumber = flightNumberField.getText();
                String updatedDepartureCity = departureCityField.getText();
                String updatedDestinationCity = destinationCityField.getText();
                LocalDateTime updatedDepartureTime = LocalDateTime.parse(departureTimeField.getText(), formatter);
                LocalDateTime updatedArrivalTime = LocalDateTime.parse(arrivalTimeField.getText(), formatter);
                double updatedPrice = Double.parseDouble(priceField.getText());
                int updatedSeatsAvailable = Integer.parseInt(seatsAvailableField.getText());
    
                // Call updateFlight method in Controller
                boolean success = controller.getDatabase().updateFlight(
                    flightID,
                    updatedFlightNumber,
                    updatedDepartureCity,
                    updatedDestinationCity,
                    updatedDepartureTime,
                    updatedArrivalTime,
                    updatedPrice,
                    updatedSeatsAvailable
                );
    
                if (success) {
                    JOptionPane.showMessageDialog(updateFrame, "Flight updated successfully!");
                    updateFrame.dispose();
                    loadFlightData(); // Refresh the flight table
                } else {
                    JOptionPane.showMessageDialog(updateFrame, "Failed to update flight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(updateFrame, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        updateFrame.add(saveButton);
        updateFrame.setVisible(true);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        if (e.getSource() == addFlight){
            addFlight();
        }
        else if(e.getSource() == removeFlight){
            removeFlight();
        }
	}

    void addFlight(){
        //Initializing the fields for all the parameters for createFlight() in DB.java
        JTextField flightNum = new JTextField();
        JTextField dep = new JTextField();
        JTextField arr = new JTextField();
        JTextField depTime = new JTextField();
        JTextField price = new JTextField();
        JTextField arrTime = new JTextField();
        JTextField ID = new JTextField();
        JTextField seats = new JTextField();


        Object[] fields = {
            "Flight Number: ", flightNum,
            "Flight ID: ", ID,
            "Departing City: ", dep,
            "Arriving City: ", arr,
            "Departing Time: ", depTime,
            "Arrival Time: ", arrTime,
            "Price: ", price,
            "Numebr of Seats: ", seats
        };
        //Prompt the user for inputs
        int prompt = JOptionPane.showConfirmDialog(this, fields, "New Flight", JOptionPane.OK_CANCEL_OPTION);
        // Define the date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //If user submits the inputs, set the fields to the corresponding inputs
        if (prompt == JOptionPane.OK_OPTION){
            try {
                String flightNumber = flightNum.getText(); 
                String depString = dep.getText();
                String arrString = arr.getText();
                LocalDateTime updatedDepartureTime = LocalDateTime.parse(depTime.getText(), formatter);
                LocalDateTime updatedArrivalTime = LocalDateTime.parse(arrTime.getText(), formatter);
                int flightID = Integer.valueOf(ID.getText());
                double cost = Double.valueOf(price.getText());
                int numSeats = Integer.valueOf(seats.getText());
                //Attempt to create the flight using the createFlight() in DB.java
                boolean result = controller.getDatabase().createFlight(flightID, flightNumber, depString, arrString, updatedDepartureTime, updatedArrivalTime, cost, numSeats);

                if (result){

                	  loadFlightData(); // Refresh the flight table
                    JOptionPane.showMessageDialog(this, "Flight has been added.");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Failed to add flight.");
                }
            }
            catch (Exception e) {

                JOptionPane.showMessageDialog(this, "One of the inputs was not accepted.", "Input error", JOptionPane.ERROR_MESSAGE);
            }
           
        }
    }

    void removeFlight(){
        //Get user input for the flight ID they would like to remove
        int flightID = Integer.valueOf(JOptionPane.showInputDialog("Flight ID you wish to remove: "));
        //Attempt to remove the flight
        boolean result = controller.getDatabase().deleteFlight(flightID);

        if (result){
            loadFlightData(); // Refresh the flight table
            JOptionPane.showMessageDialog(this, "Flight removed successfully");
        }
        else{
            JOptionPane.showMessageDialog(this, "Failed to remove flight.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
    
    private void searchByDepartureCity() {
        String city = JOptionPane.showInputDialog(this, "Enter the departing city:");
        if (city == null || city.isEmpty()) return;

        try {
            ArrayList<Flight> flights = controller.getDatabase().getFlightsByDepartureCity(city);
            if (flights.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No flights found for the departing city: " + city, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                updateFlightTable(flights);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching for flights: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByDestinationCity() {
        String city = JOptionPane.showInputDialog(this, "Enter the destination city:");
        if (city == null || city.isEmpty()) return;

        try {
            ArrayList<Flight> flights = controller.getDatabase().getFlightsByDestinationCity(city);
            if (flights.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No flights found for the destination city: " + city, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                updateFlightTable(flights);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching for flights: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateFlightTable(ArrayList<Flight> flights) {
        String[] columnNames = {"Flight ID", "Flight Number", "Departure City", "Destination City",
                                "Departure Time", "Arrival Time", "Price", "Seats Available"};
        Object[][] tableData = new Object[flights.size()][columnNames.length];

        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            tableData[i][0] = flight.getFlightID();
            tableData[i][1] = flight.getFlightNumber();
            tableData[i][2] = flight.getDepartureCity();
            tableData[i][3] = flight.getDestinationCity();
            tableData[i][4] = flight.getDepartureTime();
            tableData[i][5] = flight.getArrivalTime();
            tableData[i][6] = flight.getPrice();
            tableData[i][7] = flight.getSeatsAvailable();
        }

        flightTable.setModel(new DefaultTableModel(tableData, columnNames));
        tableScrollPane.setVisible(true);
        revalidate();
        repaint();
    }
    
    private void filterByPriceRange() {
        String minPriceInput = JOptionPane.showInputDialog(this, "Enter the minimum price:");
        if (minPriceInput == null || minPriceInput.isEmpty()) return;

        String maxPriceInput = JOptionPane.showInputDialog(this, "Enter the maximum price:");
        if (maxPriceInput == null || maxPriceInput.isEmpty()) return;

        try {
            double minPrice = Double.parseDouble(minPriceInput);
            double maxPrice = Double.parseDouble(maxPriceInput);

            if (minPrice < 0) {
                JOptionPane.showMessageDialog(this, "Minimum price cannot be less than $0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (minPrice > maxPrice) {
                JOptionPane.showMessageDialog(this, "Minimum price cannot be greater than maximum price.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ArrayList<Flight> filteredFlights = controller.getDatabase().getFlightsByPriceRange(minPrice, maxPrice);

            if (filteredFlights.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No flights found within the given price range.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                updateFlightTable(filteredFlights);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for prices.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchShortestTravelTime() {
    	try {
            ArrayList<Flight> flights = controller.getDatabase().getShortestTravelTimeFlights();
            if (flights.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No flights found with shortest travel time.");
            } else {
                updateFlightTable(flights); // Reuse your existing method
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching flights with shortest travel time: " + ex.getMessage());
        }
    }
 
    private void searchTravelTime() {
        // Show input dialogs to get departure city and destination city
        String departureCity = JOptionPane.showInputDialog(this, "Enter Departure City:");
        String destinationCity = JOptionPane.showInputDialog(this, "Enter Destination City:");

        // Check if both fields are filled
        if (departureCity == null || destinationCity == null || departureCity.trim().isEmpty() || destinationCity.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both departure and destination cities.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Call the database method to find all flights between the cities
        ArrayList<Flight> flights = controller.getDatabase().getShortestTravelTimeFlights(departureCity.trim(), destinationCity.trim());

        // Check if any flights were found and update the UI
        if (flights != null && !flights.isEmpty()) {
            updateFlightTable(flights); // Assumes you have this method to update the table
            JOptionPane.showMessageDialog(this, flights.size() + " flight(s) found and displayed.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No flights found between these cities.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showDateInputDialog() {
        // Dialog for the user to input the departure and arrival dates
    	JFrame parentFrame = new JFrame();  // Create a parent frame 
    	JDialog dateDialog = new JDialog(parentFrame, "Select Dates", true);
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel departureLabel = new JLabel("Departure Date: ");
        JLabel arrivalLabel = new JLabel("Arrival Date: ");

        // Date pickers for departure and arrival dates
        JDateChooser departureDatePicker = new JDateChooser();
        JDateChooser arrivalDatePicker = new JDateChooser();

        // Button to confirm the date selection
        JButton submitButton = new JButton("Search Flights");

        panel.add(departureLabel);
        panel.add(departureDatePicker);
        panel.add(arrivalLabel);
        panel.add(arrivalDatePicker);
        panel.add(new JLabel());  // Empty space for alignment
        panel.add(submitButton);

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            LocalDate departureDate = getDateFromDateChooser(departureDatePicker);
            LocalDate arrivalDate = getDateFromDateChooser(arrivalDatePicker);

            // Validate the dates
            if (departureDate != null && departureDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(dateDialog, "Departure date cannot be in the past.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (arrivalDate != null && arrivalDate.isBefore(departureDate)) {
                JOptionPane.showMessageDialog(dateDialog, "Arrival date cannot be before the departure date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the method to filter flights based on the dates
            searchFlightsByDates(departureDate, arrivalDate);

            // Close the dialog after the search
            dateDialog.dispose();
        });

        // Add the panel to the dialog and set properties
        dateDialog.add(panel);
        dateDialog.setSize(300, 200);
        dateDialog.setLocationRelativeTo(this);
        dateDialog.setVisible(true);
    }

    private LocalDate getDateFromDateChooser(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();
        if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;  // Return null if no date is selected
    }

    private void searchFlightsByDates(LocalDate departureDate, LocalDate arrivalDate) {
        // Calling the database with the selected dates
        ArrayList<Flight> flights = controller.getDatabase().getFlightsByDateRange(departureDate, arrivalDate);

        if (flights != null && !flights.isEmpty()) {
            updateFlightTable(flights);
            JOptionPane.showMessageDialog(this, "Flights filtered by date.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No flights found for the given dates.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addAdmin() {
        try {
            String adminIDInput = JOptionPane.showInputDialog(this, "Enter Admin ID:");
            String username = JOptionPane.showInputDialog(this, "Enter Admin Username:");
            String password = JOptionPane.showInputDialog(this, "Enter Admin Password:");
    
            if (adminIDInput == null || username == null || password == null ||
                adminIDInput.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            int adminID = Integer.parseInt(adminIDInput);
    
            if (controller.addAdmin(adminID, username, password)) {
                JOptionPane.showMessageDialog(this, "Admin added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add admin.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Admin ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeAdmin() {
        try {
            String adminIDInput = JOptionPane.showInputDialog(this, "Enter Admin ID to Remove:");
    
            if (adminIDInput == null || adminIDInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Admin ID is required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            int adminID = Integer.parseInt(adminIDInput);
    
            if (controller.removeAdmin(adminID)) {
                JOptionPane.showMessageDialog(this, "Admin removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove admin.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Admin ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
