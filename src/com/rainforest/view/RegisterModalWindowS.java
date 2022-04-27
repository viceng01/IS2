package com.rainforest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.json.JSONObject;

public class RegisterModalWindowS extends JDialog implements ActionListener{

	private static final String USER_TYPE_BUYER = "Buyer";
	private static final String USER_TYPE_SELLER = "Seller";
			

	private JTextField dirTextField;
	private JTextField dniTextField;
	private JTextField telTextField;
	private JTextField rfcTextField;
	private JButton submit;
	private MainWindow mw;
	private ControlPanel cp;
	private int option;
	private JSONObject jo;
	private boolean add;

	public RegisterModalWindowS(MainWindow mainWindow,ControlPanel cp) {
		mw = mainWindow;
		this.cp = cp;
		initGUI(mainWindow);
	}
	
	private void initGUI(MainWindow mainWindow) {
		setTitle("Register Buyer");

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel formPanel = createFormPanel();
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		submit = new JButton("Submit");
		submit.setToolTipText("Cerrar sesion");
		formPanel.add(submit);
		
		submit.setBackground(Color.white);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		submit.addActionListener(this);
		
		buttonPanel.add(submit);
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel,BorderLayout.PAGE_END);
		
		setContentPane(mainPanel);
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				quit();
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
		
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
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

		JLabel dir = new JLabel("Direccion");
		JLabel dni= new JLabel("DNI");
		JLabel tel = new JLabel("Telefono");
		JLabel rfc = new JLabel("RFC vendedor");

		dirTextField = new JTextField(15);
		dniTextField = new JTextField(15);
		telTextField = new JTextField(15);
		rfcTextField = new JTextField(15);

		hGroup.addGroup(formLayout.createParallelGroup().addComponent(dir).addComponent(dni)
				.addComponent(tel).addComponent(rfc));
		hGroup.addGroup(formLayout.createParallelGroup().addComponent(dirTextField).addComponent(dniTextField)
				.addComponent(telTextField).addComponent(rfcTextField));

		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(dir)
				.addComponent(dirTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(dni)
				.addComponent(dniTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(tel)
				.addComponent(telTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(rfc)
				.addComponent(rfcTextField));

		formLayout.setHorizontalGroup(hGroup);
		formLayout.setVerticalGroup(vGroup);

		return formPanel;
	}

	
	public int open() {
		setVisible(true);
		return 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		add = false;
		if (dirTextField.getText().equals("") || telTextField.getText().equals("") || dniTextField.getText().equals(""))
			showErrorDialog("Some fields empty");
		else {
			int aux = Integer.parseInt(telTextField.getText());
			if (mw.doesRegisterBuyerExist(dniTextField.getText(), aux)) 
				showErrorDialog("Data already used");
			else {
				add = true;
				setVisible(false);
				cp.addSeller(Integer.parseInt(telTextField.getText()),dirTextField.getText(),dniTextField.getText(),rfcTextField.getText());
				showMessageDialog("Succesful register!");
				
				dirTextField.setText(null);
				dniTextField.setText(null);
				telTextField.setText(null);
				
	
				mw.setVisible(true);
				cp.setVisible(true);
				
			}
		}		
		
		
	}
	
	 private void quit() {
	    	option = JOptionPane.showOptionDialog(this, "Are you sure you want to leave?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 1); // el 1 es para q x defecto la opcion senalada sea NO
	        if (option == 0) {
	        	setVisible(false);

				mw.setVisible(true);
				cp.setVisible(true);
				
	        }else {
	        	setVisible(true);
	        }
		}
	 
	 private void showErrorDialog(String message) {
			JOptionPane.showConfirmDialog(null, message, "Register error", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	 
	 private void showMessageDialog(String message) {
			JOptionPane.showConfirmDialog(null, message, "Registration", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
		}
	 
}
