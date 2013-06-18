package com.web_advanced.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	}
	
	public static List<Projet> findProjects(String search){
		Mysql mysql = new Mysql();
		String sql = "SELECT * FROM projet WHERE name LIKE '%"+search+"%'";
		ResultSet rs = mysql.select(sql);
		
		List<Projet> list = new ArrayList<Projet>();
		
		try {
			while (rs.next()) {
				Projet p = new Projet();
				p.setId(Integer.parseInt(rs.getObject("id").toString()));
				p.setName(rs.getObject("name").toString());
				p.setDescription(rs.getObject("description").toString());
				p.setOwner(rs.getObject("owner").toString());
				p.setTutor_id(Integer.parseInt(rs.getObject("tutor_id").toString()));
				p.setResponsible_id(Integer.parseInt(rs.getObject("responsible_id").toString()));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
