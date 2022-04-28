package com.rainforest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import com.rainforest.controller.Controller;
import com.rainforest.factories.Builder;
import com.rainforest.factories.BuilderBasedFactory;
import com.rainforest.factories.BuyerBuilder;
import com.rainforest.factories.Factory;
import com.rainforest.factories.SellerBuilder;
import com.rainforest.model.user.User;


public class MainWindow extends JFrame {

	private Controller controller;

	public MainWindow(Controller ctrl) {
		super("Rainforest");
		
		this.controller = ctrl;
		
		//Abrir y leer de bloc de notas los usuarios que ya estan logueados
		initGUI();
	}
	
	

	public void close() {
		dispose();
		System.exit(0);
	}
	
	//Patron 
	public LoginResponse authenticate(String email, String password,String type) {
		return controller.tryLogin(email, password,type);
	}
	
	public LoginResponse doesUserExist(String email, String password,String user) {
		return controller.doesUserExist(email,password,user);
	}
	
	public boolean doesRegisterBuyerExist(String dni,int tel) {
		return controller.doesRegisterBuyerExist(dni,tel);
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
		
		//Alto y ancho de la pantalla del ordenador, para que en caso de que 
		//se utilicen distintos tamaños de pantalla de ordenador, se adapte
		//el jframe a este
		int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto = Toolkit.getDefaultToolkit().getScreenSize().height;

		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		//
		
		JMenuBar mainMenu = new MainMenu(this);
		
		
		mainPanel.add(mainMenu, BorderLayout.PAGE_START);
		setJMenuBar(mainMenu);
		
		//
		
		JPanel cp = new ControlPanel(this,controller);
		mainPanel.add(cp, BorderLayout.CENTER);
		
		
		this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                saveChanges();
            }

			@Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}

        });
		
	}

	private void initGUI() {
		doPreSetup();
		doSetup();
		doPostSetup();
	}
	
	private void saveChanges() {
		controller.saveChanges();
	}

}
