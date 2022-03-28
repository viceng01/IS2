package com.rainforest.view;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.rainforest.view.toolbar.file.GeneralMenu;

public class MainMenu extends JMenuBar {

	public MainMenu(MainWindow mainWindow)
	{
		initGUI(mainWindow);
	}

	private void initGUI(MainWindow mainWindow) {
		JMenuItem fileMenu = new GeneralMenu(mainWindow);
		
		add(fileMenu);
	}
	
}
