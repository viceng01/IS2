package com.rainforest.view.toolbar.file;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.rainforest.view.MainWindow;

public class GeneralMenu extends JMenu {
	
	public GeneralMenu(MainWindow mainWindow)
	{
		super("General");
		
		initGUI(mainWindow);
	}

	private void initGUI(MainWindow mainWindow) {
		JMenuItem exitMenuItem = new ExitMenuItem(mainWindow);
		
		add(exitMenuItem);
	}
}
