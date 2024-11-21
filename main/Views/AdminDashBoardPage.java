/**
 * 
 */
package main.Views;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Controller.Controller;
import main.Models.User;
/**
 * @author henap
 *
 */
public class AdminDashBoardPage extends JPanel implements ActionListener {

	private Controller controller;
	
	String username;
	JButton logout = new JButton("Logout");
    JButton addFlight = new JButton("Add Flight");
    JButton removeFlight = new JButton("Remove Flight");
    JButton updateFlight = new JButton("Update Flight");


	JPanel buttons = new JPanel();
	JPanel title = new JPanel();

	
	JLabel titleMsg;
	
	JPanel main;
	CardLayout card;
	private final JButton btnNewButton = new JButton("Profile");
	
	
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
        // Set up the addFlight button
        addFlight.setForeground(new Color(255, 255, 255));
        addFlight.setBackground(new Color(0, 0, 0));
        addFlight.addActionListener(e -> {
            //controller.addFlight
        });
        //Set up the removeFlight button
        removeFlight.setForeground(new Color(255, 255, 255));
        removeFlight.setBackground(new Color(0, 0, 0));
        removeFlight.addActionListener(e -> {
            //controller.removeFlight
        });
        // Set up the updateFlight button
        updateFlight.setForeground(new Color(255, 255, 255));
        updateFlight.setBackground(new Color(0, 0, 0));
        updateFlight.addActionListener(e->{
            //controller.updateFlight
        });



        // Create a small panel to hold the two buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        buttonPanel.add(btnNewButton);
        buttonPanel.add(logout);

        //Created the flight button panel to hold addFlight, removeFlight and updateFlight
        JPanel flightButtonPanel = new JPanel();
        flightButtonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        flightButtonPanel.setBackground(Color.WHITE);
        flightButtonPanel.add(addFlight);
        flightButtonPanel.add(updateFlight);
        flightButtonPanel.add(removeFlight);




        // Add title and button panel to the main layout
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH); // Add title to the top
        this.add(buttonPanel, BorderLayout.EAST); // Add button panel to the right

        this.add(flightButtonPanel, BorderLayout.CENTER);


        // Set up the main layout with BoxLayout
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		
		
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
       // else if (e.getSource() == updateFlight){
       //     updateFlight();
       // }
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
        //If user submits the inputs, set the fields to the corresponding inputs
        if (prompt == JOptionPane.OK_OPTION){
            try {
                String flightNumber = flightNum.getText(); 
                String depString = dep.getText();
                String arrString = arr.getText();
                String depTimeString = depTime.getText();
                String arrTimeString = arrTime.getText();
                int flightID = Integer.valueOf(ID.getText());
                double cost = Double.valueOf(price.getText());
                int numSeats = Integer.valueOf(seats.getText());
                //Attempt to create the flight using the createFlight() in DB.java
                boolean result = controller.getDatabase().createFlight(flightID, flightNumber, depString, arrString, depTimeString, arrTimeString, cost, numSeats);

                if (result){
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
            JOptionPane.showMessageDialog(this, "Flight removed successfully");
        }
        else{
            JOptionPane.showMessageDialog(this, "Failed to remove flight.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }


}
