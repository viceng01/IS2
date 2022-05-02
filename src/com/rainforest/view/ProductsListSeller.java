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
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;

public class ProductsListSeller extends JDialog implements ActionListener{

	protected int _status;
	private int option;
	private JButton addButton;
	private JButton removeButton;
	private JButton modifyButton;
	protected JLabel direccion;

	private JButton sigOut;
	private JButton modify;

	private JPanel botonesArriba;
	private JPanel tablaMedio ;
	
	private MainWindow mw;
	private ControlPanel cp;
	private ModifySeller ms;
	private User u;
	
	private DefaultListModel model;
	private JList list;
	
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
				addProducts();
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
		model = new DefaultListModel();
		if (u!= null) {
			JSONArray ja = mw.getProducts(u.getUserInfo().getUserID());
			for (int i = 0; i < ja.length(); i++) {
				/*
				JPanel panel = new JPanel(new FlowLayout());
				panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				panel.setLayout(new BorderLayout());
				*/
				DefaultListModel aux = new DefaultListModel();
				JSONObject jo = ja.getJSONObject(i);
				JSONObject p = jo.getJSONObject("product");
				/*
				JLabel name = new JLabel("name: " + p.getString("name"));
				JLabel desc = new JLabel(p.getString("description"));
				JLabel price = new JLabel("price: " + String.valueOf(p.getFloat("price")));
				JLabel amount = new JLabel("amount: " + String.valueOf(jo.getInt("amount")));
				
				panel.add(name,BorderLayout.NORTH);
				panel.setToolTipText(desc.getText());
				panel.add(price,BorderLayout.CENTER);
				panel.add(amount,BorderLayout.SOUTH);
				*/
				String n = "name: " + p.getString("name");
				
				String pr = "price: " + String.valueOf(p.getFloat("price"));
				String a = "amount: " + String.valueOf(jo.getInt("amount"));
				//String id = p.getString("GUID");
				aux.addElement(n);
				aux.addElement(pr);
				aux.addElement(a);
				//aux.addElement(id);
				
	            model.addElement(aux);
	            

				
				
				
			}
		}
		list = new JList();
		list.setModel(model);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scroll1 = new JScrollPane(list);
        scroll1.setViewportView(list);
        JScrollBar scrollBar = scroll1.getVerticalScrollBar();
        
        
        
        tablaMedio.add(scroll1);
	}

	private JPanel createButtonPanel(){	
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		modifyButton = new JButton ("Modify");

		removeButton.setBackground(Color.white);
		addButton.setBackground(Color.white);
		modifyButton.setBackground(Color.white);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int aux [] = list.getSelectedIndices();
				Object[] aux1 = list.getSelectedValues();
				for (int j = 0; j < aux1.length ; j++) {
					//String borrar = aux1.toString();
					String[] borrar = aux1.toString().split("\\s+");
					GUID g = new GUID(borrar[3]);
					mw.removeProduct(g, u.getUserInfo().getDNI());
				}
				if (model.getSize() == aux.length)
					model.removeAllElements();
				else{
					for (int i = 0; i < aux.length ; i++ ) {
						model.remove(i);
					}
				}
			}
		});
		addButton.setToolTipText("Add new product");
		removeButton.setToolTipText("Remove selected product");
		modifyButton.setToolTipText("Modify selected product");
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(modifyButton);

		setVisible(false);
		return buttonPanel;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
