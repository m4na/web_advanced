package com.web_advanced.view;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import com.web_advanced.controller.Controller;

@SuppressWarnings("serial")
public class Menu extends VerticalLayout {

    private MenuBar menubar = new MenuBar();
    Controller c;

    public Menu(Controller c) {
    	
    	this.c = c;

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem Projets = menubar.addItem("Projets", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
            	locationProjects();
            }
        });
        final MenuBar.MenuItem Deconnexion = menubar.addItem("Déconnexion", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
            	logout();
            }
        });

        //addComponent(menubar);
        
    }
    
    public MenuBar getMenubar(){
    	return menubar;
    }
    
    private void logout(){
    	c.getWindow().setContent(c.index());
    }
    
    private void locationProjects(){
    	c.getWindow().setContent(c.myProject(null));
    }

    

}