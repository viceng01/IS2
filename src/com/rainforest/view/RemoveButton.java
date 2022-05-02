package com.rainforest.view;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.rainforest.core.GUID;

public class RemoveButton extends JButton {

	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	private ControlPanel controlPanel;

	public RemoveButton(MainWindow mainWindow, ControlPanel controlPanel) {
		super("Remove");

		this.mainWindow = mainWindow;
		this.controlPanel = controlPanel;

		addActionListener((ae) -> {
			remove();
		});
	}

	private void remove() {
		String email = controlPanel.getEmail();
		String password = controlPanel.getPassword();

		GUID userID = mainWindow.getUserGUIDWithAuthentication(email, password);

		if (userID == null)
			mainWindow.showErrorDialog("User not found with email " + email);
		else {
			int option = mainWindow.showConfirmDialog("Are you sure you wish to remove the user with email " + email + "?");

			if (option == JOptionPane.YES_OPTION) {
				mainWindow.removeUser(userID);

				controlPanel.close();
			}
		}
	}
}
