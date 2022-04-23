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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class RegisterModalWindowS extends JDialog implements ActionListener{

	private static final String USER_TYPE_BUYER = "Buyer";
	private static final String USER_TYPE_SELLER = "Seller";
			

	private JTextField dirTextField;
	private JTextField dniTextField;
	private JTextField telTextField;
	private JButton submit;
	private JComboBox<String> userTypeComboBox;
	private ProductsList productList;
	private ProductsListSeller pls;
	private MainWindow mw;
	private ControlPanel cp;
	private int option;

	public RegisterModalWindowS(MainWindow mainWindow,ControlPanel cp) {
		mw = mainWindow;
		this.cp = cp;
		initGUI(mainWindow);
	}
	
	private void initGUI(MainWindow mainWindow) {
		setTitle("Register Seller");

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
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				quit();
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

           

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

		dirTextField = new JTextField(15);
		dniTextField = new JTextField(15);
		telTextField = new JTextField(15);

		hGroup.addGroup(formLayout.createParallelGroup().addComponent(dir).addComponent(dni)
				.addComponent(tel));
		hGroup.addGroup(formLayout.createParallelGroup().addComponent(dirTextField).addComponent(dniTextField)
				.addComponent(telTextField));

		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(dir)
				.addComponent(dirTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(dni)
				.addComponent(dniTextField));
		vGroup.addGroup(formLayout.createParallelGroup(Alignment.BASELINE).addComponent(tel)
				.addComponent(telTextField));

		formLayout.setHorizontalGroup(hGroup);
		formLayout.setVerticalGroup(vGroup);

		return formPanel;
	}


	private void showMessageDialog(String message) {
		JOptionPane.showConfirmDialog(null, message, "Registration", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

	
	public int open() {
		//setResizable(false);
		setVisible(true);
		//this.setLocationRelativeTo(getParent());
		return 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {//Cuando se ha registrado volver a la otra pantalla y darlo de alta en los json
			showMessageDialog("Reg successful");
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
}
