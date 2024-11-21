/**
 * 
 */
package main.Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
	
    private JButton updateFlightButton = new JButton("Update Flight");
	
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

        // Add title and button panel to the main layout
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH); // Add title to the top
        this.add(buttonPanel, BorderLayout.EAST); // Add button panel to the right

        // Set up the main layout with BoxLayout
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
         // Title setup
         JLabel titleMsg = new JLabel("Welcome " + controller.getCurrentUser().getUsername() + "!");
         titleMsg.setFont(new Font("Arial", Font.BOLD, 18));
         this.add(titleMsg, BorderLayout.NORTH);
 
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

         updateFlightButton.setForeground(Color.WHITE);
         updateFlightButton.setBackground(Color.BLACK);
         updateFlightButton.addActionListener(e -> openUpdateFlightForm());
         this.add(updateFlightButton, BorderLayout.SOUTH);
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
        saveButton.addActionListener(e -> {
            try {
                // Get updated flight details
                String updatedFlightNumber = flightNumberField.getText();
                String updatedDepartureCity = departureCityField.getText();
                String updatedDestinationCity = destinationCityField.getText();
                String updatedDepartureTime = departureTimeField.getText();
                String updatedArrivalTime = arrivalTimeField.getText();
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
		
		
	}

}
