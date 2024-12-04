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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import main.Controller.Controller;
import main.DB.DB;
import main.Models.Booking;
import main.Models.Flight;
import main.Models.Transaction;
import main.Models.User;

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
	    private JButton searchByDateButton = new JButton("Search by Dates");
		private JButton addToCartButton = new JButton("Add to Cart");
		private JButton viewCartButton = new JButton("View Cart");
		private JButton clearCartButton = new JButton("Clear Cart");
		private JButton payButton = new JButton("Pay for Flights");
		private JButton addTravelInsuranceButton = new JButton("Add Travel Insurance");
		

		JPanel buttons = new JPanel();
		JPanel title = new JPanel();
		
		JLabel titleMsg;
		
		JPanel main;
		CardLayout card;

	    private JTable flightTable;
	    private JScrollPane tableScrollPane;
		private JTable transactionTable;
		private JScrollPane transTableScrollPane;
		private JTable historyTable;
		private JScrollPane historyTableScrollPane;
		private JTable cartTable;
		private JScrollPane cartScrollPane;
		
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
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
	        
	        searchByDateButton.setForeground(Color.WHITE);
	        searchByDateButton.setBackground(Color.BLACK);
	        searchByDateButton.addActionListener(e -> showDateInputDialog());
	        buttonPanel.add(searchByDateButton);
	        
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

			
			//Creating buttons to check transaction and history for user
			JButton transactionHistoryButton = new JButton("Transaction History");
			JButton flightHistoryButton = new JButton("Flight History");

			// Configure Transaction History Button
			transactionHistoryButton.setForeground(Color.WHITE);
			transactionHistoryButton.setBackground(Color.BLACK);
			transactionHistoryButton.addActionListener(e -> transactionHistoryCheck());
			buttonPanel.add(transactionHistoryButton);
			buttonPanel.add(Box.createVerticalStrut(10)); // Adds spacing
	 
			// Configure Flight History Button
			flightHistoryButton.setForeground(Color.WHITE);
			flightHistoryButton.setBackground(Color.BLACK);
			flightHistoryButton.addActionListener(e -> flightHistoryCheck());
			buttonPanel.add(flightHistoryButton);
			buttonPanel.add(Box.createVerticalStrut(10)); // Adds spacing
	 
			setLayout(new BorderLayout());
			add(title, BorderLayout.NORTH);
			add(buttonPanel, BorderLayout.WEST);
			add(bottomPanel, BorderLayout.SOUTH);
	 
			transactionTable = new JTable();
			transTableScrollPane = new JScrollPane(transactionTable);
			transTableScrollPane.setVisible(false);
			add(transTableScrollPane, BorderLayout.CENTER);
	 
			historyTable = new JTable();
			historyTableScrollPane = new JScrollPane(historyTable);
			historyTableScrollPane.setVisible(false);
			add(historyTableScrollPane, BorderLayout.CENTER);


			// Create the Cancel Flight button
			JButton cancelFlightButton = new JButton("Cancel Flight");
			cancelFlightButton.setForeground(Color.WHITE);
			cancelFlightButton.setBackground(Color.BLACK);

			// Add action listener for the button to call the cancelFlight() method
			cancelFlightButton.addActionListener(e -> cancelFlight());

			// Add the button to the button panel
			buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components
			buttonPanel.add(cancelFlightButton);
		 
			 // Add "Add to Cart" Button
			 addToCartButton.addActionListener(e -> addToCart());
			 buttonPanel.add(addToCartButton);
		 
			 // Add "View Cart" Button
			 viewCartButton.addActionListener(e -> viewCart());
			 buttonPanel.add(viewCartButton);
		 
			 // Add "Clear Cart" Button
			 clearCartButton.addActionListener(e -> clearCart());
			 buttonPanel.add(clearCartButton);
		 
			 // Add Cart Table
			 cartTable = new JTable();
			 cartScrollPane = new JScrollPane(cartTable);
			 cartScrollPane.setVisible(false); // Initially hidden
			 add(cartScrollPane, BorderLayout.CENTER);
		 
			 add(buttonPanel, BorderLayout.NORTH);

			 buttonPanel.add(Box.createVerticalStrut(10)); // Adds spacing
			 buttonPanel.add(payButton);

			 payButton.setForeground(Color.WHITE);
			 payButton.setBackground(Color.BLACK);
			 payButton.addActionListener(e -> processPayment());

			 buttonPanel.add(Box.createVerticalStrut(10)); // Adds spacing
			 buttonPanel.add(addTravelInsuranceButton);

			 addTravelInsuranceButton.setForeground(Color.WHITE);
			 addTravelInsuranceButton.setBackground(Color.BLACK);
			 addTravelInsuranceButton.addActionListener(e -> addTravelInsurance());

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

		private void transactionHistoryCheck(){

			User user = controller.getCurrentUser();

			ArrayList<Transaction> transactions = user.getTransactions();
			
			if (transactions.isEmpty()){

				JOptionPane.showMessageDialog(this, "Your transaction history is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);

			}

			String[] columns = {"Transaction ID", "Booking ID", "Transaction Date", "Price"};
			Object[][] table = new Object[transactions.size()][columns.length];

			for (int i = 0; i < transactions.size(); i++){

				table[i][0] = transactions.get(i).getTransactionID();
				table[i][1] = transactions.get(i).getBookingID();
				table[i][2] = transactions.get(i).getTransactionDate();
				table[i][3] = transactions.get(i).getAmount();
			}

			transactionTable.setModel(new DefaultTableModel(table, columns));
	        transTableScrollPane.setVisible(true);
	        revalidate();
	        repaint();
		}

		private void flightHistoryCheck(){

			User user = controller.getCurrentUser();

			LocalDateTime now = LocalDateTime.now();

			ArrayList<Flight> flights = user.getFlights();

			ArrayList<Flight> pastFlights = new ArrayList<Flight>();

			if (flights.isEmpty()){
				JOptionPane.showMessageDialog(this, "Your flight history is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}

			for (int i = 0; i<flights.size(); i++){
				if (flights.get(i).getDepartureTime().isBefore(now)){
					pastFlights.add(flights.get(i));
				}
			}
			
			String[] columns = {"Flight ID", "Flight Number", "Departure City", "Destination City", "Departure Time", "Arrival Time", "Price"};
			Object[][] table = new Object[pastFlights.size()][columns.length];

			for (int i = 0; i < pastFlights.size(); i++){

				table[i][0] = pastFlights.get(i).getFlightID();
				table[i][1] = pastFlights.get(i).getFlightNumber();
				table[i][2] = pastFlights.get(i).getDepartureCity();
				table[i][3] = pastFlights.get(i).getDestinationCity();
				table[i][4] = pastFlights.get(i).getDepartureTime();
				table[i][5] = pastFlights.get(i).getArrivalTime();
				table[i][6] = pastFlights.get(i).getPrice();
			}

			historyTable.setModel(new DefaultTableModel(table, columns));
	        historyTableScrollPane.setVisible(true);
	        revalidate();
	        repaint();


		}
    	private void cancelFlight(){

			User user = controller.getCurrentUser();
			LocalDateTime now = LocalDateTime.now();
			ArrayList<Flight> currFlights = new ArrayList<Flight>();
			ArrayList<Flight> flights = user.getFlights();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); //pattern for the string parsing

			for (int i = 0; i<flights.size(); i++){
				if (flights.get(i).getDepartureTime().isAfter(now)){
					currFlights.add(flights.get(i));
				}
			}

			if (currFlights.isEmpty()){
				JOptionPane.showMessageDialog(this, "You do not have any flights to cancel.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}

			int flightID = Integer.valueOf(JOptionPane.showInputDialog("Flight ID you wish to remove: "));

			boolean exists = false;

			for (int j = 0; j < flights.size(); j++){
				Flight flight = flights.get(j);
				if (flight.getFlightID() == flightID){

					exists = true;
					ArrayList<Booking> bookings = user.getBookings();
					for (Booking booking : bookings){

						if(booking.getFlightID() == flightID){
							String booked = booking.getBookingDate();
							LocalDateTime date = LocalDateTime.parse(booked, formatter); //parsing the String date in the booking.
							LocalDateTime expired = date.plusWeeks(2); //Can only get a refund within two weeks of booking.

							if (now.isBefore(expired)){
								double price = flight.getPrice();
								double total = user.getAccountBalance() + price;
								user.setAccountBalance(total);
							}
						}
					}
					flights.remove(flight);					
					break;

				}
			}

			if (!exists){
				JOptionPane.showMessageDialog(this, "The flight you are trying to cancel does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this, "The flight has been cancelled", "Info", JOptionPane.INFORMATION_MESSAGE);
    	}

		private void addToCart() {
			String flightIDInput = JOptionPane.showInputDialog(this, "Enter the Flight ID to add to your cart:");
			if (flightIDInput == null || flightIDInput.isEmpty()) return;
		
			try {
				int flightID = Integer.parseInt(flightIDInput);
				Flight flight = controller.getDatabase().getFlight(flightID); // Fetch flight from DB
		
				if (flight != null) {
					controller.getCurrentUser().addToCart(flight); // Add to user's cart
					JOptionPane.showMessageDialog(this, "Flight added to cart successfully!");
				} else {
					JOptionPane.showMessageDialog(this, "Flight not found.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Invalid Flight ID format.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void viewCart() {
			ArrayList<Flight> cart = controller.getCurrentUser().getCart();
		
			if (cart.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Your cart is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		
			String[] columnNames = {"Flight ID", "Flight Number", "Departure City", "Destination City", 
									 "Departure Time", "Arrival Time", "Price", "Seats Available"};
			Object[][] tableData = new Object[cart.size()][columnNames.length];
		
			for (int i = 0; i < cart.size(); i++) {
				Flight flight = cart.get(i);
				tableData[i][0] = flight.getFlightID();
				tableData[i][1] = flight.getFlightNumber();
				tableData[i][2] = flight.getDepartureCity();
				tableData[i][3] = flight.getDestinationCity();
				tableData[i][4] = flight.getDepartureTime();
				tableData[i][5] = flight.getArrivalTime();
				tableData[i][6] = flight.getPrice();
				tableData[i][7] = flight.getSeatsAvailable();
			}
		
			cartTable.setModel(new DefaultTableModel(tableData, columnNames));
			cartScrollPane.setVisible(true);
			revalidate();
			repaint();
		}
		
		private void clearCart() {
			controller.getCurrentUser().clearCart();
			JOptionPane.showMessageDialog(this, "Cart cleared successfully.");
			cartScrollPane.setVisible(false);
		}

		private void processPayment() {
			User currentUser = controller.getCurrentUser();
			ArrayList<Flight> cart = currentUser.getCart();
		
			if (cart.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Your cart is empty. Add flights to your cart before paying.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		
			// Mock payment process
			String paymentDetails = JOptionPane.showInputDialog(this, "Enter Payment Details:");
			if (paymentDetails == null || paymentDetails.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Payment details are required.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		
			// Iterate over the cart and create bookings
			boolean allBookingsSuccessful = true;
			for (Flight flight : cart) {
				int userId = currentUser.getUserID();
				int flightId = flight.getFlightID();
				double price = flight.getPrice();
				String seatNumber = JOptionPane.showInputDialog(this, "Enter Seat Number for flight " + flight.getFlightNumber() + ":");
		
				if (seatNumber == null || seatNumber.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Seat number is required for flight " + flight.getFlightNumber() + ".", "Error", JOptionPane.ERROR_MESSAGE);
					allBookingsSuccessful = false;
					continue;
				}
		
				boolean travelInsurance = JOptionPane.showConfirmDialog(this, "Add travel insurance for flight " + flight.getFlightNumber() + "?", "Travel Insurance", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		
				// Create booking in the database
				boolean success = controller.getDatabase().createBooking(userId, flightId, price, seatNumber, travelInsurance);
				if (!success) {
					allBookingsSuccessful = false;
				}
			}
		
			if (allBookingsSuccessful) {
				JOptionPane.showMessageDialog(this, "Payment successful, and all flights booked!", "Success", JOptionPane.INFORMATION_MESSAGE);
				currentUser.clearCart(); // Clear the cart after successful payment
			} else {
				JOptionPane.showMessageDialog(this, "Some bookings failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		private void addTravelInsurance() {
			// Prompt the user to enter the Booking ID
			String bookingIDInput = JOptionPane.showInputDialog(this, "Enter the Booking ID to add travel insurance:");
			if (bookingIDInput == null || bookingIDInput.isEmpty()) return;
		
			try {
				int bookingID = Integer.parseInt(bookingIDInput);
		
				// Retrieve the booking from the database
				Booking booking = controller.getDatabase().getBooking(bookingID);
				if (booking == null) {
					JOptionPane.showMessageDialog(this, "Booking not found.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
		
				// Check if travel insurance has already been added
				if (booking.isTravelInsurance()) { // Ensure travel_insurance field is accessible
					JOptionPane.showMessageDialog(this, "Travel insurance has already been added for this booking.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
		
				// Update the booking to add travel insurance
				boolean success = controller.getDatabase().updateBooking(
					bookingID, 
					0, 
					null, 
					true // Set travelInsurance to true
				);
		
				if (success) {
					JOptionPane.showMessageDialog(this, "Travel insurance added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Failed to add travel insurance. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
		
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Invalid Booking ID format.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}

	}
