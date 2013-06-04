package com.web_advanced.view;

import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.VerticalLayout;
import com.web_advanced.model.LDAPObject;
import com.web_advanced.model.LDAPaccess;
import com.web_advanced.model.User;

@SuppressWarnings("serial")
public class Connexion extends VerticalLayout {
	
	private LDAPaccess LDAP_access;
	
	public Connexion() {
		
		LoginForm login = new LoginForm();
		login.setWidth("100%");
		login.setHeight("300px");
		
		login.addListener(new LoginForm.LoginListener() {
			public void onLogin(LoginEvent event) {
				String id = event.getLoginParameter("username");
				String pwd = event.getLoginParameter("password");
				User.connexion(LDAP_access, id, pwd);
			}
		});
		addComponent(login);

	}

}