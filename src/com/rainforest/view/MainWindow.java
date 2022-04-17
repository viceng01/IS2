package com.rainforest.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rainforest.controller.Controller;

public class MainWindow extends JFrame {

	private Controller controller;

	public MainWindow() {
		controller = new Controller();
		initGUI();
	}

	public void close() {
		dispose();
		System.exit(0);
	}
	
	
	public LoginResponse authenticate(String email, String password,String type) {
		return controller.tryLogin(email, password,type);
	}
	
	public boolean doesUserExist(String email) {
		return controller.doesUserExist(email);
	}
	
	public void registerBuyer(String email, String password, String username) {
		controller.registerBuyer(email, password, username);
	}
	
	public void registerSeller(String email, String password, String username) {
		controller.registerSeller(email, password, username);
	}

	private void doPreSetup() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.err.println("Could not set system look and feel");
		}
	}

	private void doPostSetup() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void doSetup() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);

		JMenuBar mainMenu = new MainMenu(this);
		setJMenuBar(mainMenu);
		
		JDialog loginDialog = new LoginModalWindow(this);
		loginDialog.setVisible(true);
	}

	private void initGUI() {
		doPreSetup();
		doSetup();
		doPostSetup();
	}

}
