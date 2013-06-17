package com.web_advanced.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Mysql {

	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	public Mysql() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "web_advanced";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		try {
			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager.getConnection(url + dbName,
					userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ResultSet select(String sql){
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql) ; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int update(String sql){
		int res = 0;
		try {
			st = (Statement) conn.createStatement();
			res = st.executeUpdate(sql) ; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void closeRequest(){
		try {
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnexion(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
