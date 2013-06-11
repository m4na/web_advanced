package com.web_advanced.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.ui.Window;
import com.web_advanced.model.Mysql;
import com.web_advanced.model.Projet;
import com.web_advanced.view.Ajout_projet;
import com.web_advanced.view.Connexion;
import com.web_advanced.view.Menu;
import com.web_advanced.view.Test;

public class Controller {
	
	Window window;
	
	public Controller(Window window){
		this.window = window;
	}
	
	public void index(){
		Connexion c = new Connexion();
		window.setContent(c);
	}
	
	public void greeting(){
		Menu ac = new Menu();
		window.setContent(ac);
	}
	
	public void add_project(){
		Ajout_projet ap = new Ajout_projet();
		window.setContent(ap);
	}
	
	public void test(){
		Test tt = new Test();
		window.setContent(tt);
	}
}
