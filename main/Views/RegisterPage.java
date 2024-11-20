package main.Views;

import main.Controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author henap
 *
 */
public class RegisterPage extends JPanel implements ItemListener, ActionListener {
	
	JPanel main;
	CardLayout card;
	private Controller controller;
	
	JPanel title = new JPanel();
	JLabel RegisterMsg = new JLabel("Register");
	
	JPanel buttons = new JPanel();
	JButton back = new JButton("Back");
	JButton submit = new JButton("Submit");
	
	JPanel idPanel = new JPanel();
	JLabel idLabel = new JLabel("UserID");
	JTextField id = new JTextField();
	
	JPanel namePanel = new JPanel();
	JLabel nameLabel = new JLabel("Username");
	JTextField name = new JTextField();
	
	JPanel rolePanel = new JPanel();
	JLabel roleLabel = new JLabel("Role");
	JComboBox<String> role = new JComboBox<>(new String[] { "customer", "admin" });
	//JTextField role = new JTextField();
	
	JPanel passwordPanel = new JPanel();
	JLabel passwordLabel = new JLabel("Password");
	
	WelcomePage welcomePage;
	private final JPasswordField password = new JPasswordField();
	
	public RegisterPage(Controller controller) {
		setBackground(new Color(255, 255, 255)); //White
		
		this.controller = controller;
//		this.welcomePage = welcomePage;
//		this.main = main;
//		this.card = card;
		initialize();
		
	}
	
	public void initialize() {
		title.setBackground(new Color(255, 255, 255));
	
		title.add(RegisterMsg);
		this.add(title);
		namePanel.setBackground(new Color(255, 255, 255));
		namePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		// ID Panel setup
        idPanel.setBackground(Color.WHITE);
        idPanel.setLayout(new GridLayout(0, 2, 0, 0));
        idPanel.add(idLabel);
        id.setPreferredSize(new Dimension(100, 20));
        idPanel.add(id);
        this.add(idPanel);  // Add idPanel before namePanel

        // Name Panel setup
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new GridLayout(0, 2, 0, 0));
        namePanel.add(nameLabel);
        name.setPreferredSize(new Dimension(100, 20));
        namePanel.add(name);
        this.add(namePanel);

        // Role Panel setup
        rolePanel.setBackground(Color.WHITE);
        rolePanel.setLayout(new GridLayout(0, 2, 0, 0));
        rolePanel.add(roleLabel);
        role.setPreferredSize(new Dimension(100, 20));
        rolePanel.add(role);
        this.add(rolePanel);

        // Password Panel setup
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setLayout(new GridLayout(0, 2, 0, 0));
        passwordPanel.add(passwordLabel);
        password.setPreferredSize(new Dimension(100, 20));
        passwordPanel.add(password);
        this.add(passwordPanel);

        // Buttons Panel setup
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        
        buttons.setBackground(Color.WHITE);
        buttons.setLayout(new GridLayout(0, 2, 0, 0));
        buttons.add(back);
        buttons.add(submit);
        this.add(buttons);

        // Set layout and alignment
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        this.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
    }
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
		        controller.welcomePage();
		    } else if (e.getSource() == submit) {
		        String username = name.getText();
		        String userPassword = String.valueOf(password.getPassword());

		        // Check if password is at least 8 characters
		        if (userPassword.length() < 8) {
		            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.", 
		                                          "Input Error", JOptionPane.ERROR_MESSAGE);
		            return; // Stop execution if password is invalid
		        }

		        // Check if username is at least 5 characters
		        if (username.length() < 5) {
		            JOptionPane.showMessageDialog(this, "Username must be at least 5 characters long.", 
		                                          "Input Error", JOptionPane.ERROR_MESSAGE);
		            return; // Stop execution if username is invalid
		        }

		        // Process registration if inputs are valid
		        try {
		            int userId = Integer.parseInt(id.getText());  // Convert id to int
		            String userRole = role.getSelectedItem().toString();  // Get the selected role

		            // Call createUser method in controller with the correct data types
		            controller.createUser(userId, username, userPassword, userRole);

		        } catch (NumberFormatException ex) {
		            System.out.println("Invalid UserID: Please enter a numeric value.");
		            JOptionPane.showMessageDialog(this, "Invalid UserID: Please enter a numeric value.", 
		                                          "Input Error", JOptionPane.ERROR_MESSAGE);
		        }

			//controller.createUser(id.getText(), name.getText(), String.valueOf(password.getPassword()), role.getText());

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
