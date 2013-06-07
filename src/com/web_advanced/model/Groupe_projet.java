package com.web_advanced.model;

public class Groupe_projet {

	private int id;
	private int id_projet;
	

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

	public void insert(){
		Mysql mysql = new Mysql();
		String req = "INSERT INTO groupe_projet (id_projet) VALUES ("+id_projet+")"; 
		mysql.update(req);
		mysql.closeRequest();
		mysql.closeConnexion();

	}
}
