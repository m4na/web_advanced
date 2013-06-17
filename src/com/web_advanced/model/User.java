package com.web_advanced.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Mysql{

	String id;
	String login;
	String name;
	String type;
	
	public static User connexion(LDAPaccess LDAP_access, String id, String pwd) {
		LDAP_access = new LDAPaccess();
		User user = null;
		try {
			LDAPObject obj = LDAP_access.LDAPget(id, pwd);
			user = new User();
			user.setId(obj.getNumber());
			user.setLogin(obj.getLogin());
			user.setName(obj.getNom());
			user.setType(obj.getType());
			
		} catch (Exception e) {
			System.out.println("fail !");
		}
		
		return user;
	}
	
	public Projet listProject(){
		Mysql mysql = new Mysql();
		String sql = "SELECT * FROM groupe_user_projet "+
				"INNER JOIN groupe_projet " +
				"ON groupe_user_projet.id_groupe_projet = groupe_projet.id " +
				"INNER JOIN projet " +
				"ON groupe_projet.id_projet = projet.id " +
				"WHERE groupe_user_projet.id_user = "+id;
				
		ResultSet rs = mysql.select(sql);
		try {
			while (rs.next()) {
				System.out.println(rs.getObject(1) + " " + rs.getObject(2)
						+ " " + rs.getObject(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
