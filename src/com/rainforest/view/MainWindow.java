package com.rainforest.view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONArray;

import com.rainforest.controller.Controller;
import com.rainforest.core.GUID;
import com.rainforest.model.LoginResponse;
import com.rainforest.model.ModifyResponse;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 001L;

	private Controller controller;
	private OutputStream os;

	public MainWindow(Controller ctrl, OutputStream os) {
		super("Rainforest");

		this.controller = ctrl;
		this.os = os;

		// Abrir y leer de bloc de notas los usuarios que ya estan logueados
		initGUI();
	}

	public void close() {
		dispose();
		System.exit(0);
	}

	// Patron
	public LoginResponse tryLoginAsBuyer(String email, String password) {
		return controller.tryLoginAsBuyer(email, password);
	}

	public LoginResponse tryLoginAsSeller(String email, String password) {
		return controller.tryLoginAsSeller(email, password);
	}

	public boolean doesUserExist(String email) {
		return controller.doesUserExist(email);
	}

	public void removeUser(GUID guid) {
		controller.removeUser(guid);
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

//		// Alto y ancho de la pantalla del ordenador, para que en caso de que
//		// se utilicen distintos tamaños de pantalla de ordenador, se adapte
//		// el jframe a este
//		int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
//		int alto = Toolkit.getDefaultToolkit().getScreenSize().height;

		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

		//

		JMenuBar mainMenu = new MainMenu(this);

		mainPanel.add(mainMenu, BorderLayout.PAGE_START);
		setJMenuBar(mainMenu);

		//

		JPanel cp = new ControlPanel(this);
		mainPanel.add(cp, BorderLayout.CENTER);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				serialize();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

		});

	}

	private void initGUI() {
		doPreSetup();
		doSetup();
		doPostSetup();
	}

	private void serialize() {
		controller.serializeModel(os);
	}

	public ModifyResponse tryModifyBuyer(String email, String username, String password, String address, int telephone,
			String dni) {
		return controller.tryModifyUser(email, username, password, address, telephone, dni);
	}

	public GUID getUserGUIDWithAuthentication(String email, String password) {
		return controller.getUserGUIDWithAuthentication(email, password);
	}

	public JSONArray getProducts(GUID guid) {
		return controller.getProducts(guid);
	}

	public void removeProduct(GUID name, String dni) {
//		controller.removePrdouct(name, dni);

	}

	public void showErrorDialog(String message) {
		JOptionPane.showConfirmDialog(null, message, "Login error", JOptionPane.DEFAULT_OPTION,
				JOptionPane.ERROR_MESSAGE);
	}

	public int showConfirmDialog(String message) {
		return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showMessageDialog(String message) {
		JOptionPane.showConfirmDialog(null, message, "Seller registration", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

}
