package com.web_advanced.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.Groupe_projet;
import com.web_advanced.model.Projet;

public class AddGroup extends VerticalLayout {
	
	Groupe_projet groupe_projet;
	
	public AddGroup(final Controller controller, Projet projet) {

		// add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());
		
		//display group list of the project
		groupe_projet = new Groupe_projet();
		groupe_projet.listGroup(projet);
	}
	
	
}
