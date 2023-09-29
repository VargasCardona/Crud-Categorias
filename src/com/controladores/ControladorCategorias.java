package com.controladores;

import java.sql.SQLIntegrityConstraintViolationException;
import com.excepciones.CamposVaciosException;
import com.excepciones.CategoriaEnUsoException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.modelos.Categoria;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.mariadb.jdbc.Connection;

public class ControladorCategorias {
	
	private Connection connection;

	public ControladorCategorias() {
		connection = DatabaseSingleton.getInstance().getConnection();
	}

	public void insertarCategoria(String nombre) {
		if (GeneralUtils.estaVacio(nombre)) {
			throw new CamposVaciosException();
		}

		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO categorias (id, nombre) VALUES (?, ?)");
			ps.setString(1, GeneralUtils.generarSku(nombre));
			ps.setString(2, nombre);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public ResultSet listarCategorias() {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM categorias");

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}

		return null;
	}

	public Categoria consultarId(String id) {
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM categorias WHERE id = ?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
			
			int cantidadColumnas = rsMd.getColumnCount();

			Object[] atributos = new Object[cantidadColumnas];
			while (rs.next()) {
				for (int i = 0; i < cantidadColumnas; i++) {
					atributos[i] = rs.getObject(i + 1);
				}
			}
			
			return new Categoria((String) atributos[0], (String) atributos[1]);

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public void actualizarCategoria(String id, String nombre) {
		if (GeneralUtils.estaVacio(id)) {
			throw new ElementoNoSeleccionadoException();
		}

		if (GeneralUtils.estaVacio(nombre)) {
			throw new CamposVaciosException();
		}

		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE categorias SET nombre = ? WHERE id = ?");
			ps.setString(1, nombre);
			ps.setString(2, id);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public void eliminarCategoria(String id) {
		if (GeneralUtils.estaVacio(id)) {
			throw new ElementoNoSeleccionadoException();
		}

		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM categorias WHERE id = ?");
			ps.setString(1, id);

			ps.execute();

		} catch (SQLIntegrityConstraintViolationException x) {
			throw new CategoriaEnUsoException();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public ResultSet obtenerCategorias() {

		try {
			ResultSet rs;

			String query = "SELECT * FROM categorias";

			PreparedStatement ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			return rs;
		} catch (SQLException ex) {
			System.err.println(ex.toString());
		}
		return null;
	}
}
