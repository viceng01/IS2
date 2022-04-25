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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

public class ProductsListSeller extends JDialog implements ActionListener{

	protected int _status;
	private int option;
	private JButton addButton;
	private JButton removeButton;

	private JButton sigOut;

	private JPanel botonesArriba;
	
	private MainWindow mw;
	private ControlPanel cp;
	
	public ProductsListSeller(MainWindow mw, ControlPanel cp) {
		this.mw = mw;
		this.cp = cp;
		initGUI();
	}

	private void initGUI( ) {
		setTitle("Product List");
		JPanel mainPanel = new JPanel(new BorderLayout());
		//setLayout(new GridLayout(0, 4));
		
		//
		//
		JPanel arribaPanel = new JPanel(new FlowLayout (FlowLayout.LEFT));
		arribaPanel.setLayout(new FlowLayout (FlowLayout.LEFT));
		
		
		JLabel direccion = new JLabel("Direccion:");
		
		botonesArriba = new JPanel(new FlowLayout(FlowLayout.LEFT));
		botonesArriba.add(direccion);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		sigOut();
		
		
		
		arribaPanel.add(botonesArriba);
		//
		//
		
		//Para meter los productos
		JPanel tablaMedio = new JPanel(new GridLayout(0, 4));
		
		
		//Para los botones de ADD y remove
		JPanel abajoPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = createButtonPanel();
		abajoPanel.add(buttonPanel,BorderLayout.CENTER);
		
		mainPanel.add(arribaPanel, BorderLayout.PAGE_START);
		mainPanel.add(tablaMedio, BorderLayout.CENTER);
		mainPanel.add(abajoPanel,BorderLayout.PAGE_END);
		
		
		for(int i = 0; i < 30; ++i) {
			tablaMedio.add(new ProductButton());
		}
		

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

	public int open() {
		//setResizable(false);
		setVisible(true);
		//this.setLocationRelativeTo(getParent());
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
		this.botonesArriba.add(sigOut);
	}
	
	 private void quit() {
	    	option = JOptionPane.showOptionDialog(this, "Are you sure you want to sign out?", "Sign Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 1); // el 1 es para q x defecto la opcion senalada sea NO
	        if (option == 0) {
	        	setVisible(false);

				mw.setVisible(true);
				cp.setVisible(true);
	        }else {
	        	setVisible(true);
	        }
		}
	
}
