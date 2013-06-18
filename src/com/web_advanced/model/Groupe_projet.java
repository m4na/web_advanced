package com.web_advanced.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Groupe_projet {

	private int id;
	private int id_projet;

	LDAPaccess LDAP_access;

	public Groupe_projet(int id, int id_projet) {
		this.id = id;
		this.id_projet = id_projet;

	}

	public Groupe_projet() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_projet() {
		return id_projet;
	}

	public void setId_projet(int id_projet) {
		this.id_projet = id_projet;
	}

	public void insert() {
		Mysql mysql = new Mysql();
		String req = "INSERT INTO groupe_projet (id_projet) VALUES ("
				+ id_projet + ")";
		mysql.update(req);
		mysql.closeRequest();
		mysql.closeConnexion();
		
		mysql = new Mysql();
		req = "SELECT * FROM groupe_projet ORDER BY id DESC";
		ResultSet rs = mysql.select(req);
		
		try {
			rs.next();
			setId(Integer.parseInt(rs.getObject("id").toString()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Groupe_projet listGroup(Projet projet) {
		Mysql mysql = new Mysql();
		LDAP_access = new LDAPaccess();
		String sql = "SELECT * FROM groupe_user_projet "
				+ "INNER JOIN groupe_projet "
				+ "ON groupe_user_projet.id_groupe_projet = groupe_projet.id "
				+ "WHERE groupe_projet.id_projet = " + projet.getId();

		ResultSet rs = mysql.select(sql);
		try {
			while (rs.next()) {
				try {
					System.out.println(LDAP_access.LDAPget2(rs.getObject(2)
							.toString()));
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
