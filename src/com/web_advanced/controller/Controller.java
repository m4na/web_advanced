package com.web_advanced.controller;

import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Window;
import com.web_advanced.model.Projet;
import com.web_advanced.model.User;
import com.web_advanced.view.AddGroup;
import com.web_advanced.view.Ajout_projet;
import com.web_advanced.view.Connexion;
import com.web_advanced.view.ProjectView;
import com.web_advanced.view.ProjectsList;

public class Controller {

	Window window;
	WebApplicationContext context;

	public Controller(Window window) {
		this.window = window;
	}

	public void index(WebApplicationContext context) {
		this.context = context;
		Connexion c = new Connexion(this);
		window.setContent(c);
	}
	
	public Connexion index(){
		Connexion c = new Connexion(this);
		return c;
	}
	
	public Ajout_projet addProject(){
		Ajout_projet ap = new Ajout_projet(this);
		return ap;
	}

	public ProjectsList projectList(User user) {
		if (user != null) {
			context.getHttpSession().setAttribute("user", user);
		}
		//get all user projects
		user = (User) context.getHttpSession().getAttribute("user");
		user.listProject();
		ProjectsList pl = new ProjectsList(this);
		return pl;
	}
	
	public ProjectView projectView(Projet p){
		ProjectView pv = new ProjectView(this, p);
		return pv;
	}

	public WebApplicationContext getContext() {
		return context;
	}

	public AddGroup addGroup(Projet projet) {
		AddGroup ag = new AddGroup(this, projet);
		return ag;
	}

	public Window getWindow(){
		return window;
	}
}
