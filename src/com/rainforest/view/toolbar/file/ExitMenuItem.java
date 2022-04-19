package com.rainforest.view.toolbar.file;

import javax.swing.JMenuItem;

import com.rainforest.view.MainWindow;

public class ExitMenuItem extends JMenuItem {

	public ExitMenuItem(MainWindow mainWindow) {
		super("Exit...");

		initGUI(mainWindow);
	}

	private void initGUI(MainWindow mainWindow) {
		addActionListener((e) -> {
			mainWindow.close();
		});
	}
}
