/**
 * 
 */
package main.Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

		JPanel buttons = new JPanel();
		JPanel title = new JPanel();
		
		JLabel titleMsg;
		
		JPanel main;
		CardLayout card;
		private final JButton btnNewButton = new JButton("Transactions"); //Profile

	    private JTable flightTable;
	    private DB database; // Add a reference to the DB instance
	    private JButton browseFlightsButton = new JButton("Browse Flights");
	    private JScrollPane tableScrollPane;
		
		
		public CustomerDashBoardPage(Controller controller) {
			setBackground(new Color(255, 255, 255));
			this.controller = controller;
			initialize();
		}
		
		private void initialize() {
			
			titleMsg = new JLabel("Welcome " + controller.getCurrentUser().getUsername() + "!");
	        titleMsg.setFont(new Font("Arial", Font.BOLD, 18));

	        title.setLayout(new BorderLayout()); // Set BorderLayout for title panel
	        title.add(titleMsg, BorderLayout.NORTH); // Add title message to the center of the title panel

	        // Set up the logout button and its position in the right corner
	        logout.setBackground(new Color(0, 0, 0));
	        logout.setForeground(new Color(255, 255, 255));
	        logout.addActionListener(e -> controller.logout());

	        // Set up the Transactions button and its position in the left corner
	        btnNewButton.setForeground(new Color(255, 255, 255));
	        btnNewButton.setBackground(new Color(0, 0, 0));
	        btnNewButton.addActionListener(e -> {
	            // controller.TransactionsPage(controller.getCurrentUser()); --> NEED TO CREATE!!
	        });

	        // Create a small panel to hold the two buttons
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
	        buttonPanel.add(btnNewButton);
	        buttonPanel.add(logout);

	        // Add title and button panel to the main layout
	        this.setLayout(new BorderLayout());
	        this.add(title, BorderLayout.NORTH); // Add title to the top
	        this.add(buttonPanel, BorderLayout.EAST); // Add button panel to the right

	        // Set up the main layout with BoxLayout
	        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
	        this.setLayout(boxlayout);
	        this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
	 
	         // Browse Flights button setup
	         browseFlightsButton.setForeground(Color.WHITE);
	         browseFlightsButton.setBackground(Color.BLACK);
	         browseFlightsButton.addActionListener(e -> toggleBrowseFlights());
	         this.add(browseFlightsButton, BorderLayout.SOUTH);
	 
	         // Set up table for displaying flight data
	         flightTable = new JTable();
	         tableScrollPane = new JScrollPane(flightTable);
	         tableScrollPane.setVisible(false); // Initially hidden
	         this.add(tableScrollPane, BorderLayout.CENTER);
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
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}

	}
