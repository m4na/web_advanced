package com.web_advanced.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.Groupe_projet;
import com.web_advanced.model.Projet;

public class ProjectView extends VerticalLayout{

	Label projectName;
	Label projectDescription;
	Label projectOwner;
	Label projectTutor;
	Label projectResponsible;
	
	List<Button> bList;
	
	Controller controller;
	
	Button createGroup;
	
	public ProjectView(final Controller controller, final Projet projet){
		
		this.controller = controller;
		
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
		
		//TODO add responsible and tutor test
		//find all groups from the project
		List<Groupe_projet> gps = projet.listGroup();
		bList = new ArrayList<Button>();
		setGroupUserList(gps, bList);
		
		//TODO test if responsible
		createGroup = new Button("Créer un groupe");
		createGroup.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				controller.getWindow().setContent(controller.addGroup(projet));
			}
		});
		addComponent(createGroup);
		
	}
	
	private void setGroupUserList(List<Groupe_projet> gps, List<Button> bList) {

		// clear list
		for (int i = 0; i < bList.size(); i++) {
			removeComponent(bList.get(i));
		}
		bList = new ArrayList<Button>();

		for (int i = 0; i < gps.size(); i++) {
			Button b = new Button(String.valueOf(gps.get(i).getId()));
			b.setStyleName(BaseTheme.BUTTON_LINK);
			final Groupe_projet current = gps.get(i);
			b.addListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					//TODO event 
					//controller.getWindow().setContent(controller.projectView(current));
				}
			});
			bList.add(b);
		}
		this.bList = bList;
		// fetch butons
		for (int i = 0; i < bList.size(); i++) {
			setSpacing(true);
			addComponent(bList.get(i));
		}
	}
	
}
