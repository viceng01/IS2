package com.rainforest.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class ProductButton extends JButton {
	public ProductButton() {
		initGUI();
	}

	private void initGUI() {
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setLayout(new BorderLayout());

		String productName = "Product";
		int productQuantity = 1;
		float productPrice = 1.5f;

		add(new JLabel(productName), BorderLayout.NORTH);
		add(new JLabel("" + productQuantity), BorderLayout.CENTER);
		add(new JLabel("" + productPrice), BorderLayout.SOUTH);
	}
}
