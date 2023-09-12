package com.conexion;

import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {

	private final String BASE = "productos";
	private final String USUARIO = "root";
	private final String CONTRASENIA = "";

	private final String URL = "jdbc:mariadb://localhost:3306/" + BASE;
	private Connection con = null;

	public Connection getConexion() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			con = (Connection) DriverManager.getConnection(this.URL, this.USUARIO, this.CONTRASENIA);

		} catch (SQLException e) {
			System.err.println(e);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return con;
	}

}
