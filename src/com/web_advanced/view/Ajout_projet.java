package com.web_advanced.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.LDAPObject;
import com.web_advanced.model.LDAPaccess;
import com.web_advanced.model.Projet;

public class Ajout_projet extends VerticalLayout {

	private Projet projet = new Projet();
	private final TextField name = new TextField("Nom :");
	private final TextArea desc = new TextArea("Description :");
	private final TextField owner = new TextField("Propriéaire :");
	private final TextField tutor = new TextField("Tuteur (mail isep) :");
	private final TextField responsable = new TextField("Responsable (mail isep) :");
	private final Button save = new Button("Ajouter");
	
	LDAPaccess LDAP_access = new LDAPaccess();;

	
	public Ajout_projet(Controller controller) {
		
		//add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());
		
		setSpacing(true);
		addComponent(name);
		addComponent(desc);
		addComponent(owner);
		addComponent(tutor);
		addComponent(responsable);

		addComponent(save);

		save.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				projet.setName(name.getValue().toString());
				projet.setDescription(desc.getValue().toString());
				projet.setOwner(owner.getValue().toString());
				try {

					LDAPObject tutor_id = LDAP_access.LDAPget(tutor.getValue()
							.toString());
					projet.setTutor_id(Integer.parseInt(tutor_id.getNumber()));

					LDAPObject responsible_id = LDAP_access.LDAPget(responsable
							.getValue().toString());
					projet.setResponsible_id(Integer.parseInt(responsible_id
							.getNumber()));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				projet.insert();

			}
		});

	}
}