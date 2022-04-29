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

import com.rainforest.core.GUID;
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

	public LoginResponse doesUserExist(String email, String pasw,String user) {
		return model.doesUserExist(email,pasw,user);
	}
	
	public boolean doesRegisterBuyerExist(String dni, int tel) {
		return model.doesRegisterBuyerExist(dni,tel);
	}
	
	public boolean removeUser(String email, String password,String type) {
		return model.removeUser(email, password,type);
	}

	public LoginResponse tryLogin(String email, String password,String type, String user) {
		return model.tryLogin(email, password,type,user);
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



	public User getUser(String text) {
		return model.getUser(text);
		
	}



	



	public boolean tryModifyUser(String user, String dni) {
		// TODO Auto-generated method stub
		return model.tryModifyUser(user,dni);
	}



	public boolean tryModifyTel(int tel, String dni) {
		// TODO Auto-generated method stub
		return model.tryModifyTel(tel, dni);
	}



	public boolean tryModifyEmail(String email, String dni) {
		// TODO Auto-generated method stub
		return model.tryModifyEmail(email, dni);
	}



	public boolean tryModifyDir(String dir, String dni) {
		// TODO Auto-generated method stub
		return model.tryModifyDir(dir, dni);
	}



	public boolean tryModifyPass(String pass, String dni) {
		// TODO Auto-generated method stub
		return model.tryModifyPass(pass, dni);
	}



	public JSONArray getProducts(GUID guid) {
		// TODO Auto-generated method stub
		return model.getProducts(guid);
	}



	

	
}
