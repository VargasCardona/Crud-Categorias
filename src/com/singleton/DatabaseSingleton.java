package com.singleton;

import com.excepciones.ConexionNoInicializadaException;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSingleton {

	private static DatabaseSingleton instance;
	private Connection connection;

	private final String BASE = "taller_foraneas";
	private final String USUARIO = "root";
	private final String CONTRASENIA = "";
	private final String URL = "jdbc:mariadb://localhost:3306/" + BASE;

	private DatabaseSingleton() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			connection = (Connection) DriverManager.getConnection(this.URL, this.USUARIO, this.CONTRASENIA);

		} catch (SQLNonTransientConnectionException ex) {
			throw new ConexionNoInicializadaException();
		} catch (SQLException e) {
			System.err.println(e);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DatabaseSingleton.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public static DatabaseSingleton getInstance() {
		if (instance == null){
			instance = new DatabaseSingleton();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	public void closeConnection(){
		if (connection != null){
			try {
				connection.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

}
