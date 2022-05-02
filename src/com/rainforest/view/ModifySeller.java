package com.rainforest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.json.JSONObject;

import com.rainforest.model.user.User;

public class ModifySeller extends JDialog implements ActionListener{

	private static final String USER_TYPE_BUYER = "Buyer";
	private static final String USER_TYPE_SELLER = "Seller";
			

	private JTextField dirTextField;
	private JTextField dniTextField;
	private JTextField telTextField;
	private JTextField emailTextField;
	private JTextField userTextField;
	private char puntitos;
	private JLabel dir = new JLabel("Direccion");//si
	private JLabel dni= new JLabel("DNI");//no modificable
	private JLabel tel = new JLabel("Telefono");//si
	private JLabel email = new JLabel("Email");//si
	private JLabel user= new JLabel("Username");//si
	private JLabel pass = new JLabel("Password");//si
	private JPasswordField passTextField;
	private JCheckBox mostrar;
	private JButton submit;
	private JButton exit;
	private MainWindow mw;
	private ControlPanel cp;
	private int option;
	private JSONObject jo;
	private boolean add;
	private User u;
	private JDialog ant;
	JPanel mainPanel;

	public ModifySeller(MainWindow mainWindow,ControlPanel cp, JDialog anterior) {
		mw = mainWindow;
		this.cp = cp;
		this.ant = anterior;

		initGUI(mainWindow);
	}
	
	private void initGUI(MainWindow mainWindow) {
		setTitle("Modify data");

		mainPanel= new JPanel(new BorderLayout());
		
		//JPanel formPanel = createFormPanel();
		//mainPanel.add(formPanel);
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(7,7));
		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Datos del usuario",
				TitledBorder.LEFT, TitledBorder.TOP));
		
		dirTextField = new JTextField();
		dniTextField = new JTextField(15);
		telTextField = new JTextField(15);
		emailTextField = new JTextField(15);
		userTextField = new JTextField(15);
		passTextField = new JPasswordField(15);
		
		puntitos = passTextField.getEchoChar();
		
		formPanel.add(dir);
		formPanel.add(dirTextField);
		formPanel.add(dni);
		formPanel.add(dniTextField);
		formPanel.add(tel);
		formPanel.add(telTextField);
		formPanel.add(email);
		formPanel.add(emailTextField);
		formPanel.add(user);
		formPanel.add(userTextField);
		formPanel.add(pass);
		formPanel.add(passTextField);
		
		mostrar = new JCheckBox("Mostrar contraseña");
        mostrar.setBounds(10,10,150,30);
        mostrar.addActionListener(this);
		
        JPanel aux = new JPanel();
        
        formPanel.add(mostrar);
        
		submit = new JButton("Confirm");
		exit = new JButton ("Return");
		submit.addActionListener(this);
		exit.addActionListener(this);
		aux.add(submit);
		aux.add(exit);
        formPanel.add(aux);
        
		/*
		hGroup.addGroup(formLayout.createParallelGroup().addComponent(dir).addComponent(dni)
				.addComponent(tel).addComponent(pass).addComponent(email).addComponent(user));
		hGroup.addGroup(formLayout.createParallelGroup().addComponent(dirTextField).addComponent(dniTextField)
				.addComponent(telTextField).addComponent(emailTextField).addComponent(userTextFiled).addComponent(dirTextField));
*/
		mainPanel.add(formPanel);
		
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

	
	public int open(User u) {
		this.u = u;
		emailTextField.setText(u.getUserInfo().getEmail());
		userTextField.setText(u.getUserInfo().getUsername());
		dniTextField.setText(u.getUserInfo().getDNI());//bloquearlo
		dniTextField.setEditable(false);
		telTextField.setText(String.valueOf(u.getUserInfo().getTel()));
		dirTextField.setText(u.getUserInfo().getDir());
		//gui??
		passTextField.setText(u.getUserInfo().getPassword());
		
		
		setVisible(true);
		return 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		add = false;
		if (e.getSource() == mostrar) {
			
			if(mostrar.isSelected())
				passTextField.setEchoChar((char)0);//lo hacemos visible
			else 
				passTextField.setEchoChar(puntitos);
			
		}
		if (e.getSource() == submit) {
			if (dirTextField.getText().equals("") || telTextField.getText().equals("") || userTextField.getText().equals("") 
					|| emailTextField.getText().equals("") || passTextField.getText().equals(""))
				showErrorDialog("Some fields empty");
			else {//Si no hay ningun campo vacio comprobamos en primer lugar si se ha modificado algo
				JSONObject o = u.getJson();
				boolean salir = false;
				if (!o.get("username").equals(userTextField.getText())){//Se modifico usuario
					if (!mw.tryModifyUser( userTextField.getText(),dniTextField.getText())) {
						showErrorDialog("User already used");
						userTextField.setText(o.getString("username"));
						salir = true;
					}
					
					else u.getUserInfo().setUsername(userTextField.getText());
				}
				int aux = Integer.parseInt(telTextField.getText());
				
				if (o.getInt("tel") != aux) {//Si el telefono se ha modificado
					if (!mw.tryModifyTel( aux,dniTextField.getText())) {
						showErrorDialog("Telephone already used");
						telTextField.setText(String.valueOf(o.getInt("tel")));
						salir = true;
					}
					else u.getUserInfo().setTel(aux);
					
				}
				if (!o.get("email").equals(emailTextField.getText())) {//Si se modifico el email
					if (!mw.tryModifyEmail( emailTextField.getText(),dniTextField.getText())) {
						showErrorDialog("Email already used");
						emailTextField.setText(o.getString("email"));
						salir = true;
					}
					else u.getUserInfo().setEmail(emailTextField.getText());
				}
				if (!o.get("direction").equals(dirTextField.getText())) {//Si se modifico el email
					if (!mw.tryModifyDir( dirTextField.getText(),dniTextField.getText())) {
						showErrorDialog("Cannot update dir");
						dirTextField.setText(o.getString("direction"));
						salir = true;
					}
					else u.getUserInfo().setDir(dirTextField.getText());
				
				}
				if (!o.getString("password").equals(passTextField.getText())) {
					if (!mw.tryModifyPass( passTextField.getText(),dniTextField.getText())) {
						showErrorDialog("Cannot update pass");
						passTextField.setText(o.getString("password"));
						salir = true;
					}
					else u.getUserInfo().setPassword(passTextField.getText());
				}
				if (!salir) {
					setVisible(false);
					ant.setVisible(true);
					
					
					//ant.getComponent(1)aux;
				}
			}	
		}
		else if (e.getSource() == exit) {
			setVisible(false);
			ant.setVisible(true);
			
		}
		
		
	}
	
	 private void quit() {
	    	option = JOptionPane.showOptionDialog(this, "Are you sure you want to leave?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 1); // el 1 es para q x defecto la opcion senalada sea NO
	        if (option == 0) {
	        	setVisible(false);
	        	ant.setVisible(true);
				//mw.setVisible(true);
				//cp.setVisible(true);
				
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
