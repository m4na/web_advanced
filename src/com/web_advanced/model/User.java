package com.web_advanced.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User extends Mysql {

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

	public List<Projet> listProject(int type) {
		Mysql mysql = new Mysql();
		String sql = null;
		// type == 0
		// select all the projects that the user participate
		if (type == 0) {
			sql = "SELECT * FROM groupe_user_projet "
					+ "INNER JOIN groupe_projet "
					+ "ON groupe_user_projet.id_groupe_projet = groupe_projet.id "
					+ "INNER JOIN projet "
					+ "ON groupe_projet.id_projet = projet.id "
					+ "WHERE groupe_user_projet.id_user = " + id;

			// type == 1
			// select all the projects that the user is responsible
		} else if (type == 1) {
			sql = "SELECT * FROM projet WHERE projet.responsible_id = " + id;
		}

		ResultSet rs = mysql.select(sql);
		List<Projet> list = new ArrayList<Projet>();
		try {
			while (rs.next()) {
				
				Projet p = new Projet();
				p.setId(Integer.parseInt(rs.getObject("projet.id").toString()));
				p.setName(rs.getObject("projet.name").toString());
				p.setDescription(rs.getObject("projet.description").toString());
				p.setOwner(rs.getObject("projet.owner").toString());
				p.setTutor_id(Integer.parseInt(rs.getObject("projet.tutor_id")
						.toString()));
				p.setResponsible_id(Integer.parseInt(rs.getObject(
						"projet.responsible_id").toString()));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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
