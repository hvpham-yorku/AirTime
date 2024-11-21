package main.Controller;

import main.DB.*;
import main.Models.*;
import main.Views.*;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

import java.awt.CardLayout;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

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
					AdminDashBoardPage adminLandingPage = new AdminDashBoardPage(this);
					main.add(adminLandingPage, "adminLandingPage");
					card.show(main, "adminLandingPage");
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
			// TODO Auto-generated catch block
			return false;
		}
		if (currentUser == null) {
			return false;
		} else {
			AdminDashBoardPage adminLandingPage = new AdminDashBoardPage(this);
			main.add(adminLandingPage, "adminLandingPage");
			card.show(main, "adminLandingPage");
			return true;
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
