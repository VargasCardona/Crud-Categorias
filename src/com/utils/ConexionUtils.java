package com.utils;

import com.conexion.ConexionDB;

import org.mariadb.jdbc.Connection;

public class ConexionUtils {

	public static Connection realizarConexion() {
		try {
			ConexionDB conexionD = new ConexionDB();
			return conexionD.getConexion();
		} catch (Exception ex) {
			System.err.println("Conexión fallida");
		}
		return null;
	}
}
