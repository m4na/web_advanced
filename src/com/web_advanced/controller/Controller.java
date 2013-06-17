package com.web_advanced.controller;

import com.vaadin.ui.Window;
import com.web_advanced.model.User;
import com.web_advanced.view.Ajout_projet;
import com.web_advanced.view.Connexion;
import com.web_advanced.view.Menu;
import com.web_advanced.view.ProjectsList;

public class Controller {
	
	Window window;
	
	public Controller(Window window){
		this.window = window;
	}
	
	public void index(){
		Connexion c = new Connexion();
		window.setContent(c);
	}
	
	public Ajout_projet addProject(){
		Ajout_projet ap = new Ajout_projet();
		return ap;
	}
	
	public ProjectsList projectList(User user){
		
		//get all user projects
		user.listProject();
		ProjectsList pl = new ProjectsList();
		return pl;
	}
	
	
	
}
