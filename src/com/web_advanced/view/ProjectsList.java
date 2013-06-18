package com.web_advanced.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.Projet;

public class ProjectsList extends VerticalLayout{

	Button add;
	TextField projectName;
	Button search;
	List<Button> bList;
	
	public ProjectsList(final Controller controller){
		
		//add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());
		
		
		//display projects list
		//setList();
		
		//add button
        add = new Button("Ajouter un projet");
        addComponent(add);
        add.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().setContent(controller.addProject());
				
			}
		});
        projectName = new TextField("Nom du projet :");
        addComponent(projectName);
        search = new Button("Recherche");
        addComponent(search);
        search.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				String searchValue = projectName.getValue().toString();
				List<Projet> projets = Projet.findProjects(searchValue);
				setList(projets, bList);
			}
		});
        
        bList = new ArrayList<Button>();
		
	}
	
	private void setList(List<Projet> projets, List<Button> bList){
		
		//clear list
		for(int i = 0; i<bList.size(); i++){
			removeComponent(bList.get(i));
		}
		bList = new ArrayList<Button>();
		
		for(int i = 0; i<projets.size(); i++){
			Button b = new Button(projets.get(i).getName());
			b.setStyleName(BaseTheme.BUTTON_LINK);
	        b.setDescription(projets.get(i).getDescription());
	        b.addListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					
				}
			});
	        bList.add(b);
		}
		this.bList = bList;
		//fetch butons
		for(int i = 0; i<bList.size(); i++){
			setSpacing(true);
			addComponent(bList.get(i));
		}
	}
}
