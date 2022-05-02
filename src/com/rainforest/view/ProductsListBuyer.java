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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.rainforest.model.user.User;

import javax.swing.ImageIcon;

public class ProductsListBuyer extends JDialog implements ActionListener{

	protected int _status;
	private JButton addButton;
	private JButton removeButton;
	private JButton modify;
	private JButton sigOut;
	private JButton cestaC;
	
	private JLabel direccion;

	private JPanel botonesArriba;
	private JPanel arribaPanel;
	
	private MainWindow mw;
	private ControlPanel cp;
	private ModifyBuyer mb;
	
	private User u;
	
	public ProductsListBuyer(MainWindow mw, ControlPanel cp) {
		this.mw = mw;
		this.cp = cp;
		mb = new ModifyBuyer(mw,cp,this);
		initGUI();
	}

	private void initGUI( ) {
		setTitle("Buyer interface");
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(500,200));
		/*JPanel viewsPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(viewsPanel, BoxLayout.Y_AXIS));*/
		
		
		arribaPanel = new JPanel(new FlowLayout());
		
		
		mainPanel.add(arribaPanel,BorderLayout.PAGE_START);
		
		direccion = new JLabel("Direccion:");
		arribaPanel.add(direccion);
		
		//this.add(Box.createRigidArea(new Dimension(10, 0)));
		cesta();
		//this.add(Box.createRigidArea(new Dimension(10, 0)));
		sigOut();
		//this.add(Box.createRigidArea(new Dimension(10, 0)));
		modifyUser();
		
		
		//
		//
		
		//Para meter los productos
		JPanel tablaMedio = new JPanel(new GridLayout(0, 4));
		
		
		//Para los botones de ADD y remove
		JPanel abajoPanel = new JPanel();
		JPanel buttonPanel = createButtonPanel();
		abajoPanel.add(buttonPanel);
		
		mainPanel.add(arribaPanel);
		mainPanel.add(tablaMedio);
		mainPanel.add(abajoPanel);
		
		/*
		for(int i = 0; i < 30; ++i) {
			tablaMedio.add(new ProductButton());
		}*/
		

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
			public void windowActivated(WindowEvent e) {
				direccion.setText("Direccion: " + u.getUserInfo().getDir());
			}

			@Override
			public void windowDeactivated(WindowEvent e) {}

        });
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}


	private JPanel createButtonPanel(){	
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");

		removeButton.setBackground(Color.white);
		addButton.setBackground(Color.white);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		addButton.addActionListener(this);
		removeButton.addActionListener(this);
		
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);

		setVisible(false);
		return buttonPanel;
	
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == "Add") {

			showMessageDialog("Add successful");
		}else if (e.getSource() == "Remove") {
			showMessageDialog("Remove successful");
		}
	}

	public int open(User u) {
		this.u = u;
		direccion.setText("Direccion: " + u.getUserInfo().getDir());
		setVisible(true);
		return 1;
	}
	
	private void showMessageDialog(String message) {
		JOptionPane.showConfirmDialog(null, message, "Product status", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void sigOut(){
		//creamos el nuevo boton con la imagen proporcionada
		sigOut = new JButton(new ImageIcon("resources/signOut.jpg"));
		
		sigOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		sigOut.setToolTipText("Cerrar sesion");
		this.arribaPanel.add(sigOut,BorderLayout.NORTH);
	}
	
	public void cesta(){
		//creamos el nuevo boton con la imagen proporcionada
				cestaC = new JButton(new ImageIcon("resources/1.jpg"));
				
				cestaC.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//setVisible(false);
						
						
					}
				});
				cestaC.setToolTipText("Cesta compra");
				this.arribaPanel.add(cestaC,BorderLayout.NORTH);
		
	}
	
	public void modifyUser(){
		//creamos el nuevo boton con la imagen proporcionada
		modify = new JButton(new ImageIcon("resources/usuario.png"));
		
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int o = mb.open(u);
				if (o == 1) {
					setVisible(false);
				}
				
				return;
				//quit();
			}
		});
		modify.setToolTipText("Modifica datos usuario");
		this.arribaPanel.add(modify,BorderLayout.NORTH);
	}
	
	 private void quit() {
	    	int option = JOptionPane.showOptionDialog(this, "Are you sure you want to sign out?", "Sign Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 1); // el 1 es para q x defecto la opcion senalada sea NO
	        if (option == 0) {
	        	setVisible(false);

				mw.setVisible(true);
				cp.setVisible(true);
	        }else {
	        	setVisible(true);
	        }
		}
}
