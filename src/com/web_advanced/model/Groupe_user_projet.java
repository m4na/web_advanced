package com.web_advanced.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Groupe_user_projet {

	private int id;
	private int id_user;
	private int id_groupe_projet;
	
	LDAPaccess LDAP_access;

	public Groupe_user_projet(int id, int id_user, int id_user_projet) {
		this.id = id;
		this.id_user = id_user;
		this.id_groupe_projet = id_user_projet;
		
	}
	
	public Groupe_user_projet() {
		
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId_user() {
		return id_user;
	}



	public void setId_user(int id_user) {
		this.id_user = id_user;
	}



	public int getId_groupe_projet() {
		return id_groupe_projet;
	}



	public void setId_groupe_projet(int id_user_projet) {
		this.id_groupe_projet = id_user_projet;
	}



	public void insert(){
		Mysql mysql = new Mysql();
		String req = "INSERT INTO groupe_user_projet (id_user,id_groupe_projet) VALUES ("+id_user+","+id_groupe_projet+")"; 
		mysql.update(req);
		mysql.closeRequest();
		mysql.closeConnexion();

	}
	
	// renvoie tout les utilisateurs d'un même groupe
	public List<User> listUser(int id) {
		Mysql mysql = new Mysql();
		String sql = "SELECT * FROM groupe_user_projet WHERE groupe_user_projet.id_groupe_projet = " + id;
		ResultSet rs = mysql.select(sql);
		List<User> list = new ArrayList<User>();
		try {
			while (rs.next()) {
				
				User user = new User();
				LDAP_access = new LDAPaccess();
				LDAPObject obj;
				try {
					obj = LDAP_access.LDAPget2(rs.getObject("groupe_user_projet.id_user").toString());
					user.setId(obj.getNumber());
					user.setLogin(obj.getLogin());
					user.setName(obj.getNom());
					user.setType(obj.getType());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
