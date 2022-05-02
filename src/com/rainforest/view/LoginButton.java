package com.rainforest.view;

import javax.swing.JButton;

import com.rainforest.model.LoginResponse;

public class LoginButton extends JButton {

	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	private ControlPanel controlPanel;

	public LoginButton(MainWindow mainWindow, ControlPanel controlPanel) {
		super("Login");

		this.mainWindow = mainWindow;
		this.controlPanel = controlPanel;

		addActionListener((ae) -> {
			login();
		});
	}

	private void login() {
		String email = controlPanel.getEmail();
		String password = controlPanel.getPassword();
		String userType = controlPanel.getUserType();

		LoginResponse response = null;

		if (userType.equals("Buyer"))
			response = mainWindow.tryLoginAsBuyer(email, password);
		else if (userType.equals("Seller"))
			response = mainWindow.tryLoginAsSeller(email, password);

		if (response == LoginResponse.OK) {
			updateLoginUI(userType);
		} else {
			showLoginError(response);
		}
	}

	private void updateLoginUI(String userType) {
		mainWindow.showMessageDialog("Login successful");

		if (userType.equals("Buyer"))
			controlPanel.openBuyerPanel();
		else if (userType.equals("Seller"))
			controlPanel.openSellerPanel();

		controlPanel.close();
	}

	private void showLoginError(LoginResponse response) {
		String message;

		switch (response) {
		case UNKNOWN_USER:
			message = "Unknown email";
			break;
		case INCORRECT_PASSWORD:
			message = "Incorrect email or password";
			break;
		default:
			message = "Unexpected error";
		}

		mainWindow.showErrorDialog(message);
	}

}
