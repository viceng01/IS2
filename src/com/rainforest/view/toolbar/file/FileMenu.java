package com.rainforest.view.toolbar.file;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {
	
	public FileMenu()
	{
		super("File");
		
		initGUI();
	}

	private void initGUI() {
		JMenuItem exitMenuItem = new ExitMenuItem();
		
		add(exitMenuItem);
	}
}
