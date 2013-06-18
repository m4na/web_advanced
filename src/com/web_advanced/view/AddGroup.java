package com.web_advanced.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.Groupe_projet;
import com.web_advanced.model.Groupe_user_projet;
import com.web_advanced.model.LDAPObject;
import com.web_advanced.model.LDAPaccess;
import com.web_advanced.model.Projet;

public class AddGroup extends VerticalLayout {
	
	Groupe_projet groupeProjet;
	
	TextField userList;
	Button addGroup;
	
	public AddGroup(final Controller controller, final Projet projet) {

		// add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());
		
		//display group list of the project
		groupeProjet = new Groupe_projet();
		groupeProjet.setId_projet(projet.getId());
		
		userList = new TextField("Utilisateurs à ajouter (; pour séparer) :");
        addComponent(userList);
        
        addGroup = new Button("Ajouter le groupe");
        addGroup.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				groupeProjet.insert();
				String str[]=userList.getValue().toString().split(";");
				LDAPaccess LDAP_access = new LDAPaccess();
				for(int i = 0; i<str.length; i++){
					Groupe_user_projet gup = new Groupe_user_projet();
					try {
						LDAPObject onj = LDAP_access.LDAPget(str[i]);
						gup.setId_user(Integer.parseInt(onj.getNumber()));
						gup.setId_groupe_projet(groupeProjet.getId());
						gup.insert();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				controller.getWindow().setContent(controller.projectView(projet));
			}
		});
        addComponent(addGroup);
	}
	
	
}
