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
import main.Models.*;

/**
 * @author henap
 *
 */
public class TransactionPage extends JPanel implements ActionListener {

	private Controller controller;
	
	
	public TransactionPage(Controller controller) {
		setBackground(new Color(255, 255, 255));
		this.controller = controller;
		initialize();
	}
	
	private void initialize() {
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
