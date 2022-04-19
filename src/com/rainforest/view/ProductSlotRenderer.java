package com.rainforest.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class ProductSlotRenderer extends JPanel {
	public ProductSlotRenderer() {
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
