package com.web_advanced.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.terminal.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.web_advanced.controller.Controller;
import com.web_advanced.controller.FileUploader;
import com.web_advanced.model.Groupe_projet;
import com.web_advanced.model.Groupe_user_projet;
import com.web_advanced.model.Projet;
import com.web_advanced.model.User;

public class ProjectView extends VerticalLayout{

	Label projectName;
	Label projectDescription;
	Label projectOwner;
	Label projectTutor;
	Label projectResponsible;
	
	List<Button> bList;
	
	Controller controller;
	
	Button createGroup;
	
	Groupe_user_projet gup;
	
	Panel panel = null;
	
	public ProjectView(final Controller controller, final Projet projet){
		
		this.controller = controller;
		
		//add the menu
		Menu menu = new Menu(controller);
		addComponent(menu.getMenubar());
		
		projectName = new Label();
		projectName.setValue("Nom: "+projet.getName());
		projectName.setContentMode(Label.CONTENT_TEXT);
		
		projectDescription = new Label();
		projectDescription.setValue("Description: "+projet.getDescription());
		projectDescription.setContentMode(Label.CONTENT_TEXT);
		
		projectOwner = new Label();
		projectOwner.setValue("Owner: "+projet.getOwner());
		projectOwner.setContentMode(Label.CONTENT_TEXT);
		
		projectTutor = new Label();
		projectTutor.setValue("Tuteur: "+projet.getTutor());
		projectTutor.setContentMode(Label.CONTENT_TEXT);
		
		projectResponsible = new Label();
		projectResponsible.setValue("Responsable: "+projet.getResponsible());
		projectResponsible.setContentMode(Label.CONTENT_TEXT);
		
		setSpacing(true);
		addComponent(projectName);
		setSpacing(true);
		addComponent(projectDescription);
		setSpacing(true);
		addComponent(projectOwner);
		setSpacing(true);
		addComponent(projectTutor);
		setSpacing(true);
		addComponent(projectResponsible);
		
		//add responsible or tutor test
		//find all groups from the project
		User user = (User) controller.getContext().getHttpSession().getAttribute("user");
		if(user.isResponsible(projet) || user.isTutor(projet)){
			List<Groupe_projet> gps = projet.listGroup();
			bList = new ArrayList<Button>();
			setGroupUserList(gps, bList);
		}
		
		
		//test if responsible
		if(user.isResponsible(projet)){
			createGroup = new Button("Créer un groupe");
			createGroup.addListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					controller.getWindow().setContent(controller.addGroup(projet));
				}
			});
			addComponent(createGroup);
		}
		
		int idGroup = user.getGroupProject(projet);
		if(idGroup!=0){
			gup = new Groupe_user_projet();
			gup.setId_groupe_projet(idGroup);
			setUserList();
		}
		
	}
	
	private void setGroupUserList(List<Groupe_projet> gps, List<Button> bList) {

		// clear list
		for (int i = 0; i < bList.size(); i++) {
			removeComponent(bList.get(i));
		}
		bList = new ArrayList<Button>();

		for (int i = 0; i < gps.size(); i++) {
			Button b = new Button(String.valueOf(gps.get(i).getId()));
			b.setStyleName(BaseTheme.BUTTON_LINK);
			final Groupe_projet current = gps.get(i);
			b.addListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					//TODO event 
					gup = new Groupe_user_projet();
					gup.setId_groupe_projet(current.getId());
					setUserList();
				}
			});
			bList.add(b);
		}
		this.bList = bList;
		// fetch butons
		for (int i = 0; i < bList.size(); i++) {
			setSpacing(true);
			addComponent(bList.get(i));
		}
	}
	
	private void setUserList() {
		if(panel!=null){
			removeComponent(panel);
		}
		List<User> listUser = gup.listUser();
		panel = new Panel("Groupe");
		for(int i=0;i<listUser.size();i++){
			Label nameUser = new Label(listUser.get(i).getName());
			setSpacing(true);
			panel.addComponent(nameUser);
		}
		setSpacing(true);
		setSpacing(true);
		// Create the upload with a caption and set receiver later
		Upload upload = new Upload("Upload Image Here", null);
		upload.setButtonCaption("Start Upload");
		        
		FileUploader uploader = new FileUploader(String.valueOf(gup.getId_groupe_projet())); 
		//list files 
		File[] fileList = uploader.listFiles();
		for (int i = 0; i < fileList.length; i++) 
		{
			if (fileList[i].isFile()) 
			{
				System.out.println(fileList[i].getName());
				Button b = new Button(fileList[i].getName());
				b.setStyleName(BaseTheme.BUTTON_LINK);
				final File current = fileList[i];
				b.addListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						FileResource resource = new FileResource(current, getApplication());
						controller.getWindow().open(resource);
					}
				});
				panel.addComponent(b);
			}
		}
		
		// Put the upload component in a panel
		panel.addComponent(upload);
		
		addComponent(panel);
		        
		
		upload.setReceiver(uploader);
		upload.addListener(uploader);
	}
	
}
