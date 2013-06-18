package com.web_advanced.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;

public class MyProjects extends VerticalLayout{

	public MyProjects(final Controller controller) {

		// add the menu
		Menu menu = new Menu();
		addComponent(menu.getMenubar());

		// display projects list
	}


	/*
	 * Shows a notification when a button is clicked.
	 */
	public void buttonClick(ClickEvent event) {
		System.out.println(event.getSource());
		getWindow().showNotification("Coucou");
	}

}
