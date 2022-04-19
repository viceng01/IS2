package com.rainforest.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ProductsList extends JPanel {
	public ProductsList() {
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(0, 4));
		
		for(int i = 0; i < 50; ++i) {
			add(new ProductSlotRenderer());
		}
		
	}
}
