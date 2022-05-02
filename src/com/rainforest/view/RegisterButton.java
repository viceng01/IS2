package com.rainforest.view;

import javax.swing.JButton;

public class RegisterButton extends JButton {

	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	private ControlPanel controlPanel;

	public RegisterButton(MainWindow mainWindow, ControlPanel controlPanel) {
		super("Register");

		this.mainWindow = mainWindow;
		this.controlPanel = controlPanel;

		addActionListener((ae) -> {
			register();
		});
	}

	private void register() {
		String email = controlPanel.getEmail();

		if (mainWindow.doesUserExist(email)) {
			mainWindow.showErrorDialog("User already exists with same email");
			return;
		}

		String type = controlPanel.getUserType();

		if (type.equals(ControlPanel.USER_TYPE_BUYER))
			controlPanel.openBuyerRegistrationForm();
		else if (type.equals(ControlPanel.USER_TYPE_SELLER))
			controlPanel.openSellerRegistrationForm();

		controlPanel.close();
	}
}
