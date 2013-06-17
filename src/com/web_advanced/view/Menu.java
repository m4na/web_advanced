package com.web_advanced.view;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Menu extends VerticalLayout {

    private MenuBar menubar = new MenuBar();

    public Menu() {

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem Profil = menubar.addItem("Profil", null);
        final MenuBar.MenuItem Projets = menubar.addItem("Projets", null);
        final MenuBar.MenuItem Deconnexion = menubar.addItem("Déconnexion", null);
        //addComponent(menubar);
    }
    
    public MenuBar getMenubar(){
    	return menubar;
    }

    

}