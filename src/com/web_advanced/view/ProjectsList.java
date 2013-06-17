package com.web_advanced.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;

public class ProjectsList extends VerticalLayout implements Button.ClickListener{

	Button add;
	
	public ProjectsList(final Controller controller){
		
		//add the menu
		Menu menu = new Menu();
		addComponent(menu.getMenubar());
		
		//display projects list
		setList();
		
		//add button
        add = new Button("Ajouter un projet");
        addComponent(add);
        add.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().setContent(controller.addProject());
				
			}
		});
		
	}
	
	private void setList(){
		setSpacing(true);

        // Button w/ text and tooltip
        Button b = new Button("test");
        b.setStyleName(BaseTheme.BUTTON_LINK);
        b.setDescription("tool test");
        b.addListener(this); // react to clicks
        addComponent(b);
	}
	
	/*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(ClickEvent event) {
    	System.out.println(event.getSource());
        getWindow().showNotification("Coucou");
    }
   
	
}
