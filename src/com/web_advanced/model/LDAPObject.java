package com.web_advanced.model;

import java.io.Serializable;

/**
 * Utilisateur r??cup??r?? de OpenLDAP
 * 
 * @author Gilles Carpentier
 * @version 1/12/2009
 * 
 */
public class LDAPObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nom;
	private String employeeType;
	private String employeeNumber;
	private String login;
	private String password;
	private String mail;

	public LDAPObject(String login, String password, String nom, String type,
			String numero, String mail) {
		this.nom = nom;
		this.employeeType = type;
		this.employeeNumber = numero;
		this.login = login;
		this.password = password;
		this.mail = mail;
	}

	public String getNom() {
		return nom;
	}

	public String getLogin() {
		return login;
	}

	public String getType() {
		return employeeType;
	}

	public String getNumber() {
		return employeeNumber;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return "login = " + login + " nom = " + nom + " type = " + employeeType
				+ " id = " + employeeNumber;
	}
}