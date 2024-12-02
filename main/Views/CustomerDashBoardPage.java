package main.Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.Controller.Controller;
import main.DB.DB;
import main.Models.Flight;

/**
 * @author henap
 *
 */
public class CustomerDashBoardPage extends JPanel implements ActionListener {

		private Controller controller;
		
		String username;
		JButton logout = new JButton("Logout");
		private JButton back = new JButton("Back");
		private JButton transactions = new JButton("Transactions"); 
	    private JButton browseFlightsButton = new JButton("Browse Flights");
	    private JButton searchDepartureCityButton = new JButton("Search by Departure City");
	    private JButton searchDestinationCityButton = new JButton("Search by Destination City");
	    private JButton searchShortestTravelTimeButton = new JButton("Shortest Travel Time");
	    private JButton searchTravelTimeButton = new JButton("Search Travel Time by cities");

		JPanel buttons = new JPanel();
		JPanel title = new JPanel();
		
		JLabel titleMsg;
		
		JPanel main;
		CardLayout card;

	    private JTable flightTable;
	    private JScrollPane tableScrollPane;
		
		
		public CustomerDashBoardPage(Controller controller) {
			setBackground(new Color(255, 255, 255));
			this.controller = controller;
			initialize();
		}
		
		private void initialize() {
			
			JLabel titleMsg = new JLabel("Welcome " + controller.getCurrentUser().getUsername() + "!");
	        titleMsg.setFont(new Font("Arial", Font.BOLD, 18));

	        JPanel title = new JPanel();
	        title.setLayout(new BorderLayout());
	        title.add(titleMsg, BorderLayout.NORTH);

	        logout.setBackground(Color.BLACK);
	        logout.setForeground(Color.WHITE);
	        logout.addActionListener(e -> controller.logout());
	        
	        back.setForeground(Color.WHITE);
	        back.setBackground(Color.BLACK);
	        back.addActionListener(e -> controller.logout());
	        
	        // Create a small panel to hold the two buttons
	        JPanel bottomPanel = new JPanel();
	        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
	        bottomPanel.add(back);
	        bottomPanel.add(logout);

	        transactions.setForeground(Color.WHITE);
	        transactions.setBackground(Color.BLACK);
	        transactions.addActionListener(e -> {
	            // Navigate to Transactions Page
	        });

	        // Create buttonPanel and set BoxLayout for vertical alignment
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical layout
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
	        
	        // Add other buttons to the panel
	        browseFlightsButton.setForeground(Color.WHITE);
	        browseFlightsButton.setBackground(Color.BLACK);
	        browseFlightsButton.addActionListener(e -> toggleBrowseFlights());
	        buttonPanel.add(browseFlightsButton);
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components

	        buttonPanel.add(searchDepartureCityButton);
	        searchDepartureCityButton.setForeground(Color.WHITE);
	        searchDepartureCityButton.setBackground(Color.BLACK);
	        searchDepartureCityButton.addActionListener(e -> searchByDepartureCity());
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components

	        buttonPanel.add(searchDestinationCityButton);
	        searchDestinationCityButton.setForeground(Color.WHITE);
	        searchDestinationCityButton.setBackground(Color.BLACK);
	        searchDestinationCityButton.addActionListener(e -> searchByDestinationCity());
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
	        
	        JButton filterByPriceButton = new JButton("Filter by Price");
	        filterByPriceButton.setForeground(Color.WHITE);
	        filterByPriceButton.setBackground(Color.BLACK);
	        filterByPriceButton.addActionListener(e -> filterByPriceRange());
	        buttonPanel.add(filterByPriceButton);
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
	        
	        // Set up the Shortest Travel Time button
	        searchShortestTravelTimeButton.setForeground(Color.WHITE);
	        searchShortestTravelTimeButton.setBackground(Color.BLACK);
	        searchShortestTravelTimeButton.addActionListener(e -> searchShortestTravelTime());
	        buttonPanel.add(searchShortestTravelTimeButton);
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
	        
	        searchTravelTimeButton.setForeground(Color.WHITE);
	        searchTravelTimeButton.setBackground(Color.BLACK);
	        searchTravelTimeButton.addActionListener(e -> searchTravelTime());
	        buttonPanel.add(searchTravelTimeButton);
	        
	        // Optionally, add some spacing between buttons for better layout
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
	        buttonPanel.add(transactions);
	        
	        setLayout(new BorderLayout());
	        add(title, BorderLayout.NORTH);
	        add(buttonPanel, BorderLayout.WEST);
	        add(bottomPanel, BorderLayout.SOUTH);
	        
	        flightTable = new JTable();
	        tableScrollPane = new JScrollPane(flightTable);
	        tableScrollPane.setVisible(false);
	        add(tableScrollPane, BorderLayout.CENTER);
	     }

	     private void toggleBrowseFlights() {
	        if (!tableScrollPane.isVisible()) {
	            loadFlightData(); // Load flight data from the database
	            tableScrollPane.setVisible(true);
	        } else {
	            tableScrollPane.setVisible(false);
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

	        // Call the database method to find the shortest travel time flight
	        Flight shortestFlight = controller.getDatabase().getShortestTravelTimeFlight(departureCity.trim(), destinationCity.trim());

	        // Check if a flight was found and update the UI
	        if (shortestFlight != null) {
	            ArrayList<Flight> flights = new ArrayList<>();
	            flights.add(shortestFlight);
	            updateFlightTable(flights); // Assumes you have this method to update the table
	            JOptionPane.showMessageDialog(this, "Shortest travel time flight displayed.", "Info", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "No flights found between these cities.", "Info", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }

	    
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}

	}
