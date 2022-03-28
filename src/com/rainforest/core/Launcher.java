package com.rainforest.core;

import javax.swing.SwingUtilities;

import com.rainforest.view.MainWindow;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MainWindow();
		});
	}

}
