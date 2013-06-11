package com.web_advanced.controller;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * Main application class.
 */
public class Web_advancedApplication extends Application {

	private Window mainWindow;

	
	@Override
	public void init() {
		
		mainWindow = new Window();
		setMainWindow(mainWindow);

		Controller c = new Controller(mainWindow);
		//c.index();
		//c.greeting();
		//c.add_project();
		c.test();
	}


	

}
