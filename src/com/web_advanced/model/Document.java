package com.web_advanced.model;

public class Document {

	private int id;
	private int id_goupe_projet;
	

	public Document(int id, int id_goupe_projet) {
		this.id = id;
		this.id_goupe_projet = id_goupe_projet;
		
	}

	public Document() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_projet() {
		return id_goupe_projet;
	}

	public void setId_projet(int id_goupe_projet) {
		this.id_goupe_projet = id_goupe_projet;
	}

	public void insert(){
		Mysql mysql = new Mysql();
		String req = "INSERT INTO document (id_goupe_projet) VALUES ("+id_goupe_projet+")"; 
		mysql.update(req);
		mysql.closeRequest();
		mysql.closeConnexion();

	}
}
