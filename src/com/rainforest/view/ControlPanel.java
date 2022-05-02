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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final String USER_TYPE_BUYER = "Buyer";
	public static final String USER_TYPE_SELLER = "Seller";

	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JTextField usernameTextField;
	private JComboBox<String> userTypeComboBox;

	private ProductsListBuyer productListBuyer;
	private ProductsListSeller productListSeller;

	private BuyerRegisterModalWindow brmw;
	private SellerRegisterModalWindow srmw;

	private MainWindow mainWindow;

	private char puntitos;
	private JCheckBox mostrar;

	public ControlPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		initGUI(mainWindow);
		puntitos = passwordTextField.getEchoChar();

		/* TODO */
		// HABRIA QUE DEJA SOLO UN PRODUCT LIST Y QUE LLAME A LA INTERFAZ
		// CORRESPONDIENTE
		productListBuyer = new ProductsListBuyer(mainWindow, this);
		productListSeller = new ProductsListSeller(mainWindow, this);
		brmw = new BuyerRegisterModalWindow(mainWindow, this);
		srmw = new SellerRegisterModalWindow(mainWindow, this);
	}

	public String getUsername() {
		return usernameTextField.getText();
	}

	public String getEmail() {
		return emailTextField.getText();
	}

	public String getPassword() {
		return new String(passwordTextField.getPassword());
	}

	public String getUserType() {
		return userTypeComboBox.getSelectedItem().toString();
	}

	public void flushFields() {
		emailTextField.setText(null);
		passwordTextField.setText(null);
		usernameTextField.setText(null);
		userTypeComboBox.setSelectedIndex(0);
	}

	public void openBuyerPanel() {

	}

	public void openSellerPanel() {
	}

	public void openBuyerRegistrationForm() {
		brmw.setVisible(true);
	}

	public void openSellerRegistrationForm() {
		srmw.setVisible(true);
	}

	private void initGUI(MainWindow mainWindow) {

		JPanel formPanel = createFormPanel();
		add(formPanel);

		JPanel buttonPanel = createButtonPanel(mainWindow);
		add(buttonPanel);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	private JPanel createButtonPanel(MainWindow mainWindow) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton loginButton = new LoginButton(mainWindow, this);
		JButton registerButton = new RegisterButton(mainWindow, this);
		JButton removeButton = new RemoveButton(mainWindow, this);

		mostrar = new JCheckBox("Mostrar contraseña");

		mostrar.setBounds(10, 10, 150, 30);
		mostrar.addActionListener((ae) -> {
			passwordTextField.setEchoChar((mostrar.isSelected()) ? (char) 0 : puntitos);
		});

		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(mostrar);

		return buttonPanel;
	}

	private JPanel createFormPanel() {
		JPanel formPanel = new JPanel();

		formPanel.setLayout(new GridBagLayout());
		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Details",
				TitledBorder.LEFT, TitledBorder.TOP));

		GroupLayout formLayout = new GroupLayout(formPanel);

		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Details",
				TitledBorder.LEFT, TitledBorder.TOP));
		formPanel.setLayout(formLayout);

		formLayout.setAutoCreateGaps(true);
		formLayout.setAutoCreateContainerGaps(true);

		SequentialGroup hGroup = formLayout.createSequentialGroup();
		SequentialGroup vGroup = formLayout.createSequentialGroup();

		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");
		JLabel usernameLabel = new JLabel("Username");
		JLabel userTypeLabel = new JLabel("User type");

		emailTextField = new JTextField(15);
		passwordTextField = new JPasswordField(15);
		usernameTextField = new JTextField(15);
		userTypeComboBox = new JComboBox<String>(new String[] { USER_TYPE_BUYER, USER_TYPE_SELLER });

		hGroup.addGroup(formLayout.createParallelGroup().addComponent(emailLabel).addComponent(passwordLabel)
				.addComponent(usernameLabel).addComponent(userTypeLabel));
		hGroup.addGroup(formLayout.createParallelGroup().addComponent(emailTextField).addComponent(passwordTextField)
				.addComponent(usernameTextField).addComponent(userTypeComboBox));

		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(emailLabel)
				.addComponent(emailTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(passwordLabel)
				.addComponent(passwordTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(usernameLabel)
				.addComponent(usernameTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(userTypeLabel)
				.addComponent(userTypeComboBox));

		formLayout.setHorizontalGroup(hGroup);
		formLayout.setVerticalGroup(vGroup);

		return formPanel;
	}

	public void close() {
		flushFields();

		setVisible(false);
		mainWindow.setVisible(false);
	}

}
