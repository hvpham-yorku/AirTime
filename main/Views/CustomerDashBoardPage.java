/**
 * 
 */
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
import java.util.ArrayList;

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
		private JButton btnTransactions = new JButton("Transactions"); // Profile
	    private JButton browseFlightsButton = new JButton("Browse Flights");
	    private JButton searchDepartureCityButton = new JButton("Search by Departure City");
	    private JButton searchDestinationCityButton = new JButton("Search by Destination City");


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

	        logout.setBackground(new Color(0, 0, 0));
	        logout.setForeground(new Color(255, 255, 255));
	        logout.addActionListener(e -> controller.logout());

	        btnTransactions.setForeground(new Color(255, 255, 255));
	        btnTransactions.setBackground(new Color(0, 0, 0));
	        btnTransactions.addActionListener(e -> {
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

	        // Optionally, add some spacing between buttons for better layout
	        buttonPanel.add(Box.createVerticalStrut(10)); // Adds 10px spacing between components

	        setLayout(new BorderLayout());
	        add(title, BorderLayout.NORTH);
	        add(buttonPanel, BorderLayout.WEST); // Adjust panel placement as needed

	        add(logout, BorderLayout.SOUTH);
	        buttonPanel.add(btnTransactions);
	        
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

	    
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}

	}
