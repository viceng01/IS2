package com.rainforest.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rainforest.controller.Controller;	

public class MainWindow extends JFrame {

	private Controller controller;
	
	public MainWindow()
	{
		controller = new Controller();
		initGUI();
	}

	private void initGUI() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		
		JMenuBar mainMenu = new MainMenu();

		setJMenuBar(mainMenu);
		
		postSetup();
	}

	private void postSetup() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.err.println("Could not set system look and feel");
		}
	}
}
