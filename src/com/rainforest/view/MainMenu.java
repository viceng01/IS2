package com.rainforest.view;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.rainforest.view.toolbar.file.FileMenu;

public class MainMenu extends JMenuBar {

	public MainMenu()
	{
		initGUI();
	}

	private void initGUI() {
		JMenuItem fileMenu = new FileMenu();
		
		add(fileMenu);
	}
	
}
