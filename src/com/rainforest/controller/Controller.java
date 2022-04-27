package com.rainforest.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.rainforest.factories.Factory;
import com.rainforest.model.RainforestModel;
import com.rainforest.model.user.User;
import com.rainforest.view.LoginResponse;


public class Controller {

	private static String _outFile = "BaseDeDatos.json";
	private RainforestModel model;
	private Factory<User> userFactory;
	private OutputStream os;
	private  JSONArray usuarios;
	
	public Controller(Factory<User> userFactory) {
		this.userFactory = userFactory;
		model = new RainforestModel();
	}

	
	
	public boolean addUser(User user) {
		return model.addUser(user);
	}

	public LoginResponse doesUserExist(String email, String password,String user) {
		return model.doesUserExist(email,password,user);
	}
	
	public boolean doesRegisterBuyerExist(String dni, int tel) {
		return model.doesRegisterBuyerExist(dni,tel);
	}
	
	public boolean removeUser(User user) {
		return model.removeUser(user);
	}

	public LoginResponse tryLogin(String email, String password,String type) {
		return model.tryLogin(email, password,type);
	}
	

	
	public void loadDataBase(InputStream in) {
        JSONObject jo = new JSONObject(new JSONTokener(in));
        usuarios = jo.getJSONArray("usuarios");
        
        for(int i = 0; i < usuarios.length(); i++) {
        	JSONObject o = usuarios.getJSONObject(i);
        	User u = this.userFactory.createInstance(o);
        	
        	this.addUser(u);
            
        }
        
	}

	
	public void addBuyer(String email, String password, String user,String dir, String dni,int tel) {
		model.addBuyer(email,password,user,dir,dni,tel);
	}
	
	public void addSeller(String email, String password, String username, String dir, String dni, int tel, String rfc) {
		model.addSeller(email,password,username,dir,dni,tel,rfc);
		
	}



	public void saveChanges() {
		OutputStream os = null;
		try {
			os = _outFile == null ? System.out : new FileOutputStream(new File(_outFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream p = new PrintStream(os);

		JSONArray a = model.getUsuarios();
		JSONObject salida = new JSONObject();
		salida.put("usuarios", a);
		
		
		try {
			p.write(salida.toString(3).getBytes());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}



	

	
}
