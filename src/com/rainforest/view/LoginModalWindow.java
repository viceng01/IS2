package com.rainforest.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LoginModalWindow extends JDialog {

	public LoginModalWindow() {
		super(null, "Login", ModalityType.DOCUMENT_MODAL);
		
		initGUI();
	}

	private void initGUI() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel formPanel = createFormPanel();
		add(formPanel);

		JPanel buttonPanel = createButtonPanel();
		add(buttonPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton loginButton = new JButton("Login");
		JButton registerButton = new JButton("Register");

		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);

		return buttonPanel;
	}

	private JPanel createFormPanel() {
		JPanel formPanel = new JPanel();
		
		formPanel.setLayout(new GridBagLayout());
		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Details", TitledBorder.LEFT, TitledBorder.TOP));

		GroupLayout formLayout = new GroupLayout(formPanel);
		
		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Details", TitledBorder.LEFT, TitledBorder.TOP));
		formPanel.setLayout(formLayout);
		
		formLayout.setAutoCreateGaps(true);
		formLayout.setAutoCreateContainerGaps(true);
		
		SequentialGroup hGroup = formLayout.createSequentialGroup();
		SequentialGroup vGroup = formLayout.createSequentialGroup();

		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");

		JTextField emailTextField = new JTextField(15);
		JTextField passwordTextField = new JTextField(15);

		hGroup.addGroup(formLayout.createParallelGroup().addComponent(emailLabel).addComponent(passwordLabel));
		hGroup.addGroup(formLayout.createParallelGroup().addComponent(emailTextField).addComponent(passwordTextField));

		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(emailLabel).addComponent(emailTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(passwordLabel).addComponent(passwordTextField));

		formLayout.setHorizontalGroup(hGroup);
		formLayout.setVerticalGroup(vGroup);

		return formPanel;
	}
}
