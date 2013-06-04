package com.web_advanced.controller;

import com.vaadin.ui.Window;
import com.web_advanced.view.Connexion;

public class Controller {
	
	Window window;
	
	public Controller(Window window){
		this.window = window;
	}
	
	public void index(){
		Connexion c = new Connexion();
		window.setContent(c);
	}
}
