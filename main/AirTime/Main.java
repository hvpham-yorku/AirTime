package main.AirTime;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Controller.Controller;
import main.Views.LoginPage;
import main.Views.RegisterPage;
import main.Views.WelcomePage;

/**
 * @author henap
 *
 */

public class Main {

	private JPanel container;
	private CardLayout card;
	private JFrame main;
	private Controller controller;
	private WelcomePage welcomePage;

	public Main() {
		main = new JFrame("AirTime");

		container = new JPanel();
		main.add(container);
		card = new CardLayout();
		container.setLayout(card);
		// container.setVisible(true);

		controller = new Controller(container, card);
		welcomePage = new WelcomePage(controller);
		container.add(welcomePage, "welcomePage");

		LoginPage loginPage = new LoginPage(controller);
		container.add(loginPage, "loginPage");
		RegisterPage registerPage = new RegisterPage(controller);
		container.add(registerPage, "registerPage");

		card.show(container, "1");
		welcomePage.setVisible(true);

		main.setSize(1200, 650);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);

	}

	public static void main(String[] args) {
		Main main = new Main();

	}

	public WelcomePage getWelcomePage() {
		return this.welcomePage;
	}

	public JFrame getFrame() {
		return this.main;
	}

	public CardLayout getCard() {
		return this.card;
	}

	public JPanel getContainer() {
		return this.container;
	}

	public Controller getController() {
		return this.controller;
	}
}