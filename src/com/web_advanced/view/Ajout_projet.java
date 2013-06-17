package com.web_advanced.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.web_advanced.model.Projet;

public class Ajout_projet extends VerticalLayout {
	
	private Projet projet = new Projet();
	private final TextField name = new TextField("Nom :");
	private final TextArea desc = new TextArea("Description :");
	private final TextField owner = new TextField("Propriéaire :");
	private final TextField tutor = new TextField("Tuteur :");
	private final TextField responsable = new TextField("Responsable :");
	private final Button save = new Button("Ajouter");

	public Ajout_projet() {
		
		//add the menu
		Menu menu = new Menu();
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
				projet.setTutor_id(Integer.parseInt((tutor.getValue().toString())));
				projet.setResponsible_id(Integer.parseInt((responsable.getValue().toString())));
				projet.insert();

			}
		});

	}
}