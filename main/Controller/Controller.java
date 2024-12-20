package main.Controller;

import java.awt.CardLayout;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import main.DB.*;
import main.Models.*;
import main.Views.*;

/**
 * @author henap
 * Controller without MySQL connection
 */
public class Controller {

	private JPanel main;
	private CardLayout card;
	private DB mysql_database;
	private User currentUser;

	
          
	public Controller(JPanel main, CardLayout card) {
		this.main = main;
		this.card = card;

		
		try {
			this.mysql_database = new DB();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public DB getDatabase() {
		return this.mysql_database;
	}
	
	protected CardLayout getCard() {
		return this.card;
	}
	
	protected JPanel getMain() {
		return this.main;
	}
	
	public boolean createUser(int userID, String username, String password, String role) {

		if (password.length() < 8) {
			throw new IllegalArgumentException("Password must be at least 8 characters");
		} else if (username.length() < 5) {
			throw new IllegalArgumentException("Username must be at least 5 characters");
		} else {
			try {
				currentUser = this.mysql_database.createUser(userID, username, password, role);
				if (currentUser != null) {
					// Redirect based on user role
	                if ("admin".equals(currentUser.getRole())) {
	                    AdminDashBoardPage adminLandingPage = new AdminDashBoardPage(this);
	                    main.add(adminLandingPage, "adminLandingPage");
	                    card.show(main, "adminLandingPage");
	                } else if ("customer".equals(currentUser.getRole())) {
	                    CustomerDashBoardPage customerLandingPage = new CustomerDashBoardPage(this);
	                    main.add(customerLandingPage, "customerLandingPage");
	                    card.show(main, "customerLandingPage");
	                }
	                return true;
				} else {
					return false;
				}
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}

	}
	
	public boolean login(String username, String password) {
		try {
			currentUser = this.mysql_database.verifyUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (currentUser == null) {
			return false;
		} else {
			// Redirect based on user role
	        if ("admin".equals(currentUser.getRole())) {
	            AdminDashBoardPage adminLandingPage = new AdminDashBoardPage(this);
	            main.add(adminLandingPage, "adminLandingPage");
	            card.show(main, "adminLandingPage");
	        } else if ("customer".equals(currentUser.getRole())) {
	            CustomerDashBoardPage customerLandingPage = new CustomerDashBoardPage(this);
	            main.add(customerLandingPage, "customerLandingPage");
	            card.show(main, "customerLandingPage");
	        }
	        return true;
		}
	}

	public boolean addAdmin(int adminID, String username, String password) {
		try {
			return this.mysql_database.createUser(adminID, username, password, "admin") != null;
		} catch (Exception e) {
			System.out.println("Error adding admin: " + e.getMessage());
			return false;
		}
	}
	
	public boolean removeAdmin(int adminID) {
		try {
			User user = this.mysql_database.getUser(adminID);
			if (user == null) {
				System.out.println("Error: Admin with ID " + adminID + " does not exist.");
				return false;
			}
			if (!"admin".equalsIgnoreCase(user.getRole())) {
				System.out.println("Error: User with ID " + adminID + " is not an admin.");
				return false;
			}
			return this.mysql_database.deleteUser(adminID);
		} catch (Exception e) {
			System.out.println("Error removing admin: " + e.getMessage());
			return false;
		}
	}
	
	
	public User getCurrentUser() {
		return this.currentUser;
	}

	public void logout() {
		currentUser = null;
		this.welcomePage();
	}

	public void previous() {
		card.previous(main);
	}

	public void welcomePage() {
		System.out.println("WELCOME!");
		card.show(main, "welcomePage");
	}

	public void loginPage() {
		System.out.println("LOGIN!");
		card.show(main, "loginPage");
	}

	public void registerPage() {
		System.out.println("REGISTER!");
		card.show(main, "registerPage");
	}
	
}
