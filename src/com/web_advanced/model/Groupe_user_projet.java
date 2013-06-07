package com.web_advanced.model;

public class Groupe_user_projet {

	private int id;
	private int id_user;
	private int id_user_projet;
	

	public Groupe_user_projet(int id, int id_user, int id_user_projet) {
		this.id = id;
		this.id_user = id_user;
		this.id_user_projet = id_user_projet;
		
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



	public int getId_user_projet() {
		return id_user_projet;
	}



	public void setId_user_projet(int id_user_projet) {
		this.id_user_projet = id_user_projet;
	}



	public void insert(){
		Mysql mysql = new Mysql();
		String req = "INSERT INTO groupe_user_projet (id_user,id_user_projet) VALUES ("+id_user+","+id_user_projet+")"; 
		mysql.update(req);
		mysql.closeRequest();
		mysql.closeConnexion();

	}
}
