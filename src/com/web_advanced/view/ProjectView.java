package com.web_advanced.view;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.Projet;

public class ProjectView extends VerticalLayout{

	Label projectName;
	Label projectDescription;
	Label projectOwner;
	Label projectTutor;
	Label projectResponsible;
	
	public ProjectView(Controller controller, Projet projet){
		
		//add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());
		
		projectName = new Label();
		projectName.setValue("Nom: "+projet.getName());
		projectName.setContentMode(Label.CONTENT_TEXT);
		
		projectDescription = new Label();
		projectDescription.setValue("Description: "+projet.getDescription());
		projectDescription.setContentMode(Label.CONTENT_TEXT);
		
		projectOwner = new Label();
		projectOwner.setValue("Owner: "+projet.getOwner());
		projectOwner.setContentMode(Label.CONTENT_TEXT);
		
		projectTutor = new Label();
		projectTutor.setValue("Tuteur: "+projet.getTutor());
		projectTutor.setContentMode(Label.CONTENT_TEXT);
		
		projectResponsible = new Label();
		projectResponsible.setValue("Responsable: "+projet.getResponsible());
		projectResponsible.setContentMode(Label.CONTENT_TEXT);
		
		setSpacing(true);
		addComponent(projectName);
		setSpacing(true);
		addComponent(projectDescription);
		setSpacing(true);
		addComponent(projectOwner);
		setSpacing(true);
		addComponent(projectTutor);
		setSpacing(true);
		addComponent(projectResponsible);
	}
	
}
