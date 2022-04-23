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
	private static String _inFile = "BaseDeDatos.json";
	private static Factory<User> _eventsFactory = null;

	public MainWindow() {
		super("Login");
		//A lo mejor es buan idea inicializar las factorias
		//y el controller fuera del main window 0.0
		initFactories();
		controller = new Controller(_eventsFactory);
		InputStream in = null;
		try {
			in = new FileInputStream(_inFile);
			controller.loadDataBase(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Abrir y leer de bloc de notas los usuarios que ya estan logueados
		initGUI();
	}
	
	private static void initFactories() {
		List<Builder<User>> lista = new ArrayList<>();
		lista.add(new BuyerBuilder());
		lista.add(new SellerBuilder());

		Factory<User> eventsFactory = new BuilderBasedFactory<>(lista);
		_eventsFactory = eventsFactory;
		
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
		//add(new JScrollPane(new ProductsList()), BorderLayout.CENTER);
		
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
		
		//Utilizamos un GridLayout para que se separe el JPanel central en 2 
		//Teniendo a la izquierda usuario y contraseña y a la derecha los botones de login y de registro
		JPanel viewsPanel = new JPanel(new GridLayout(1, 1));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel);
		
		JPanel loginDialog = new LoginModalWindow(this);
		loginDialog.setVisible(true);
		
		mainPanel.add(loginDialog);
		
		
	}

	private void initGUI() {
		doPreSetup();
		doSetup();
		doPostSetup();
	}

}
