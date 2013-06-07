package com.web_advanced.model;

public class Projet {

	private int id;
	private String name;
	private String description;
	private String owner;
	private int tutor_id;
	private int responsible_id;

	public Projet(int id, String name, String description, String owner,
			int tutor_id, int responsible_id) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.tutor_id = tutor_id;
		this.responsible_id = responsible_id;
	}
	public Projet() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getTutor_id() {
		return tutor_id;
	}

	public void setTutor_id(int tutor_id) {
		this.tutor_id = tutor_id;
	}

	public int getResponsible_id() {
		return responsible_id;
	}

	public void setResponsible_id(int responsible_id) {
		this.responsible_id = responsible_id;
	}

	public void insert(){
		Mysql mysql = new Mysql();
		String req = "INSERT INTO projet (name, description, owner, tutor_id, responsible_id ) VALUES ('"+name+"','"+description+"','"+owner+"',"+tutor_id+","+responsible_id+")"; 
		mysql.update(req);
		mysql.closeRequest();
		mysql.closeConnexion();
		/*ResultSet rs = mysql.request("SELECT * FROM projet");
		try {
			while (rs.next()) {
				System.out.println(rs.getObject(1) + " " + rs.getObject(2)
						+ " " + rs.getObject(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

}
