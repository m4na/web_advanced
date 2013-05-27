package com.example.web_advanced;

import java.sql.SQLException;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.data.util.sqlcontainer.query.generator.DefaultSQLGenerator;

public class mysql {

	private SimpleJDBCConnectionPool connectionPool;
	private DefaultSQLGenerator generator;
	private TableQuery tq; // la petite nouveautÈ ici

	public mysql(String server, String user, String pwd) {
		try {
			connectionPool = new SimpleJDBCConnectionPool(
					"com.mysql.jdbc.Driver", server, user, pwd, 2, 2);
			System.out
					.println("connection pool created for MySQL on " + server);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		generator = new DefaultSQLGenerator();
	}

	public SQLContainer queryTable(String tableName) {
		SQLContainer container = null;

		try {
			tq = new TableQuery(tableName, connectionPool, generator);
			container = new SQLContainer(tq);
			System.out.println("container created for table " + tableName);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return container;
	}

	/*******************/
	// j'ai ajoutÈ cette fonction pour utiliser la tablequery tq dans l'autre
	// page
	public TableQuery getTableQuery() {
		return tq;
	}
	/***********************/

}
