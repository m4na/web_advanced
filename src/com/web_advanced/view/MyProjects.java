package com.web_advanced.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.Projet;
import com.web_advanced.model.User;

public class MyProjects extends VerticalLayout {

	List<Button> bList;
	Label projectNameParticipate;
	Label projectNameResponsible;
	User user;
	Button searchProject;
	Button addProject;
	Controller controller;

	public MyProjects(final Controller controller) {

		this.controller = controller;
		
		// add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());

		// display projects list of the user
		projectNameParticipate = new Label("Liste des projets auquels vous participez :");
        addComponent(projectNameParticipate);
        bList = new ArrayList<Button>();
        user = (User) controller.getContext().getHttpSession().getAttribute("user");
        setList(user.listProject(0), bList);
        
        projectNameResponsible = new Label("Liste des projets auquels vous êtes responsable :");
        addComponent(projectNameResponsible);
        bList = new ArrayList<Button>();
        user = (User) controller.getContext().getHttpSession().getAttribute("user");
        setList(user.listProject(1), bList);
        
        searchProject = new Button("Faire une recherche de projet");
        addComponent(searchProject);
        
        addProject = new Button("Ajouter un projet");
        addComponent(addProject);
        
        searchProject.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controller.getWindow().setContent(controller.projectList(null));

			}
		});
        
        addProject.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controller.getWindow().setContent(controller.addProject());

			}
		});
	}

	private void setList(List<Projet> projets, List<Button> bList) {

		// clear list
		for (int i = 0; i < bList.size(); i++) {
			removeComponent(bList.get(i));
		}
		bList = new ArrayList<Button>();

		for (int i = 0; i < projets.size(); i++) {
			Button b = new Button(projets.get(i).getName());
			b.setStyleName(BaseTheme.BUTTON_LINK);
			b.setDescription(projets.get(i).getDescription());
			final Projet current = projets.get(i);
			b.addListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					controller.getWindow().setContent(controller.projectView(current));

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
