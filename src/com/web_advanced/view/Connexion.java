package com.web_advanced.view;

import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.VerticalLayout;
import com.web_advanced.controller.Controller;
import com.web_advanced.model.LDAPaccess;
import com.web_advanced.model.User;




@SuppressWarnings("serial")
public class Connexion extends VerticalLayout {
	
	private LDAPaccess LDAP_access;
	
	public Connexion(final Controller controller) {
		
		LoginForm login = new LoginForm();
		login.setWidth("100%");
		login.setHeight("300px");
		
		login.addListener(new LoginForm.LoginListener() {
			public void onLogin(LoginEvent event) {
				String id = event.getLoginParameter("username");
				String pwd = event.getLoginParameter("password");
				User user = User.connexion(LDAP_access, id, pwd);
				if(user != null){
					
					getApplication().getMainWindow().setContent(controller.myProject(user));
				}
			}
		});
		addComponent(login);

	}

}