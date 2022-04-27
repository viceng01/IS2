package com.rainforest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.rainforest.controller.Controller;

public class ControlPanel extends JPanel {

	private static final String USER_TYPE_BUYER = "Buyer";
	private static final String USER_TYPE_SELLER = "Seller";
	private static String _inFile = "BaseDeDatos.json";

	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JTextField usernameTextField;
	private JComboBox<String> userTypeComboBox;
	private ProductsList productList;
	private ProductsListSeller pls;
	private RegisterModalWindowB rmwb;
	private RegisterModalWindowS rmws;
	private MainWindow mw;
	private Controller ctrl;
	private String email;
	private String password;
	private String username ;

	public ControlPanel(MainWindow mainWindow, Controller ctrl) {
		this.ctrl= ctrl; 
		mw = mainWindow;
		initGUI(mainWindow);
		
		/*TODO*/
		//HABRIA QUE DEJA SOLO UN PRODUCT LIST Y QUE LLAME A LA INTERFAZ CORRESPONDIENTE
		productList = new ProductsList(mw,this);
		pls = new ProductsListSeller(mw,this);
		rmwb = new RegisterModalWindowB(mw,this);
		rmws = new RegisterModalWindowS(mw,this);
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
		
		
		JButton loginButton = new JButton("Login");
		JButton registerButton = new JButton("Register");
		
		

		loginButton.addActionListener((ae) -> {
			LoginResponse response = mainWindow.authenticate(emailTextField.getText(), passwordTextField.getText(),userTypeComboBox.getSelectedItem().toString());

			//Si el usuario ha iniciado correctamente sesion se muestra la pantalla 
			//correspondiente al usuario, pudiendo ser esta la del admin, buyer o seller
			if (response == LoginResponse.OK) {
				showMessageDialog("Login successful");
				//Una vez tenemos el usuario, habria que buscar el que tenga ese email para poder ver los prodcutos que
				//ofrece ese vendedor, en caso de que el usuario sea un vendedor claro
				
				if (userTypeComboBox.getSelectedItem().toString().equals("Seller")){
					int sol = pls.open();
					if (sol == 1) {
						setVisible(false);
					}
					
				}else {
					int sol = productList.open();
					if (sol == 1) {
						setVisible(false);
					}
				}
				
				this.mw.setVisible(false);
				emailTextField.setText(null);
				passwordTextField.setText(null);
				usernameTextField.setText(null);
				userTypeComboBox.setSelectedIndex(0);
				return;
			}
			
			
				

			String message;

			switch (response) {
			case UNKNOWN_USER:
				message = "Unknown email";
				break;
			case INCORRECT_PASSWORD:
				message = "Incorrect email or password";
				break;
			case OK:
				message = "Todo fue bien:)";
				break;
			default:
				message = "Unexpected error";
			}

			showErrorDialog(message);
		});
		registerButton.addActionListener((ae) -> {
			email = emailTextField.getText();

			password = passwordTextField.getText();
			username = usernameTextField.getText();
			
			//Para comprobar que los datos del usuario que hemos introducido no esten 
			//ya dados de alta en la base de datos
			LoginResponse r = mainWindow.doesUserExist(email,password,username);
			if (r != LoginResponse.OK) {
				String message;

				switch (r) {
				case EMPTY_DATA:
					message = "Some fields empty";
					break;
				case ALREADY_USED:
					message = "Data already used";
					break;
				default:
					message = "Unexpected error";
				}
				JOptionPane.showConfirmDialog(null, message, "Register error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String type = userTypeComboBox.getSelectedItem().toString();
			
			//En caso de seleccionar el buyer...
				if (userTypeComboBox.getSelectedItem().equals(USER_TYPE_BUYER)) {
					//Abrimos el jdialog para registrar un buyer
					int option = rmwb.open();
					if (option == 1) {
						setVisible(false);
					}
				} else {
					//Abrimos el jdialog para registrar un buyer
					int option = rmws.open();
					if (option == 1) {
						setVisible(false);
					}
				}
				this.mw.setVisible(false);

				emailTextField.setText(null);
				passwordTextField.setText(null);
				usernameTextField.setText(null);
				userTypeComboBox.setSelectedIndex(0);
				return;
			
		});

		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		

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
		passwordTextField = new JTextField(15);
		usernameTextField = new JTextField(15);
		userTypeComboBox = new JComboBox<String>(new String[] { USER_TYPE_BUYER, USER_TYPE_SELLER});

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

	private void showErrorDialog(String message) {
		JOptionPane.showConfirmDialog(null, message, "Login error", JOptionPane.DEFAULT_OPTION,
				JOptionPane.ERROR_MESSAGE);
	}

	private void showMessageDialog(String message) {
		JOptionPane.showConfirmDialog(null, message, "Seller registration", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void addBuyer(int tel,String dir, String dni) {
		if (tel != 0) {
			ctrl.addBuyer(email,password,username,dir,dni,tel);
		}
	}
	
	public void addSeller(int tel,String dir, String dni, String rfc) {
		if (tel != 0) {
			ctrl.addSeller(email,password,username,dir,dni,tel,rfc);
		}
	}
	
	
}
