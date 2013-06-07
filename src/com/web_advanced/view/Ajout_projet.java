package com.web_advanced.view;

import java.util.Arrays;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class Ajout_projet extends VerticalLayout {

	public Ajout_projet() {
		// Create the Form
		final Form projectForm = new Form();
		projectForm.setCaption("Ajouter un projet");
		projectForm.setWriteThrough(false);
		projectForm.setInvalidCommitted(false);

		// Determines which properties are shown, and in which order:
		projectForm.setVisibleItemProperties(Arrays.asList(new String[] {
				"Nom du projet", "Description du projet", "Propriétaire",
				"Responsable de projet", "Tuteur du projet", "Pièce joint" }));

		// Add form to layout
		addComponent(projectForm);

		// The cancel / apply buttons
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);
		Button discardChanges = new Button("Discard changes",
				new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						projectForm.discard();
					}
				});
		discardChanges.setStyleName(BaseTheme.BUTTON_LINK);
		buttons.addComponent(discardChanges);
		buttons.setComponentAlignment(discardChanges, Alignment.MIDDLE_LEFT);

		Button apply = new Button("Apply", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					projectForm.commit();
				} catch (Exception e) {
					// Ignored, we'll let the Form handle the errors
				}
			}
		});
		buttons.addComponent(apply);
		projectForm.getFooter().addComponent(buttons);
		projectForm.getFooter().setMargin(false, false, true, true);
	}

}