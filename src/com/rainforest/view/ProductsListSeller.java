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
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.model.user.User;

public class ProductsListSeller extends JDialog implements ActionListener{

	protected int _status;
	private int option;
	private JButton addButton;
	private JButton removeButton;
	protected JLabel direccion;

	private JButton sigOut;
	private JButton modify;

	private JPanel botonesArriba;
	private JPanel tablaMedio ;
	
	private MainWindow mw;
	private ControlPanel cp;
	private ModifySeller ms;
	private User u;
	
	public ProductsListSeller(MainWindow mw, ControlPanel cp) {
		this.mw = mw;
		this.cp = cp;
		ms = new ModifySeller(mw,cp, this);
		initGUI();
	}

	private void initGUI( ) {
		setTitle("Seller interface");
		JPanel mainPanel = new JPanel(new BorderLayout());
		//setLayout(new GridLayout(0, 4));
		mainPanel.setPreferredSize(new Dimension(500,200));
		//
		//
		JPanel arribaPanel = new JPanel(new FlowLayout (FlowLayout.LEFT));
		arribaPanel.setLayout(new FlowLayout (FlowLayout.LEFT));
		
		
		direccion = new JLabel("Direccion:");
		
		
		botonesArriba = new JPanel(new FlowLayout(FlowLayout.LEFT));
		botonesArriba.add(direccion);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		sigOut();
		
		modifyUser();
		
		arribaPanel.add(botonesArriba);
		//
		//
		
		//Para meter los productos
		tablaMedio = new JPanel(new FlowLayout());
		tablaMedio.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Products",
				TitledBorder.LEFT, TitledBorder.TOP));
		
		//Para los botones de ADD y remove
		JPanel abajoPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = createButtonPanel();
		abajoPanel.add(buttonPanel,BorderLayout.CENTER);
		
		mainPanel.add(arribaPanel, BorderLayout.PAGE_START);
		mainPanel.add(tablaMedio, BorderLayout.CENTER);
		mainPanel.add(abajoPanel,BorderLayout.PAGE_END);
		
		/*
		for(int i = 0; i < 30; ++i) {
			tablaMedio.add(new ProductButton());
		}*/
		

		setContentPane(mainPanel);
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				addProducts();
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
				direccion.setText("Direccion: " + u.getUserInfo().getDir());
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

           

        });
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}


	private void addProducts() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panel.setLayout(new BorderLayout());
		if (u!= null) {
		JSONArray ja = mw.getProducts(u.getUserInfo().getUserID());
		for (int i = 0; i < ja.length(); i++) {
			JSONObject jo = ja.getJSONObject(i);
			JSONObject p = jo.getJSONObject("product");
			JLabel name = new JLabel("n-" + p.getString("name"));
			JLabel desc = new JLabel(p.getString("description"));
			JLabel price = new JLabel("p-" + String.valueOf(p.getFloat("price")));
			JLabel amount = new JLabel("u-" + String.valueOf(jo.getInt("amount")));
			
			panel.add(name,BorderLayout.NORTH);
			panel.setToolTipText(desc.getText());
			panel.add(price,BorderLayout.CENTER);
			panel.add(amount,BorderLayout.SOUTH);
			tablaMedio.add(panel);
			
		}
		}
		
		
		
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

	public int open(User user) {
		this.u = user;
		
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
	
	public void modifyUser(){
		//creamos el nuevo boton con la imagen proporcionada
		modify = new JButton(new ImageIcon("resources/usuario.png"));
		
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int o = ms.open(u);
				if (o == 1) {
					setVisible(false);
				}
				direccion.setText("Direccion: " + u.getUserInfo().getDir());
				return;
				//quit();
			}
		});
		modify.setToolTipText("Modifica datos usuario");
		this.botonesArriba.add(modify);
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
	 
	 public void setDir(String dir) {
		 direccion.setText("Direccion: " + dir);
	 }
	
}
