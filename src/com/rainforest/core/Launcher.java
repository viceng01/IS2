package com.rainforest.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.rainforest.controller.Controller;
import com.rainforest.factories.Builder;
import com.rainforest.factories.BuilderBasedFactory;
import com.rainforest.factories.BuyerBuilder;
import com.rainforest.factories.Factory;
import com.rainforest.factories.SellerBuilder;
import com.rainforest.model.RainforestModel;
import com.rainforest.model.user.User;
import com.rainforest.view.MainWindow;

public class Launcher {

	private static Factory<User> _eventsFactory = null;
	private static Controller controller;

	private static String _inFile = "BaseDeDatos.json";
	private static String _outFile = null;
	
	private static void initFactories() {
		List<Builder<User>> lista = new ArrayList<>();
		lista.add(new BuyerBuilder());
		lista.add(new SellerBuilder());

		Factory<User> eventsFactory = new BuilderBasedFactory<>(lista);
		_eventsFactory = eventsFactory;
		
	}

	public static void main(String[] args) {
		initFactories();
		controller = new Controller(_eventsFactory);
		InputStream in = null;
		try {
			//OutputStream os = _outFile == null ? System.out : new FileOutputStream(new File(_outFile));
			in = new FileInputStream(_inFile);
			controller.loadDataBase(in);
			in.close();
			//os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> {
			new MainWindow(controller);
		});
	}
	
	

}
